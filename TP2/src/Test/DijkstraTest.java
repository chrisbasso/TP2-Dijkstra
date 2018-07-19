package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import Solver.Dijkstra;
import Solver.GrafoPesado;

public class DijkstraTest {
	
	public static GrafoPesado construyeGrafoTest() {

		// diamante
		GrafoPesado grafo = new GrafoPesado(4);

		grafo.setArista(0, 1, 1);
		grafo.setArista(0, 2, 1);
		grafo.setArista(1, 3, 5);
		grafo.setArista(2, 3, 10);

		return grafo;
	}

	@Test
	public void dijkstraTest() {
		
		GrafoPesado grafo = construyeGrafoTest();
		Dijkstra caminoMin = new Dijkstra(grafo);

		List<Integer> camino = caminoMin.caminoMinimo(0, 3);

		assertTrue(camino.contains(0));
		assertTrue(camino.contains(1));
		assertTrue(camino.contains(3));
		assertTrue(camino.size() == 3);
		

	}

}
