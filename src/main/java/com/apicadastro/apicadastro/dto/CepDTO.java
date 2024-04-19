package com.apicadastro.apicadastro.dto;

import com.apicadastro.apicadastro.entities.Cep;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CepDTO {
	
	private Long id;
	
	@NotBlank(message = "Campo obrigatorio")
	@Size(min = 8, message = "deve ter no minimo 8 digitos")
	@Pattern(regexp = "\\d+", message = "Somente números são permitidos")
	private String cep;
	
	private String logradouro;
	private String complemento;
	@NotBlank(message = "Campo obrigatorio")
	private String bairro;
	@NotBlank(message = "Campo obrigatorio")
	private String localidade;
	@NotBlank(message = "Campo obrigatorio")
	private String uf;
	@NotBlank(message = "Campo obrigatorio")
	private String ibge;
	@NotNull(message = "Campo obrigatorio")
	private Long gia;
	@NotNull(message = "Campo obrigatorio")
	private Long ddd;
	@NotNull(message = "Campo obrigatorio")
	private Long siafi;
	
	public CepDTO() {
	}

	public CepDTO(Long id, String cep, String logradouro, String complemento, String bairro, String localidade,
			String uf, String ibge, Long gia, Long ddd, Long siafi) {
		this.id = id;
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.ibge = ibge;
		this.gia = gia;
		this.ddd = ddd;
		this.siafi = siafi;
	}
	
	public CepDTO(Cep entity) {
		id = entity.getId();
		cep = entity.getCep();
		logradouro = entity.getLogradouro();
		complemento = entity.getComplemento();
		bairro = entity.getBairro();
		localidade = entity.getLocalidade();
		uf = entity.getUf();
		ibge = entity.getIbge();
		gia = entity.getGia();
		ddd = entity.getDdd();
		siafi = entity.getSiafi();
	}

	public Long getId() {
		return id;
	}

	public String getCep() {
		return cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public String getUf() {
		return uf;
	}

	public String getIbge() {
		return ibge;
	}

	public Long getGia() {
		return gia;
	}

	public Long getDdd() {
		return ddd;
	}

	public Long getSiafi() {
		return siafi;
	}
}
