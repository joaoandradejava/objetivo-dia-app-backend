package com.joaoandrade.objetivodiaapp.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.joaoandrade.objetivodiaapp.domain.model.Usuario;
import com.joaoandrade.objetivodiaapp.domain.observer.EsqueciSenhaObserver;
import com.joaoandrade.objetivodiaapp.domain.service.email.EnvioEmailService;

@Component
public class EmailListener {

	@Autowired
	private EnvioEmailService envioEmailService;

	@EventListener
	public void esqueciSenhaListener(EsqueciSenhaObserver esqueciSenhaObserver) {
		Usuario usuario = esqueciSenhaObserver.getUsuario();
		usuario.setSenha(esqueciSenhaObserver.getNovaSenha());
		envioEmailService.enviarEmail(esqueciSenhaObserver.getUsuario(), "Esqueceu a senha", "esqueci-senha.html");

	}
}
