package br.ufrn.imd.models;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Matricula {
	private final Discente discente;
	
	private final Turma turma;
	
	private BigDecimal nota1;

	private BigDecimal nota2;

	private BigDecimal nota3;

	private Integer frequencia;
	
	private BigDecimal mediaParcial;
	
	private BigDecimal mediaFinal;
	
	private StatusAprovacao status;

	public Matricula(Discente discente, Turma turma) {
		this.discente = discente;
		this.turma = turma;
	}

	public BigDecimal getNota1() {
		return nota1;
	}

	public void cadastrarNota1(BigDecimal nota1) {
		if(verificarIntervaloNotas(nota1)) {
			throw new IllegalArgumentException("A nota deve estar entre 0 e 10");
		} 
		this.nota1 = nota1;
	}

	public BigDecimal getNota2() {
		return nota2;
	}

	public void cadastrarNota2(BigDecimal nota2) {
		if(verificarIntervaloNotas(nota2)) {
			throw new IllegalArgumentException("A nota deve estar entre 0 e 10");
		} 
		this.nota2 = nota2;
	}

	public BigDecimal getNota3() {
		return nota3;
	}

	public void cadastrarNota3(BigDecimal nota3) {
		if(verificarIntervaloNotas(nota3)) {
			throw new IllegalArgumentException("A nota deve estar entre 0 e 10");
		} 
		this.nota3 = nota3;
	}

	public Integer getFrequencia() {
		return frequencia;
	}

	public void cadastrarFrequencia(Integer frequencia) {
		if(verificarIntervaloFrequencia(frequencia)) {
			throw new IllegalArgumentException("A frequencia deve estar entre 0 e 100");
		} 
		this.frequencia = frequencia;
	}

	public Discente getDiscente() {
		return discente;
	}

	public Turma getTurma() {
		return turma;
	}

	public BigDecimal getMediaParcial() {
		return mediaParcial;
	}

	public void setMediaParcial(BigDecimal mediaParcial) {
		this.mediaParcial = mediaParcial;
	}

	public BigDecimal getMediaFinal() {
		return mediaFinal;
	}

	public void setMediaFinal(BigDecimal mediaFinal) {
		this.mediaFinal = mediaFinal;
	}

	public StatusAprovacao getStatus() {
		return status;
	}
	
	private void setStatus(StatusAprovacao status) {
		this.status = status;
	}
	
	public void consolidarParcialmente() {
		boolean frequenciaPermitida = verificarFrequencia();
		this.calcularMediaParcial();
		if(frequenciaPermitida) {
			if(this.verificarNotaMinima() && this.mediaParcial.compareTo(new BigDecimal(6)) >= 0) {
				this.setStatus(StatusAprovacao.APR);				
			} else if(this.mediaParcial.compareTo(new BigDecimal(3)) >= 0) {
				this.setStatus(StatusAprovacao.REC);
			} else {
				this.setStatus(StatusAprovacao.REP);
			}
		} else {
			if(this.mediaParcial.compareTo(new BigDecimal(3)) <= 0) {
				this.setStatus(StatusAprovacao.REPMF);
			} else {
				this.setStatus(StatusAprovacao.REPF);
			}
		}
	}
	
	private Boolean verificarFrequencia() {
		Boolean frequenciaPermitida = false;
		if(getFrequencia() >= 75) {
			frequenciaPermitida = true;
		}
		return frequenciaPermitida;
	}
	
	
	private void calcularMediaParcial() {
		BigDecimal soma = getNota1().add(getNota2()).add(getNota3());
		this.mediaParcial = soma.divide(new BigDecimal(3), RoundingMode.HALF_UP);
	}
	
	
	private Boolean verificarNotaMinima() {
		Boolean notaMinimaPermitida = false;
		if(this.getNota1().compareTo(new BigDecimal(4)) >= 0
			&& this.getNota2().compareTo(new BigDecimal(4)) >= 0
			&& this.getNota3().compareTo(new BigDecimal(4)) >= 0) {
			notaMinimaPermitida = true;
		}
		return notaMinimaPermitida;
	}
	
	private Boolean verificarIntervaloNotas(BigDecimal valor) {
		Boolean intervaloInvalido = false;
		if(valor.compareTo(new BigDecimal(0)) < 0 || valor.compareTo(new BigDecimal(10)) > 0) {
			intervaloInvalido = true;
		}
		return intervaloInvalido;
	}
	
	private Boolean verificarIntervaloFrequencia(Integer valor) {
		Boolean intervaloInvalido = false;
		if(valor < 0 || valor > 100) {
			intervaloInvalido = true;
		}
		return intervaloInvalido;
	}
	
	/*
	 	1. Critérios de Aprovação Direta:
			O aluno é considerado aprovado se:
			Média parcial nas unidades avaliativas for igual ou superior a 6,0.
			Nenhuma nota de unidade avaliativa for inferior a 4,0.
			Frequência mínima de 75% da carga horária for atingida.
			Média final: A média final do aluno aprovado será a média parcial, e o aluno estará dispensado da
			avaliação de reposição.
		2. Critérios para Avaliação de Reposição:
			O aluno terá direito a realizar uma avaliação de reposição se:
			Cumprir o critério de frequência mínima de 75%.
			Sua média parcial for igual ou superior a 3,0.
			A nota da avaliação de reposição substituirá a menor nota entre as unidades avaliativas do aluno.
		3. Reprovação:
			O aluno será reprovado se:
			Sua média parcial for inferior a 3,0.
			Não atingir a frequência mínima de 75%.
			Não atender aos critérios para realizar a avaliação de reposição.
			Média final: O aluno reprovado terá sua média final igual à média parcial.
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}