package com.mitocode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.model.Menu;
import com.mitocode.model.Rol;
import com.mitocode.service.IMenuService;

@RestController
@RequestMapping("/menus")
public class MenuController {

	@Autowired
	private IMenuService service;

	@GetMapping
	public ResponseEntity<List<Menu>> listar() throws Exception {
		List<Menu> menus = service.listar();
		return new ResponseEntity<>(menus, HttpStatus.OK);
	}

	@PostMapping("/usuario")
	public ResponseEntity<List<Menu>> listar(@RequestBody String nombre) {
		List<Menu> menus = service.listarMenuPorUsuario(nombre);
		return new ResponseEntity<>(menus, HttpStatus.OK);
	}

	@PostMapping("/rolUsuario")
	public ResponseEntity<Rol> obtenerRolUsuario(@RequestBody String nombre) {
		Rol rolUsuario = service.obtenerRolUsuario(nombre);
		return new ResponseEntity<>(rolUsuario, HttpStatus.OK);
	}

}
