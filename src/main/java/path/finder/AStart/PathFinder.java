package path.finder.AStart;

import java.awt.Point;
import java.util.ArrayList;

public interface PathFinder {
	
	ArrayList<Point> calcularCaminho(Point origem, Point destino);
}
