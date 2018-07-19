package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Solver.GrafoPesado;

public class GrafoPesadoTest {

	public static GrafoPesado construyeGrafoTest() {

		// diamante
		GrafoPesado grafo = new GrafoPesado(4);

		grafo.setArista(0, 1, 0);
		grafo.setArista(0, 2, 0);
		grafo.setArista(1, 3, 0);
		grafo.setArista(2, 3, 10);

		return grafo;
	}

	@Test
	public void consultarArista() {

		GrafoPesado grafo = construyeGrafoTest();

		
		assertTrue(grafo.isArista(0, 1));
		assertTrue(grafo.isArista(0, 2));
		assertTrue(grafo.isArista(1, 3));
		assertTrue(grafo.isArista(2, 3));


	}
	//checkea que el grafo sea dirigido
	@Test
	public void consultarAristaInversa() {

		GrafoPesado grafo = construyeGrafoTest();

		
		assertFalse(grafo.isArista(1, 0));
		assertFalse(grafo.isArista(2, 0));
		assertFalse(grafo.isArista(3, 1));
		assertFalse(grafo.isArista(3, 2));


	}
	@Test
	public void consultarVecinos() {

		GrafoPesado grafo = construyeGrafoTest();

		assertTrue(grafo.vecinos(0).size() == 2);
		assertTrue(grafo.vecinos(1).size() == 1);
		assertTrue(grafo.vecinos(2).size() == 1);
		assertTrue(grafo.vecinos(3).size() == 0);
	}

	@Test
	public void consultarPeso() {
		
		GrafoPesado grafo = construyeGrafoTest();
		
		assertEquals(10, grafo.getPesoArista(2, 3));
	}

}
