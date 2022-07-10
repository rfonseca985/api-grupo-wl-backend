package com.backend.dtos;

import java.io.Serializable;

import com.backend.domain.Alimento;

public class AlimentoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	
	public AlimentoDTO() {
		super();
	}

	public AlimentoDTO(Alimento obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
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
	
	
	
	
	
	

}
