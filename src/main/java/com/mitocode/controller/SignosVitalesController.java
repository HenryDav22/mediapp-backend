package com.mitocode.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
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

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.SignosVitales;
import com.mitocode.service.ISignosVitalesService;

@RestController
@RequestMapping("/signosvitales")
public class SignosVitalesController {

	@Autowired
	private ISignosVitalesService service;

	private String mensajeNoEncontrado = "ID NO ENCONTRADO ";

	@GetMapping
	public ResponseEntity<List<SignosVitales>> listar() throws Exception {
		List<SignosVitales> lista = service.listar();
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SignosVitales> listarPorId(@PathVariable("id") Integer id) throws Exception {
		SignosVitales obj = service.listarPorId(id);

		if (obj == null) {
			throw new ModeloNotFoundException(mensajeNoEncontrado.concat(id.toString()));
		}
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@GetMapping("/hateoas/{id}")
	public EntityModel<SignosVitales> listarPorIdHateoas(@PathVariable("id") Integer id) throws Exception {
		SignosVitales obj = service.listarPorId(id);

		if (obj.getIdSignos() == null) {
			throw new ModeloNotFoundException(mensajeNoEncontrado.concat(id.toString()));
		}

		EntityModel<SignosVitales> recurso = EntityModel.of(obj);

		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));

		recurso.add(linkTo.withRel("signos-recurso"));

		return recurso;
	}

	@PostMapping
	public ResponseEntity<SignosVitales> registrar(@Valid @RequestBody SignosVitales p) throws Exception {
		SignosVitales obj = service.registrar(p);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSignos())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<SignosVitales> modificar(@Valid @RequestBody SignosVitales p) throws Exception {
		SignosVitales obj = service.modificar(p);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		SignosVitales obj = service.listarPorId(id);

		if (obj == null) {
			throw new ModeloNotFoundException(mensajeNoEncontrado.concat(id.toString()));
		}

		service.eliminar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/pageable")
	public ResponseEntity<Page<SignosVitales>> listarPageable(Pageable pageable) throws Exception {
		Page<SignosVitales> pacientes = service.listarPageable(pageable);
		return new ResponseEntity<>(pacientes, HttpStatus.OK);
	}
}
