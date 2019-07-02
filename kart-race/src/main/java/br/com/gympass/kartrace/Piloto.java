package br.com.gympass.kartrace;

import java.time.LocalTime;

public class Piloto implements Comparable<Piloto> {
	private int posicao;
	private String numeroPiloto;
	private String nomePiloto;
	private int voltasCompletadas;
	private LocalTime tempoProva;
	private int melhorVolta;
	private float velocidadeMedia;
	private LocalTime melhorVoltaProva;

	public Piloto(Volta volta) {
		this.numeroPiloto = volta.getNumeroPiloto();
		this.nomePiloto = volta.getNomePiloto();
		this.voltasCompletadas = volta.getNumeroVolta();
		this.tempoProva = volta.getTempoVolta();
		this.melhorVolta = volta.getNumeroVolta();
		this.velocidadeMedia = volta.getVelocidadeMediaVolta();
		this.melhorVoltaProva = volta.getTempoVolta();
	}

	public int getPosicao() {
		return posicao;
	}

	public String getNumeroPiloto() {
		return numeroPiloto;
	}

	public String getNomePiloto() {
		return nomePiloto;
	}

	public int getVoltasCompletadas() {
		return voltasCompletadas;
	}

	public LocalTime getTempoProva() {
		return tempoProva;
	}

	public int getMelhorVolta() {
		return melhorVolta;
	}

	public float getVelocidadeMedia() {
		return velocidadeMedia;
	}

	public LocalTime getMelhorVoltaProva() {
		return melhorVoltaProva;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public void setNumeroPiloto(String numeroPiloto) {
		this.numeroPiloto = numeroPiloto;
	}

	public void setNomePiloto(String nomePiloto) {
		this.nomePiloto = nomePiloto;
	}

	public void setVoltasCompletadas(int voltasCompletadas) {
		this.voltasCompletadas = voltasCompletadas;
	}

	public void setTempoProva(LocalTime tempoProva) {
		this.tempoProva = tempoProva;
	}

	public void setMelhorVolta(int melhorVolta) {
		this.melhorVolta = melhorVolta;
	}

	public void setVelocidadeMedia(float velocidadeMedia) {
		this.velocidadeMedia = velocidadeMedia;
	}

	public void setMelhorVoltaProva(LocalTime melhorVoltaProva) {
		this.melhorVoltaProva = melhorVoltaProva;
	}

	@Override
	public int compareTo(Piloto piloto) {
		if (this.voltasCompletadas > piloto.voltasCompletadas) {
			return -1;
		}

		if (this.voltasCompletadas < piloto.voltasCompletadas) {
			return 1;
		}

		if (this.tempoProva.equals(piloto.tempoProva)) {
			return 0;
		}
		return this.tempoProva.isBefore(piloto.tempoProva) ? -1 : 1;
	}

	public String toString() {
		return String.format("%d   %3s %-20s %d  %12s %d  %.3f %12s", this.posicao, this.numeroPiloto, this.nomePiloto,
				this.voltasCompletadas, this.tempoProva.toString(), this.melhorVolta, this.velocidadeMedia,
				this.melhorVoltaProva.toString());
	}

	public String toString(LocalTime tempoProvaVencedor) {
		LocalTime vencedor = LocalTime.of(this.tempoProva.getHour(), this.tempoProva.getMinute(),
				this.tempoProva.getSecond(), this.tempoProva.getNano());

		vencedor = vencedor.minusHours(tempoProvaVencedor.getHour()).minusMinutes(tempoProvaVencedor.getMinute())
				.minusSeconds(tempoProvaVencedor.getSecond()).minusNanos(tempoProvaVencedor.getNano());

		return this.toString() + " +" + vencedor;
	}
}
