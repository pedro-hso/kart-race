package br.com.gympass.kartrace;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class App {
	public static void main(String[] args) throws IOException {

		if (args.length != 1) {
			System.err.println("Forneça o caminho do arquivo de log da corrida");
			System.exit(1);
		}

		try {
			Corrida corrida = new Corrida(Paths.get(args[0]));

			corrida.start();

			List<Piloto> resultadoCorrida = corrida.getResultadoCorrida();
			Piloto melhorPiloto = resultadoCorrida.get(0);

			System.out.println("GRID DE CHEGADA\n");
			
			System.out.println("POS COD PILOTO               VC TEMPO PROVA  MV VelMed MV TEMPO      DIFERENÇA");
			resultadoCorrida.forEach(piloto -> System.out.println(piloto.toString(melhorPiloto.getTempoProva())));
			System.out.println("\nMELHOR VOLTA DA PROVA\n" + corrida.getMelhorVoltaProva());
			
			System.out.println("\nPOS = Posição de chegada\n"
					+ "COD = Código do piloto\n"
					+ "PILOTO = Nome do piloto\n"
					+ "VC = Voltas completadas\n"
					+ "TEMPO PROVA = Tempo total de prova\n"
					+ "MV = Número da melhor volta\n"
					+ "VelMed = Velocidade média na prova\n"
					+ "MV TEMPO = Tempo da melhor volta\n"
					+ "DIFERENÇA = Tempo que cada piloto chegou após o vencedor");
		} catch (IOException exception) {
			System.err.println("Erro ao manipular o arquivo de log da corrida");
			System.exit(2);
		}

	}
}