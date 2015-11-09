package path.finder.AStart;

import java.awt.Point;
import java.util.ArrayList;

public class Map {
	
	private int[][] mapaObstaculos;
	private ArrayList<ArrayList<Node>> map;
	private int mapHeight;
	private int mapWith;
	private Point pontoOrigem = new Point(0,0);
	private Point pontoDestino = new Point(0,0);

	/*
	 * Ao desenhar o tamanho, assumo que será um array bidimensional de mesmo tamanho de altura e largura
	 */
	public Map(int[][] mapaObstaculos){
		this.mapaObstaculos = mapaObstaculos;
		this.mapWith = 10;
		this.mapHeight = 10;
		criarEstruturaDosNodes();
	}

	private void criarEstruturaDosNodes() {
		Node node;
		
		map = new ArrayList<ArrayList<Node>>();
		
		for (int x = 0; x < this.mapWith; x++) {
			map.add(new ArrayList<Node>());
			for (int y = 0; y < this.mapHeight; y++) {
				node = new Node(x,y, mapaObstaculos[y][x], this);
				map.get(x).add(node);
			}
			
		}
	}
	
	public int getLarguraMapa() {
		return mapWith;
	}
	
	public int getAlturaMapa() {
		return mapHeight;
	}

	public Node getNode(Point localizacao){
		return map.get(localizacao.x).get(localizacao.y);
	}
	
	public void setPontoOrigem(Point coordenada) {
		map.get(pontoOrigem.x).get(pontoOrigem.y).setStart(false);
		map.get(coordenada.x).get(coordenada.y).setStart(true);
		this.pontoOrigem = coordenada;
	}
	
	public void setPontoDestino(Point coordenada) {
		map.get(pontoDestino.x).get(pontoDestino.y).setGoal(false);
		map.get(coordenada.x).get(coordenada.y).setGoal(true);
		this.pontoDestino = coordenada;
	}

	public Point getGoalPoint() {
		return this.pontoDestino;
	}
	
	public boolean nodeEhDestino(Node node) {
		return this.pontoDestino.x == node.getX() && this.pontoDestino.y == node.getY();
	}

	public float getDistanceBetween(Node node1, Node node2) {
		//if the nodes are on top or next to each other, return 1
		if (node1.getX() == node2.getX() || node1.getY() == node2.getY()){
			return 1;//*(mapHeight+mapWith);
		} else { //if they are diagonal to each other return diagonal distance: sqrt(1^2+1^2)
			return (float) 1.9;//*(mapHeight+mapWith);
		}
	}
	
	
}
