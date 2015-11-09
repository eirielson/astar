package path.finder.AStart;

import java.awt.Point;
import java.util.ArrayList;

import path.finder.helpers.Constantes;
import path.finder.heuristicas.Heuristica;
import path.finder.heuristicas.Manhattan;

public class App {
    public static void main(String[] args){

       Map mapa = new Map(Constantes.mapaObstaculos);
       
       Heuristica tecnicaHeuristica = new Manhattan();

       AStar pathFinder = new AStar(mapa, tecnicaHeuristica);
       ArrayList<Point> path=  pathFinder.calcularCaminho(new Point(5,2), new Point(5,8));
       PrintMap(mapa, path);
    }
    
    public static void PrintMap(Map map, ArrayList<Point> shortestPath) {
		Node node;
		String path = "";
		for(int y=0; y<map.getAlturaMapa(); y++) {

			for(int x=0; x<map.getLarguraMapa(); x++) {
				node = map.getNode(new Point(x,y));
				
				
				if(node.ehObstaculo()) {
					System.out.print("\u2B1B");
				} else if(node.isStart) {
					System.out.print("\u274E");
				} else if(node.isGoal) {
					System.out.print("\u274E");
				} else if (shortestPath.contains(new Point(node.getCoordenadas().x, node.getCoordenadas().y))) {
					System.out.print("\u274E");
					path += String.format("=> %s %s ", node.getCoordenadas().y, node.getCoordenadas().x);
				} else {
					System.out.print("\u25FD");
				}
				if(y==map.getAlturaMapa())
					System.out.print("_");
			}

			System.out.println();
		}
		System.out.println(path);
	}
}
