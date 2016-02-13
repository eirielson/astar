package path.finder.ia;

import java.awt.Point;
import java.util.ArrayList;

import path.finder.helpers.Constantes;
import path.finder.heuristicas.Heuristica;
import path.finder.heuristicas.Manhattan;

public class App {
	public static void main(String[] args) {

		Map mapa = new Map(Constantes.mapaObstaculos);

		Heuristica tecnicaHeuristica = new Manhattan();

		AStar pathFinder = new AStar(mapa, tecnicaHeuristica);
		ArrayList<Point> path = pathFinder.calcularCaminho(new Point(5, 2), new Point(5, 8));
		mapa.PrintMap(path);
	}
}