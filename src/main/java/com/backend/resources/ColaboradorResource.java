package com.backend.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.backend.domain.Colaborador;
import com.backend.dtos.ColaboradorDTO;
import com.backend.service.ColaboradorService;

@RestController
@RequestMapping(value = "/colaborador")
public class ColaboradorResource {

	@Autowired
	private ColaboradorService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Colaborador> findById(@PathVariable Long id) {
		Colaborador obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<ColaboradorDTO>> findAll() {
		List<Colaborador> list = service.findAll();
		List<ColaboradorDTO> listDTO = list.stream().map(obj -> new ColaboradorDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<Colaborador> create(@Valid @RequestBody Colaborador obj) {
		obj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ColaboradorDTO> update(@PathVariable Long id, @Valid @RequestBody ColaboradorDTO objDto) {
		Colaborador newObj = service.update(id, objDto);
		return ResponseEntity.ok().body(new ColaboradorDTO(newObj));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
