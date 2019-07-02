package br.com.gympass.kartrace;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Corrida {
	private Stream<String> stream;
	private Conversor conversor;
	private Map<String, Piloto> mapaCorrida;
	private List<Piloto> resultadoCorrida;
	private Volta melhorVoltaProva;

	public Corrida(Path caminho) throws IOException {
		this.stream = Files.lines(caminho);
		this.conversor = new Conversor();
		this.mapaCorrida = new HashMap<>();
	}

	public void start() {
		stream.map(conversor::conversor).sorted().forEach(volta -> {
			if (mapaCorrida.get(volta.getNumeroPiloto()) == null) {
				mapaCorrida.put(volta.getNumeroPiloto(), new Piloto(volta));
			} else {
				Piloto piloto = mapaCorrida.get(volta.getNumeroPiloto());
				piloto.setVoltasCompletadas(volta.getNumeroVolta());
				piloto.setTempoProva(piloto.getTempoProva().plusMinutes(volta.getTempoVolta().getMinute())
						.plusSeconds(volta.getTempoVolta().getSecond()).plusNanos(volta.getTempoVolta().getNano()));

				if (volta.getTempoVolta().isBefore(piloto.getMelhorVoltaProva())) {
					piloto.setMelhorVolta(volta.getNumeroVolta());
					piloto.setMelhorVoltaProva(volta.getTempoVolta());
				}

				if (this.melhorVoltaProva == null || volta.getTempoVolta().isBefore(melhorVoltaProva.getTempoVolta())) {
					this.melhorVoltaProva = volta;
				}

				float tempo = volta.getVelocidadeMediaVolta() * volta.getTempoVolta().toNanoOfDay();
				piloto.setVelocidadeMedia(
						(float) piloto.getVoltasCompletadas() * tempo / (float) piloto.getTempoProva().toNanoOfDay());
			}
		});

		AtomicInteger posicao = new AtomicInteger();

		this.resultadoCorrida = mapaCorrida.values().stream().sorted().map((Piloto piloto) -> {
			piloto.setPosicao(posicao.incrementAndGet());
			return piloto;
		}).collect(Collectors.toList());
	}

	public List<Piloto> getResultadoCorrida() {
		return this.resultadoCorrida;
	}

	public Volta getMelhorVoltaProva() {
		return this.melhorVoltaProva;
	}
}