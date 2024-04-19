package com.apicadastro.apicadastro.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.apicadastro.apicadastro.entities.Cep;

public interface CepRepository extends JpaRepository<Cep, Long> {
	
	@Query("SELECT obj FROM Cep obj " +
	 "WHERE UPPER(obj.uf) LIKE UPPER(CONCAT('%', :name, '%'))")
	Page<Cep> searchByUf(String name, Pageable pageable);

}
