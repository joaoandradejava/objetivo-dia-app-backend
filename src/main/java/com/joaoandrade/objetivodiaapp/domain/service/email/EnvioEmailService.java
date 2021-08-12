package com.joaoandrade.objetivodiaapp.domain.service.email;

import com.joaoandrade.objetivodiaapp.domain.model.Usuario;

public interface EnvioEmailService {

	public void enviarEmail(Usuario usuario, String titulo, String corpo);
}
