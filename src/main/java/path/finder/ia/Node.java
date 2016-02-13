package path.finder.ia;

import java.awt.Point;
import java.util.ArrayList;

public final class Node implements Comparable<Node> {
	public boolean ehNodeInicial;
	public boolean ehNodeDestino;
	private float custoF;
	private float distanciaDoDestino;
	private int identificadorTipoTerreno;
	private Point coordenadas;
	private Node pontoAnteriorReferencia;
	private ArrayList<Node> caminhosConhecidos;
	private Map map;

	Node(int x, int y, int identificadorTipoTerreno, Map map) {
		this.setCoordenadas(new Point(x, y));
		this.identificadorTipoTerreno = identificadorTipoTerreno;
		this.caminhosConhecidos = new ArrayList<Node>();
		this.map = map;
	}

	private void setCoordenadas(Point point) {
		this.coordenadas = point;
	}

	/*
	 * Obtem os caminhos possiveis do Nó. 
	 * Gera caminhos para os pontos cardeais a partir do ponto atual. 
	 */
	public ArrayList<Node> obterCaminhosProximos() {
		int x = this.coordenadas.x;
		int y = this.coordenadas.y;
		

		if (!(y == 0)) {
			AdicionarCaminho(new Point(x, (y - 1)));
		}
		if (!(y == 0) && !(x == (map.getLarguraMapa() - 1))) {
			AdicionarCaminho(new Point(x + 1, y - 1));
		}
		if (!(x == (map.getLarguraMapa() - 1))) {
			AdicionarCaminho(new Point(x + 1, y));
		}
		if (!(x == (map.getLarguraMapa() - 1)) && !(y == (map.getAlturaMapa() - 1))) {
			AdicionarCaminho(new Point(x + 1, y + 1));
		}
		if (!(y == (map.getAlturaMapa() - 1))) {
			AdicionarCaminho(new Point(x, y + 1));
		}
		if (!(x == 0) && !(y == (map.getAlturaMapa() - 1))) {
			AdicionarCaminho(new Point(x - 1, y + 1));
		}
		if (!(x == 0)) {
			AdicionarCaminho(new Point(x - 1, y));
		}
		if (!(x == 0) && !(y == 0)) {
			AdicionarCaminho(new Point(x - 1, y - 1));
		}
		return caminhosConhecidos;
	}

	private void AdicionarCaminho(Point coordenadasNovoVizinho){
		caminhosConhecidos.add(map.getNode(coordenadasNovoVizinho));
	}
	
	public boolean ehObstaculo() {
		return identificadorTipoTerreno == 1;
	}

	public void setDistanciaDoInicio(float f) {
		this.custoF = f;
	}

	public float distanciaDoInicio() {
		return this.custoF;
	}

	public float distanciaDoDestino() {
		return this.distanciaDoDestino;
	}

	public void setarPontoReferencia(Node noCorrente) {
		this.pontoAnteriorReferencia = noCorrente;

	}

	public Node obterPontoReferencia() {
		return this.pontoAnteriorReferencia;
	}

	public void setCustoEstimadoParaDestino(float estimativaParaDestino) {
		this.distanciaDoDestino = estimativaParaDestino;
	}

	public Point getCoordenadas() {
		return coordenadas;
	}

	public void marcarComoPontoInicio(boolean b) {
		this.ehNodeInicial = b;
	}

	public void marcarComoPontoDestino(boolean b) {
		this.ehNodeDestino = b;
	}
	
	public int compareTo(Node o) {
		float distanciaDesteNodeParaDestino = distanciaDoDestino() + distanciaDoInicio();
		float distanciaDaReferenciaParaDestino = o.distanciaDoDestino() + o.distanciaDoInicio();

		if (distanciaDesteNodeParaDestino < distanciaDaReferenciaParaDestino) {
			return -1;
		} else if (distanciaDesteNodeParaDestino > distanciaDaReferenciaParaDestino) {
			return 1;
		} else {
			return 0;
		}
	}
}