package com.joaoandrade.objetivodiaapp.domain.dto;

public class GraficoObjetivoConcluidoDTO {
	private int objetivosConcluidos;
	private int objetivosNaoConcluidos;

	public GraficoObjetivoConcluidoDTO() {
	}

	public GraficoObjetivoConcluidoDTO(int objetivosConcluidos, int objetivosNaoConcluidos) {
		super();
		this.objetivosConcluidos = objetivosConcluidos;
		this.objetivosNaoConcluidos = objetivosNaoConcluidos;
	}

	public int getObjetivosConcluidos() {
		return objetivosConcluidos;
	}

	public void setObjetivosConcluidos(int objetivosConcluidos) {
		this.objetivosConcluidos = objetivosConcluidos;
	}

	public int getObjetivosNaoConcluidos() {
		return objetivosNaoConcluidos;
	}

	public void setObjetivosNaoConcluidos(int objetivosNaoConcluidos) {
		this.objetivosNaoConcluidos = objetivosNaoConcluidos;
	}

}
