package com.edpas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_access_token")
public class OauthAccessToken {

	@Id
	@Column(name = "token_id", length = 256)
	private String tokenId;
	
	@Column(name = "token")
	private byte[] token;
	
	@Column(name = "authentication_id", length = 256)
	private String authenticationId;
	
	@Column(name = "user_name", length = 256)
	private String userName;
	
	@Column(name = "client_id", length = 256)
	private String clientId;
	
	@Column(name = "authentication")
	private byte[] authentication;
	
	@Column(name = "refresh_token", length = 256)
	private String refreshToken;

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public byte[] getToken() {
		return token;
	}

	public void setToken(byte[] token) {
		this.token = token;
	}

	public String getAuthenticationId() {
		return authenticationId;
	}

	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public byte[] getAuthentication() {
		return authentication;
	}

	public void setAuthentication(byte[] authentication) {
		this.authentication = authentication;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
		
}
