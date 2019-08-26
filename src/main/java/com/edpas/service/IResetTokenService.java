package com.edpas.service;

import com.edpas.model.ResetToken;

public interface IResetTokenService {

	public ResetToken findByToken(String token);
	
	public void save(ResetToken resetToken);
	
	public void delete(ResetToken resetToken);
}
