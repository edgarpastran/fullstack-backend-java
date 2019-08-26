package com.edpas.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edpas.model.ResetToken;

public interface IResetTokenRepo extends JpaRepository<ResetToken, Long> {

	public ResetToken findByToken(String token);
}
