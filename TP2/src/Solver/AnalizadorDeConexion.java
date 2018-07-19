package Solver;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnalizadorDeConexion{
	
	private GrafoPesado grafo;

	public AnalizadorDeConexion(GrafoPesado grafo) {
		this.grafo = grafo;
	}

	public boolean esConexo() {
		
		return alcanzables(0).size() == grafo.cantVertices();
	}

	public Set<Integer> alcanzables(int origen) {
		
		Set<Integer> marcados = new HashSet<Integer>();
		List<Integer> pendientes = new ArrayList<Integer>();
		pendientes.add(origen);

		while (pendientes.size() > 0) {
			int i = pendientes.get(0);
			marcados.add(i);

			for (Integer vecino : grafo.vecinos(i)) {
				if (!marcados.contains(vecino) && !pendientes.contains(vecino))
					pendientes.add(vecino);
			}

			pendientes.remove(pendientes.indexOf(i));
		}

		return marcados;
	}
}
