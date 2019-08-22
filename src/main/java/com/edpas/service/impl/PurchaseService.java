package com.edpas.service.impl;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.edpas.dto.FilterPurchaseDTO;
import com.edpas.dto.PurchaseSummaryDTO;
import com.edpas.model.Purchase;
import com.edpas.repo.IPurchaseRepo;
import com.edpas.service.IPurchaseService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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

	@Override
	public List<Purchase> searchByDriverLicenseAndFullName(FilterPurchaseDTO filterPurchaseDTO) {
		return this.purchaseRepo.searchByDriverLicenseAndFullName(filterPurchaseDTO.getDriverLicense(), filterPurchaseDTO.getFullName());
	}

	@Override
	public List<Purchase> searchByDates(FilterPurchaseDTO filterPurchaseDTO) {	
		LocalDateTime nextDate = filterPurchaseDTO.getDate().plusDays(1);
		return this.purchaseRepo.searchByDates(filterPurchaseDTO.getDate(), nextDate);
	}
	
	@Override
	public List<PurchaseSummaryDTO> listPurchaseSummary() {
		List<PurchaseSummaryDTO> list = new ArrayList<PurchaseSummaryDTO>();
		this.purchaseRepo.listPurchaseSummary().forEach(item -> {
			PurchaseSummaryDTO purchaseSummaryDTO = new PurchaseSummaryDTO();
			purchaseSummaryDTO.setQuantity(Integer.parseInt(item[0].toString()));
			purchaseSummaryDTO.setDate(item[1].toString());
			list.add(purchaseSummaryDTO);
		});
		return list;
	}
	
	@Override
	public byte[] generateReportPurchaseSummary() {
		byte[] report = null;
		try {
			File file = new ClassPathResource("/reports/purchaseSummary.jasper").getFile();
			JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(this.listPurchaseSummary());
			JasperPrint jasperPrint = JasperFillManager.fillReport(file.getPath(), null, data);
			report = JasperExportManager.exportReportToPdf(jasperPrint);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}		
		return report;
	}
	
}
