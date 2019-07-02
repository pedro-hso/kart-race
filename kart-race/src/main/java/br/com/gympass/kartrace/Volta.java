package br.com.gympass.kartrace;

import java.time.LocalTime;

public class Volta implements Comparable<Volta> {
	private LocalTime hora;
	private String numeroPiloto;
	private String nomePiloto;
	private int numeroVolta;
	private LocalTime tempoVolta;
	private float velocidadeMediaVolta;

	public Volta(LocalTime hora, String numeroPiloto, String nomePiloto, int numeroVolta, LocalTime tempoVolta,
			float velocidadeMediaVolta) {
		this.hora = hora;
		this.numeroPiloto = numeroPiloto;
		this.nomePiloto = nomePiloto;
		this.numeroVolta = numeroVolta;
		this.tempoVolta = tempoVolta;
		this.velocidadeMediaVolta = velocidadeMediaVolta;
	}

	public LocalTime getHora() {
		return hora;
	}

	public String getNumeroPiloto() {
		return numeroPiloto;
	}

	public String getNomePiloto() {
		return nomePiloto;
	}

	public int getNumeroVolta() {
		return numeroVolta;
	}

	public LocalTime getTempoVolta() {
		return tempoVolta;
	}

	public float getVelocidadeMediaVolta() {
		return velocidadeMediaVolta;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public void setNumeroPiloto(String numeroPiloto) {
		this.numeroPiloto = numeroPiloto;
	}

	public void setNomePiloto(String nomePiloto) {
		this.nomePiloto = nomePiloto;
	}

	public void setNumeroVolta(int numeroVolta) {
		this.numeroVolta = numeroVolta;
	}

	public void setTempoVolta(LocalTime tempoVolta) {
		this.tempoVolta = tempoVolta;
	}

	public void setVelocidadeMediaVolta(float velocidadeMediaVolta) {
		this.velocidadeMediaVolta = velocidadeMediaVolta;
	}

	public String toString() {
		return String.format("%12s %3s %-20s %d %12s %.3f", hora.toString(), numeroPiloto, nomePiloto, numeroVolta,
				tempoVolta.toString(), velocidadeMediaVolta);
	}

	@Override
	public int compareTo(Volta volta) {
		if (this.numeroPiloto != volta.numeroPiloto) {
			return this.numeroPiloto.compareTo(volta.numeroPiloto);
		}

		return Integer.compare(this.numeroVolta, volta.numeroVolta);
	}
}
