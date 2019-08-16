package com.edpas.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edpas.model.Purchase;

public interface IPurchaseRepo extends JpaRepository<Purchase, Integer> {

}
