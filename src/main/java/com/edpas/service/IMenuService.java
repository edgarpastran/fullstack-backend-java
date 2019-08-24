package com.edpas.service;

import java.util.List;

import com.edpas.model.Menu;

public interface IMenuService extends ICRUDService<Menu> {

	public List<Menu> listMenuByUsername(String username);
}
