package path.finder.heuristicas;

import java.awt.Point;

public final class Diagonal implements Heuristica {

	public float estimativaParaDestino(Point origem, Point destino) {

		float valorDiagonal = Math.min(Math.abs(origem.x - destino.x), Math.abs(origem.y - destino.y));
		float valorReta = Math.abs(origem.x - destino.x) + Math.abs(origem.y - destino.y);

		float valorEstimado = (float) (Math.sqrt(2) * valorDiagonal + (valorReta - 2 * valorReta));

		float p = (1 / 10000);
		valorEstimado *= (1.0 + p);

		return valorEstimado;
	}

}
