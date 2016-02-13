package path.finder.ia;

import java.awt.Point;
import java.util.ArrayList;
import path.finder.heuristicas.Heuristica;

/*
 * Algumas propriedades darei o nome em ingles para ficar fácil
 * a associação com a tecnica utilizada do algoritimo
 */
public class AStar implements PathFinder {

	private Map map;
	private Heuristica calculadorDistancia;
	private Nodes openList;
	private ArrayList<Node> closedList;

	public AStar(Map map, Heuristica calculadorDistancia) {
		this.map = map;
		this.calculadorDistancia = calculadorDistancia;

		closedList = new ArrayList<Node>();
		openList = new Nodes();
	}

	public ArrayList<Point> calcularCaminho(Point origem, Point destino) {

		this.map.marcarOrigemEm(origem);
		this.map.marcarDestinoEm(destino);

		if (EhObstaculo(destino))
			return null;

		resetParaNovaBusca(origem);
		
		while (openList.size() != 0) {

			Node noCorrente = openList.getFirst();

			if (map.checaSePontoEhDestino(noCorrente)) {
				return constroiCaminhoEncontrato(noCorrente);
			}

			openList.remove(noCorrente);
			closedList.add(noCorrente);

			for (Node vizinho : noCorrente.obterCaminhosProximos()) {

				boolean caminhoVizinhoMelhor;

				if (closedList.contains(vizinho) || vizinho.ehObstaculo())
					continue;

				float distanciaDoInicio = (noCorrente.distanciaDoInicio()
						+ map.obterDistanciaEntre(noCorrente, vizinho));

				if (!openList.contains(vizinho)) {
					openList.add(vizinho);
					caminhoVizinhoMelhor = true;
				} else if (distanciaDoInicio < noCorrente.distanciaDoInicio()) {
					caminhoVizinhoMelhor = false;
				} else {
					caminhoVizinhoMelhor = true;
				}

				if (caminhoVizinhoMelhor) {
					vizinho.setarPontoReferencia(noCorrente);
					vizinho.setDistanciaDoInicio(distanciaDoInicio);
					vizinho.setCustoEstimadoParaDestino(calculadorDistancia.estimativaParaDestino(vizinho.getCoordenadas(),
							map.getNode(destino).getCoordenadas()));
				}
			}
		}

		return null;
	}

	private void resetParaNovaBusca(Point origem) {
		map.getNode(origem).setDistanciaDoInicio(0);
		closedList.clear();
		openList.clear();
		openList.add(map.getNode(origem));
	}

	private ArrayList<Point> constroiCaminhoEncontrato(Node noCorrente) {
		ArrayList<Point> path = new ArrayList<Point>();
		while (!(noCorrente.obterPontoReferencia() == null)) {
			path.add(0, noCorrente.getCoordenadas());
			noCorrente = noCorrente.obterPontoReferencia();
		}
		return path;
	}

	private boolean EhObstaculo(Point destino) {
		return map.getNode(destino).ehObstaculo();
	}

}
