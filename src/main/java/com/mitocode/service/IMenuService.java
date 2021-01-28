package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Menu;
import com.mitocode.model.Rol;

public interface IMenuService extends ICRUD<Menu, Integer>{
	
	List<Menu> listarMenuPorUsuario(String nombre);

	Rol obtenerRolUsuario(String nombre);
}
