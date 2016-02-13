package path.finder.ia;

import java.awt.Point;
import java.util.ArrayList;

public class Map {

	private ArrayList<ArrayList<Node>> map;
	private Point pontoDestino = new Point(0, 0);
	private int[][] mapaObstaculos;
	private int alturaMapa;
	private int larguraMapa;

	/*
	 * Ao desenhar o tamanho, assumo que será um array bidimensional de mesmo
	 * tamanho de altura e largura
	 */
	public Map(int[][] mapaObstaculos) {
		this.mapaObstaculos = mapaObstaculos;
		this.map = new ArrayList<ArrayList<Node>>();
		this.larguraMapa = mapaObstaculos.length;
		this.alturaMapa = mapaObstaculos.length;
		criarEstruturaDosNodes();
	}

	private void criarEstruturaDosNodes() {
		Node node;
		int valorTerreno = 0;
		for (int x = 0; x < this.larguraMapa; x++) {
			map.add(new ArrayList<Node>());
			for (int y = 0; y < this.alturaMapa; y++) {
				valorTerreno = mapaObstaculos[y][x];
				node = new Node(x, y, valorTerreno, this);
				map.get(x).add(node);
			}

		}
	}

	public Node getNode(Point localizacao) {
		return map.get(localizacao.x).get(localizacao.y);
	}

	public void marcarOrigemEm(Point coordenada) {
		this.getNode(pontoDestino).marcarComoPontoInicio(false);
		this.getNode(coordenada).marcarComoPontoInicio(true);
	}

	public void marcarDestinoEm(Point coordenada) {
		this.getNode(pontoDestino).marcarComoPontoDestino(false);
		this.getNode(coordenada).marcarComoPontoDestino(true);
		this.pontoDestino = coordenada;
	}

	public Point obterDestinoNoMapa() {
		return this.pontoDestino;
	}

	public boolean checaSePontoEhDestino(Node node) {
		return this.pontoDestino.equals(node.getCoordenadas());
	}
	
	public int getLarguraMapa() {
		return larguraMapa;
	}
	
	public int getAlturaMapa() {
		return alturaMapa;
	}

	/*
	 * Movimentos horizontais e verticais tem custo 10. Diagonais 14. O
	 * algoritimo em sua origem irá calcular a raiz quadrada para movimentos
	 * diagonais Mas, optei por usar inteiros :)
	 */
	public int obterDistanciaEntre(Node node1, Node node2) {
		if (node1.getCoordenadas().getX() == node2.getCoordenadas().getX()
				|| node1.getCoordenadas().getY() == node2.getCoordenadas().getY()) {
			return 10;
		} else {
			return 14;
		}
	}
	
	public void PrintMap(ArrayList<Point> melhorCaminho) {
		Node node;
		String path = "";
		for (int y = 0; y < this.alturaMapa; y++) {

			for (int x = 0; x < this.larguraMapa; x++) {
				node = this.getNode(new Point(x, y));

				if (node.ehObstaculo()) {
					System.out.print("\u2B1B");
				} else if (node.ehNodeInicial) {
					System.out.print("\u274E");
				} else if (node.ehNodeDestino) {
					System.out.print("\u274E");
				} else if (melhorCaminho.contains(node.getCoordenadas())) {
					System.out.print("\u274E");
					path += String.format("=> %s %s ", node.getCoordenadas().y, node.getCoordenadas().x);
				} else {
					System.out.print("\u25FD");
				}
				if (y == this.alturaMapa)
					System.out.print("_");
			}

			System.out.println();
		}
		System.out.println(path);
	}
}