package com.edpas.service;

import java.util.List;

import com.edpas.dto.FilterPurchaseDTO;
import com.edpas.dto.PurchaseSummaryDTO;
import com.edpas.model.Purchase;

public interface IPurchaseService extends ICRUDService<Purchase> {

	public List<Purchase> searchByDriverLicenseAndFullName(FilterPurchaseDTO filterPurchaseDTO);
	
	public List<Purchase> searchByDates(FilterPurchaseDTO filterPurchaseDTO);
	
	public List<PurchaseSummaryDTO> listPurchaseSummary();
	
	public byte[] generateReportPurchaseSummary();
}
