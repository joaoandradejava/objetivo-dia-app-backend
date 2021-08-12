package com.joaoandrade.objetivodiaapp.api.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EsqueciSenhaInput {

	@Size(max = 255)
	@Email
	@NotBlank
	private String email;

	public EsqueciSenhaInput() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}