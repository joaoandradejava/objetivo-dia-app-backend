package com.joaoandrade.objetivodiaapp.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.StringUtils;

@Entity
public class Anotacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;

	@Lob
	private String conteudo;

	@CreationTimestamp
	private LocalDateTime dataCriacao;

	@UpdateTimestamp
	private LocalDateTime dataModificacao;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Anotacao() {
	}

	public Anotacao(Long id, String titulo, String conteudo, LocalDateTime dataCriacao, LocalDateTime dataModificacao,
			Usuario usuario) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.conteudo = conteudo;
		this.dataCriacao = dataCriacao;
		this.dataModificacao = dataModificacao;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(LocalDateTime dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String pegarConteudoResumido() {
		if (!StringUtils.hasLength(conteudo)) {
			return null;
		}

		int tamanhoMaximo = conteudo.length() > 255 ? 255 : conteudo.length();
		String conteudoResumido = "";

		for (int i = 0; i < tamanhoMaximo; i++) {
			conteudoResumido += this.conteudo.charAt(i);
		}

		return conteudoResumido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Anotacao other = (Anotacao) obj;
		return Objects.equals(id, other.id);
	}

}
