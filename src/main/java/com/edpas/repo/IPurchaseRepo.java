package com.edpas.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edpas.model.Purchase;

public interface IPurchaseRepo extends JpaRepository<Purchase, Integer> {

	@Query("FROM Purchase purchase WHERE purchase.person.driverLicense = :driverLicense OR LOWER(purchase.person.firstName) LIKE %:fullName% OR LOWER(purchase.person.lastName) LIKE %:fullName%")
	public List<Purchase> searchByDriverLicenseAndFullName(@Param("driverLicense")String driverLicense, @Param("fullName")String fullName);
	
	@Query("FROM Purchase purchase WHERE purchase.date BETWEEN :date AND :nextDate")
	public List<Purchase> searchByDates(@Param("date")LocalDateTime date, @Param("nextDate")LocalDateTime nextDate);

	/* 
	 * FUNCTION ON POSTGRESQL
	 * ======== == =========
  	CREATE OR REPLACE FUNCTION fn_list_purchases () 
		RETURNS TABLE (
		 quantity INT,
		 date TEXT
		) 
	AS $$
	DECLARE 
	    var_r record;
	BEGIN
	FOR var_r IN(
		select (count(*)::int) as quantity, to_char(p.date, 'MM/dd/yyyy') as date from purchase p 
		group by to_char(p.date, 'MM/dd/yyyy') order by to_char(p.date, 'MM/dd/yyyy') asc 
		)  
	 LOOP
        quantity := var_r.quantity; 
 		date := var_r.date;
        RETURN NEXT;
	 END LOOP;
	END; $$ 
	LANGUAGE 'plpgsql';
	 */
	@Query(value = "SELECT * FROM fn_list_purchases()", nativeQuery = true)
	public List<Object[]> listPurchaseSummary();
}
