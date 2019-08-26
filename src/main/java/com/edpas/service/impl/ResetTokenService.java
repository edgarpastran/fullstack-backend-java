package com.edpas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edpas.model.ResetToken;
import com.edpas.repo.IResetTokenRepo;
import com.edpas.service.IResetTokenService;

@Service
public class ResetTokenService implements IResetTokenService {

	@Autowired
	private IResetTokenRepo resetTokenRepo;
	
	@Override
	public ResetToken findByToken(String token) {
		return this.resetTokenRepo.findByToken(token);
	}

	@Override
	public void save(ResetToken resetToken) {
		this.resetTokenRepo.save(resetToken);
	}

	@Override
	public void delete(ResetToken resetToken) {
		this.resetTokenRepo.delete(resetToken);
	}

}
