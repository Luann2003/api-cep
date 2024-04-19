package com.apicadastro.apicadastro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.apicadastro.apicadastro.dto.CepDTO;
import com.apicadastro.apicadastro.entities.Cep;
import com.apicadastro.apicadastro.repositories.CepRepository;
import com.apicadastro.apicadastro.services.exceptions.DatabaseException;
import com.apicadastro.apicadastro.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CepService {
	
	@Autowired
	private CepRepository repository;
	
	@Transactional(readOnly = true)
	public Page<CepDTO> findAll(String name, Pageable pageable){
		Page<Cep> list = repository.searchByUf(name, pageable);
		return list.map(x-> new CepDTO(x));
	}
	
	@Transactional(readOnly = true)
	public CepDTO findById(Long id) {
		Cep cep = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new CepDTO(cep);
	}
	
	@Transactional()
	public CepDTO insert(CepDTO dto) {
		Cep entity = new Cep();	
		copyDtoToEntity(dto, entity);
		
		entity = repository.save(entity);
		
		return new CepDTO(entity);
	}

	@Transactional()
	public CepDTO update(Long id, CepDTO dto) {
		try {
			Cep entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new CepDTO(entity);
		}
		catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
    	if (!repository.existsById(id)) {
    		throw new ResourceNotFoundException("Recurso não encontrado");
    	}
    	try {
            repository.deleteById(id);    		
    	}
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
	
	private void copyDtoToEntity(CepDTO dto, Cep entity) {
		entity.setCep(dto.getCep());
		entity.setLogradouro(dto.getLogradouro());
		entity.setComplemento(dto.getComplemento());
		entity.setBairro(dto.getBairro());
		entity.setLocalidade(dto.getLocalidade());
		entity.setUf(dto.getUf());
		entity.setIbge(dto.getIbge());
		entity.setGia(dto.getGia());
		entity.setDdd(dto.getDdd());
		entity.setSiafi(dto.getSiafi());	
	}

}
