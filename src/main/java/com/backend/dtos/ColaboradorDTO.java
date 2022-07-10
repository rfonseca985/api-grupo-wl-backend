package com.backend.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.backend.domain.Colaborador;

public class ColaboradorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotEmpty(message = "Campo NOME é requerido")
	@Length(min= 3, max= 255 , message = "O campo NOME de ter entre 3 e 255 caracteres")
	private String nome;
	
	@NotEmpty(message = "Campo CPF é requerido")
	@CPF
	private String cpf;
	
	public ColaboradorDTO() {
		super();
	}

	public ColaboradorDTO(Colaborador obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	
}
