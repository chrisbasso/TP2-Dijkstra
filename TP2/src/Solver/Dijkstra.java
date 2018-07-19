package Solver;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {

	private GrafoPesado grafo;
	private int vertices;
	private double[] minimosTentativos;
	private boolean[] zonaSegura;
	private int[] verticesPadre;
	private int nodoActual;
	private ArrayList<Integer> cola;

	public Dijkstra(GrafoPesado grafo) {
		this.grafo = grafo;
		this.vertices = grafo.cantVertices();
	}

	public List<Integer> caminoMinimo(int s, int t) {
		inicializaVariables();
		minimosTentativos[s] = 0;
		cola.add(s);

		while (!cola.isEmpty()) {
			nodoActual = cola.get(0);
			cola.remove(0);
			zonaSegura[nodoActual] = true;
			propaga(nodoActual);
			nodoActual = minimoCandidato(cola);
		}
		return camino(s, t);
	}

	private void inicializaVariables() {
		minimosTentativos = new double[vertices];
		for (int i = 0; i < vertices; i++) {
			minimosTentativos[i] = Double.MAX_VALUE;
		}

		zonaSegura = new boolean[vertices];
		verticesPadre = new int[vertices];
		cola = new ArrayList<Integer>();
	}

	private void propaga(int nodoActual) {
		for (int i : grafo.vecinos(nodoActual)) {

			if (!zonaSegura[i]) {

				if (minimosTentativos[nodoActual] + grafo.getPesoArista(nodoActual, i) < minimosTentativos[i]) {
					minimosTentativos[i] = minimosTentativos[nodoActual] + grafo.getPesoArista(nodoActual, i);
					verticesPadre[i] = nodoActual;
					cola.add(i);
				}
			}
		}

	}

	private int minimoCandidato(ArrayList<Integer> cola) {

		int ret = 0;
		double min = Double.MAX_VALUE;

		for (Integer i : cola) {

			for (int j : grafo.vecinos(i)) {

				if (!zonaSegura[j]) {

					if (grafo.getPesoArista(i, j) < min) {

						min = grafo.getPesoArista(i, j);
						ret = j;

					}
				}

			}

		}

		return ret;
	}

	private List<Integer> camino(int s, int t) {

		List<Integer> ret = new ArrayList<Integer>();

		int siguienteNodo = t;

		while (siguienteNodo != s) {

			ret.add(siguienteNodo);
			siguienteNodo = verticesPadre[siguienteNodo];

		}

		ret.add(s);

		return ret;
	}

}
