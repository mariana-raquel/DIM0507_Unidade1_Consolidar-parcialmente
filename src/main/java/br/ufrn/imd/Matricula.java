package br.ufrn.imd;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.google.common.collect.Lists;

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
		if(nota1.compareTo(new BigDecimal(0)) < 0 || nota1.compareTo(new BigDecimal(10)) > 0) {
			throw new IllegalArgumentException("A nota deve estar entre 0 e 10");
		} 
		this.nota1 = nota1;
	}

	public BigDecimal getNota2() {
		return nota2;
	}

	public void cadastrarNota2(BigDecimal nota2) {
		if(nota2.compareTo(new BigDecimal(0)) < 0 || nota2.compareTo(new BigDecimal(10)) > 0) {
			throw new IllegalArgumentException("A nota deve estar entre 0 e 10");
		} 
		this.nota2 = nota2;
	}

	public BigDecimal getNota3() {
		return nota3;
	}

	public void cadastrarNota3(BigDecimal nota3) {
		if(nota3.compareTo(new BigDecimal(0)) < 0 || nota3.compareTo(new BigDecimal(10)) > 0) {
			throw new IllegalArgumentException("A nota deve estar entre 0 e 10");
		} 
		this.nota3 = nota3;
	}

	public Integer getFrequencia() {
		return frequencia;
	}

	public void cadastrarFrequencia(Integer frequencia) {
		if(frequencia < 0 || frequencia > 100) {
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
		this.calcularMediaParcial();
		
		if(this.verificarFrequencia()) {
			
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
		
		List<BigDecimal> notas = Lists.newArrayList(this.nota1, this.nota2, this.nota2);
		
		long notasIntervaloPermitido = notas.stream().filter(n -> n.compareTo(new BigDecimal(4)) >= 0).count();
		
		if(notasIntervaloPermitido == 3l) {
			notaMinimaPermitida = true;
		}
		
		return notaMinimaPermitida;
	}

}