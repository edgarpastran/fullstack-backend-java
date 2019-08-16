package com.edpas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edpas.model.Purchase;
import com.edpas.repo.IPurchaseRepo;
import com.edpas.service.IPurchaseService;

@Service
public class PurchaseService implements IPurchaseService {

	@Autowired
	private IPurchaseRepo purchaseRepo;

	@Override
	public Purchase insert(Purchase object) {
		object.getPurchaseDetails().forEach(detail -> {
			detail.setPurchase(object);
		});
		return this.purchaseRepo.save(object);
	}

	@Override
	public Purchase update(Purchase object) {
		return this.purchaseRepo.save(object);
	}

	@Override
	public void delete(Integer id) {
		this.purchaseRepo.delete(id);
	}

	@Override
	public Purchase getOne(Integer id) {
		return this.purchaseRepo.findOne(id);
	}

	@Override
	public List<Purchase> getAll() {
		return this.purchaseRepo.findAll();
	}
	
}