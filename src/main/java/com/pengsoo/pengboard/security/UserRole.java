package com.pengsoo.pengboard.security;

import lombok.Getter;

@Getter
public enum UserRole { //enum은 열거형 상수들의 집합

	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	
	UserRole(String value) {
		this.value = value;
		
	}
	private String value;
	
}
