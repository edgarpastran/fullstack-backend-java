package com.edpas.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.edpas.dto.FilterPurchaseDTO;
import com.edpas.dto.PurchaseSummaryDTO;
import com.edpas.exception.ModelNotFoundException;
import com.edpas.model.Purchase;
import com.edpas.service.IPurchaseService;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

	@Autowired
	private IPurchaseService purchaseService;
	
	@GetMapping
	public ResponseEntity<List<Purchase>> list() {
		List<Purchase> list = this.purchaseService.getAll();
		return new ResponseEntity<List<Purchase>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Purchase> find(@PathVariable("id") Integer id) {
		Purchase object = this.purchaseService.getOne(id);
		if (object == null) {
			throw new ModelNotFoundException("Id not found: "+id);
		}
		return new ResponseEntity<Purchase>(object, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> register(@Valid @RequestBody Purchase purchase) {
		Purchase saved = this.purchaseService.insert(purchase);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getIdPurchase()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Object> update(@Valid @RequestBody Purchase purchase) {
		this.purchaseService.update(purchase);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
		Purchase object = this.purchaseService.getOne(id);
		if (object == null) {
			throw new ModelNotFoundException("Id not found: "+id);
		}
		else {
			this.purchaseService.delete(id);
		}
		return new ResponseEntity<Object>(object, HttpStatus.OK);
	}
	
	@PostMapping("/search")
	public ResponseEntity<List<Purchase>> search(@RequestBody FilterPurchaseDTO filterPurchaseDTO) {
		List<Purchase> purchases = new ArrayList<>();
		if (filterPurchaseDTO != null) {
			if (filterPurchaseDTO.getDate() != null) {
				purchases = this.purchaseService.searchByDates(filterPurchaseDTO);
			}
			else {
				purchases = this.purchaseService.searchByDriverLicenseAndFullName(filterPurchaseDTO);
			}
		}
		return new ResponseEntity<List<Purchase>>(purchases, HttpStatus.OK);
	}
	
	@GetMapping("/listPurchaseSummary")
	public ResponseEntity<List<PurchaseSummaryDTO>> listPurchaseSummary() {
		List<PurchaseSummaryDTO> list = new ArrayList<PurchaseSummaryDTO>();
		list = this.purchaseService.listPurchaseSummary();
		return new ResponseEntity<List<PurchaseSummaryDTO>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/reportPurchaseSummary", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> reportPurchaseSummary() {
		byte[] report = this.purchaseService.generateReportPurchaseSummary();
		return new ResponseEntity<byte[]>(report, HttpStatus.OK);
	}
}
