package com.edpas.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.edpas.model.Menu;
import com.edpas.repo.IMenuRepo;
import com.edpas.service.IMenuService;

@Service
public class MenuService implements IMenuService {

	@Autowired
	private IMenuRepo menuRepo;
		

	@Override
	public Menu insert(Menu object) {
		return this.menuRepo.save(object);
	}

	@Override
	public Menu update(Menu object) {
		return this.menuRepo.save(object);
	}

	@Override
	public void delete(Integer id) {
		this.menuRepo.delete(id);
	}

	@Override
	public Menu getOne(Integer id) {
		return this.menuRepo.findOne(id);
	}

	@Override
	public List<Menu> getAll() {
		return this.menuRepo.findAll();
	}

	@Override
	public Page<Menu> listPageable(Pageable pageable) {
		return this.menuRepo.findAll(pageable);
	}

	@Override
	public List<Menu> listMenuByUsername(String username) {
		List<Menu> menus = new ArrayList<Menu>();
		this.menuRepo.listMenuByUsername(username).forEach(data -> {
			Menu menu = new Menu();
			menu.setIdMenu(Integer.parseInt(data[0].toString()));
			menu.setIcon(data[1].toString());
			menu.setName(data[2].toString());
			menu.setUrl(data[3].toString());
			menus.add(menu);
		});				
		return menus;
	}
}
