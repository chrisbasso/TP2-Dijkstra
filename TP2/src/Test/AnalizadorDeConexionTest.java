package Test;

import java.util.Set;

import org.junit.jupiter.api.Test;

import Solver.AnalizadorDeConexion;
import Solver.GrafoPesado;

public class AnalizadorDeConexionTest {
	@Test
	public void conexoTest() {
		GrafoPesado grafo = diamante();

		AnalizadorDeConexion analizador = new AnalizadorDeConexion(grafo);
		Set<Integer> alcanzables = analizador.alcanzables(0);

		Assert.iguales(new int[] { 0, 1, 2, 3 }, alcanzables);
	}

	@Test
	public void primeraComponenteTest() {
		GrafoPesado grafo = diamanteMasTriangulo();

		AnalizadorDeConexion analizador = new AnalizadorDeConexion(grafo);
		Set<Integer> alcanzables = analizador.alcanzables(0);

		Assert.iguales(new int[] { 0, 1, 2, 3 }, alcanzables);
	}

	@Test
	public void segundaComponenteTest() {
		GrafoPesado grafo = diamanteMasTriangulo();

		AnalizadorDeConexion analizador = new AnalizadorDeConexion(grafo);
		Set<Integer> alcanzables = analizador.alcanzables(4);

		Assert.iguales(new int[] { 4, 5, 6 }, alcanzables);
	}

	@Test
	public void aisladoTest() {
		GrafoPesado grafo = diamanteMasAislado();

		AnalizadorDeConexion analizador = new AnalizadorDeConexion(grafo);
		Set<Integer> alcanzables = analizador.alcanzables(4);

		Assert.iguales(new int[] { 4 }, alcanzables);
	}

	private GrafoPesado diamanteMasTriangulo() {
		GrafoPesado grafo = new GrafoPesado(7);
		grafo.setArista(0, 1, 1);
		grafo.setArista(0, 2, 1);
		grafo.setArista(0, 3, 1);
		grafo.setArista(1, 2, 1);
		grafo.setArista(2, 3, 1);
		grafo.setArista(4, 5, 1);
		grafo.setArista(6, 5, 1);
		grafo.setArista(4, 6, 1);
		return grafo;
	}

	private GrafoPesado diamante() {
		GrafoPesado grafo = new GrafoPesado(4);
		grafo.setArista(0, 1, 1);
		grafo.setArista(0, 2, 1);
		grafo.setArista(0, 3, 1);
		grafo.setArista(1, 2, 1);
		grafo.setArista(2, 3, 1);
		return grafo;
	}

	private GrafoPesado diamanteMasAislado() {
		GrafoPesado grafo = new GrafoPesado(5);
		grafo.setArista(0, 1, 1);
		grafo.setArista(0, 2, 1);
		grafo.setArista(0, 3, 1);
		grafo.setArista(1, 2, 1);
		grafo.setArista(2, 3, 1);
		return grafo;
	}
}
