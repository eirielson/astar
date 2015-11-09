package path.finder.heuristicas;

import java.awt.Point;

/**
 * Realiza cálculo da heuristica baseado na técnica manhattan.
 * Vai ser mais rápido, mas tem dificuldades de encontrar o melhor caminho por nao considerar 
 * sempre as diagonais.
 */
public class Manhattan implements Heuristica{

	public float estimativaParaDestino(Point origem, Point destino) {
	
		int diferencaX = Math.abs(origem.x - destino.x);
		int diferencaY = Math.abs(origem.y - destino.y);
		
		return 10*(diferencaX + diferencaY);
	}

}