package com.edpas.service.impl;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RestAuthService {

	public boolean hasAccess(String path) {
		String methodRole = "";
		switch(path) {
		case "person.getAll":
		case "person.listPageable":
		case "person.getOne":
		case "product.getAll":
		case "product.listPageable":
		case "product.getOne":
		case "purchase.insert":
		case "purchase.getOne":
		case "purchase.getAll":
		case "purchase.listPageable":
		case "purchase.searchByDriverLicenseAndFullName":
		case "purchase.searchByDates":
		case "purchase.listPurchaseSummary":
		case "purchase.generateReportPurchaseSummary":			
			methodRole = "Administrator,Manager,Operator";
			break;
			
		case "person.insert":
		case "person.delete":
		case "person.update":
		case "product.insert":
		case "product.delete":
		case "product.update":
		case "purchase.delete":
		case "purchase.update":
			methodRole = "Administrator,Manager";
			break;
		}
		String[] methodRoles = methodRole.split(",");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			System.out.println("PATH: "+path);
			System.out.println("USER: "+authentication.getName());
			
			for (GrantedAuthority grantedAuthority: authentication.getAuthorities()) {
				String role = grantedAuthority.getAuthority();
				System.out.println("ROLE: "+role);
				for (String roleName: methodRoles) {
					if (role.equalsIgnoreCase(roleName)) {
						System.out.println("HAS ACCESS: YES");
						return true;
					}
					else {
						System.out.println("HAS ACCESS: NO");
					}
				}
			}
		}
		return false;
	}
}
