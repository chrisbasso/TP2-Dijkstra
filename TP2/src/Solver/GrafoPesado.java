package Solver;

import java.util.ArrayList;
import java.util.List;

public class GrafoPesado {

	private Double[][] grafo;
	private int cantVertices;

	public GrafoPesado(int cantVertices) {

		this.cantVertices = cantVertices;
		this.grafo = new Double[cantVertices][cantVertices];

	}

	public void setArista(int i, int j, double peso) {

		grafo[i][j] = peso;

	}

	public List<Integer> vecinos(int i) {

		ArrayList<Integer> ret = new ArrayList<Integer>();

		for (int j = 0; j < cantVertices; j++) {

			if (grafo[i][j] != null) {

				ret.add(j);
			}

		}

		return ret;
	}

	public boolean isArista(int i, int j) {

		return grafo[i][j] != null;

	}

	public double getPesoArista(int i, int j) {

		if (grafo[i][j] == null) {

			throw new IllegalArgumentException("No hay arista");
		}

		return grafo[i][j];
	}

	public int cantVertices() {

		return cantVertices;

	}

}
