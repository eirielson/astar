package path.finder.heuristicas;

import java.awt.Point;

public interface Heuristica {
	
	public float estimativaParaDestino(Point origem, Point destino); 
}
