package path.finder.heuristicas;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class ManhattanTest {

	private Manhattan manhattan;
	private Point destino;

	@Before
	public void Setup() {
		this.manhattan = new Manhattan();
		this.destino = new Point(15, 2);
	}

	@Test
	public void Deve_retornar_heuristica_zero_quando_estiver_no_ponto_destino() {
		Point origem = destino;

		double estimativaCalculada = manhattan.estimativaParaDestino(origem, destino);

		Assert.assertEquals(0.0, estimativaCalculada);
	}

	@Test
	public void Deve_retornar_estimativa_20_quando_estiver_2_pontos_do_destino_na_horizontal() {
		Point origem = new Point(13, 2);

		double estimativaCalculada = manhattan.estimativaParaDestino(origem, destino);

		Assert.assertEquals(20.0, estimativaCalculada);

	}
}
