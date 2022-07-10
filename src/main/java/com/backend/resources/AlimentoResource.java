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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.backend.domain.Alimento;
import com.backend.dtos.AlimentoDTO;
import com.backend.service.AlimentoService;

@RestController
@RequestMapping(value = "/alimento")
public class AlimentoResource {

	@Autowired
	private AlimentoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Alimento> findaById(@PathVariable Long id) {
		Alimento obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<AlimentoDTO>> findAll(
			@RequestParam(value = "colaborador", defaultValue = "0") Long id_colab) {
		List<Alimento> list = service.findAll(id_colab);
		List<AlimentoDTO> listDTO = list.stream().map(obj -> new AlimentoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Alimento> update(@PathVariable Long id, @Valid @RequestBody Alimento obj) {
		Alimento newObj = service.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}

	@PostMapping
	public ResponseEntity<Alimento> create(@RequestParam(value = "colaborador", defaultValue = "0") Long id_colab,
			@Valid @RequestBody Alimento obj) {
		Alimento newObj = service.create(id_colab, obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/alimentos/{id}")
				.buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
