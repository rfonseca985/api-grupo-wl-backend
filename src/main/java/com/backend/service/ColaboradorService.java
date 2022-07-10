package com.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.domain.Colaborador;
import com.backend.dtos.ColaboradorDTO;
import com.backend.repositories.ColaboradorRepository;
import com.backend.service.excepitions.DataIntegrityViolationException;
import com.backend.service.excepitions.ObjectNotFoundException;


@Service
public class ColaboradorService {

	@Autowired
	private ColaboradorRepository repository;
	
	public Colaborador findById(Long id) {
		Optional<Colaborador> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Colaborador não encontrado! Id: " + id + " Tipo " + Colaborador.class.getName()));
	}
	
	public List<Colaborador> findAll(){
		return repository.findAll();
	}
	
	public Colaborador create (Colaborador obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Colaborador update(Long id, ColaboradorDTO objDto) {
		Colaborador obj = findById(id);
		obj.setNome(objDto.getNome());
		obj.setCpf(objDto.getCpf());
		return repository.save(obj);
	}

	public void deleteById(Long id) {
		repository.findById(id);

		try {
			repository.deleteById(id);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(
					"Colaborador não pode ser deletado! Possui alimentos associados Id: " + id + ", Type: " + Colaborador.class.getName());
		}
	}

	
}
