package com.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.domain.Alimento;
import com.backend.domain.Colaborador;
import com.backend.repositories.AlimentoRepository;
import com.backend.service.excepitions.ObjectNotFoundException;

@Service
public class AlimentoService {


	@Autowired
	private AlimentoRepository repository;
	@Autowired ColaboradorService service;
	
	public Alimento findById(Long id) {
		Optional<Alimento> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Alimento não encontrado! Id: " + id + " Tipo: " + Alimento.class.getName()));
	}
	
	public List<Alimento> findAll(Long id_colab){
		service.findById(id_colab);
		return repository.findAllByColaboradorId(id_colab);
	}

	public Alimento update(Long id, Alimento obj) {
		Alimento newObj = findById(id);
		updateData(newObj,obj);
		return repository.save(newObj);
	}

	private void updateData(Alimento newObj, Alimento obj) {
		newObj.setNome(obj.getNome());
		
	}

	public Alimento create(Long id_colab, Alimento obj) {
		obj.setId(null);
		Colaborador colab = service.findById(id_colab);
		obj.setColaborador(colab);
		return repository.save(obj);
	}

	public void delete(Long id) {
		Alimento obj = findById(id);
		repository.delete(obj);
		
	}
}
