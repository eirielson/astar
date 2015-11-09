package path.finder.AStart;

import java.awt.Point;
import java.util.ArrayList;

public final class Node implements Comparable<Node> {

	private int y;
	private int x;
	public boolean isStart;
	public boolean isGoal;
	private Point coordenadas;
	private Map map;
	private int identificadorTipoTerreno;
	private float custoF;
	private Node previousNode;
	private float distanciaDoDestino;

	Node(int x, int y, int identificadorTipoTerreno, Map map) {
		this.x = x;
		this.y = y;
		this.setCoordenadas(new Point(this.x, this.y));
		this.identificadorTipoTerreno = identificadorTipoTerreno;
		this.map = map;
	}

	private void setCoordenadas(Point point) {
		this.coordenadas = point;
	}

	public ArrayList<Node> getTrechosPossiveisCaminho() {
		ArrayList<Node> neighborList = new ArrayList<Node>();
		if (!(y == 0)) {
			neighborList.add(map.getNode(new Point(x, (y - 1))));
		}
		if (!(y == 0) && !(x == (map.getLarguraMapa() - 1))) {
			neighborList.add(map.getNode(new Point(x + 1, y - 1)));
		}
		if (!(x == (map.getLarguraMapa() - 1))) {
			neighborList.add(map.getNode(new Point(x + 1, y)));
		}
		if (!(x == (map.getLarguraMapa() - 1)) && !(y == (map.getAlturaMapa() - 1))) {
			neighborList.add(map.getNode(new Point(x + 1, y + 1)));
		}
		if (!(y == (map.getAlturaMapa() - 1))) {
			neighborList.add(map.getNode(new Point(x, y + 1)));
		}
		if (!(x == 0) && !(y == (map.getAlturaMapa() - 1))) {
			neighborList.add(map.getNode(new Point(x - 1, y + 1)));
		}
		if (!(x == 0)) {
			neighborList.add(map.getNode(new Point(x - 1, y)));
		}
		if (!(x == 0) && !(y == 0)) {
			neighborList.add(map.getNode(new Point(x - 1, y - 1)));
		}
		return neighborList;
	}

	public boolean ehObstaculo() {
		return identificadorTipoTerreno == 1;
	}

	public int compareTo(Node o) {
		float thisTotalDistanceFromGoal = distanciaDoDestino() + distanciaDoInicio();
		float otherTotalDistanceFromGoal = o.distanciaDoDestino() + o.distanciaDoInicio();

		if (thisTotalDistanceFromGoal < otherTotalDistanceFromGoal) {
			return -1;
		} else if (thisTotalDistanceFromGoal > otherTotalDistanceFromGoal) {
			return 1;
		} else {
			return 0;
		}
	}

	public void setDistanciaDoInicio(float f) {
		this.custoF = f;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public float distanciaDoInicio() {
		return this.custoF;
	}

	public float distanciaDoDestino() {
		return this.distanciaDoDestino;
	}

	public void setPreviousNode(Node noCorrente) {
		this.previousNode = noCorrente;

	}

	public void setHeuristica(float estimativaParaDestino) {
		this.distanciaDoDestino = estimativaParaDestino;
	}

	public Node getPreviousNode() {
		return this.previousNode;
	}

	public Point getCoordenadas() {
		return coordenadas;
	}

	public void setStart(boolean b) {
this.isStart = b;		
	}

	public void setGoal(boolean b) {
		this.isGoal = b;
		
	}
}