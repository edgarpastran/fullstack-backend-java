package com.edpas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edpas.model.Menu;
import com.edpas.service.IMenuService;

@RestController
@RequestMapping("/menus")
public class MenuController {

	@Autowired
	private IMenuService menuService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Menu>> getAll() {
		List<Menu> menues = new ArrayList<>();
		menues = this.menuService.getAll();
		return new ResponseEntity<List<Menu>>(menues, HttpStatus.OK);
	}
	
	@PostMapping(value = "/username", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Menu>> listByUsername(@RequestBody String username) {
		List<Menu> menus = new ArrayList<>();
		menus = this.menuService.listMenuByUsername(username);
		return new ResponseEntity<List<Menu>>(menus, HttpStatus.OK);
	}
}
