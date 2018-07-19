package Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.javatuples.Pair;

import PartesMina.Camino;
import PartesMina.Mina;
import PartesMina.POI;
import PartesMina.Tunel;

public class InteligenciaMapa {

	private Tunel[][] matrizTuneles;

	private double kmCamino;

	public GrafoPesado construyeGrafo(Mina mina) {

		matrizTuneles = new Tunel[mina.getPois().size()][mina.getPois().size()];
		GrafoPesado grafo = new GrafoPesado(mina.getPois().size());
		
		for (int i = 0; i < mina.getTuneles().size(); i++) {

			Tunel tunel = mina.getTuneles().get(i);
			int iOrigen = mina.getIndicePoi(tunel.getOrigen());
			int iDestino = mina.getIndicePoi(tunel.getDestino());

			grafo.setArista(iOrigen, iDestino, tunel.getDistancia());

			matrizTuneles[iDestino][iOrigen] = tunel;
		}

		return grafo;
	}

	public Set<Integer> alcanzables(int indicePoi, Mina mina) {

		GrafoPesado grafo = construyeGrafo(mina);

		AnalizadorDeConexion analizador = new AnalizadorDeConexion(grafo);
		Set<Integer> alcanzables = analizador.alcanzables(indicePoi);

		return alcanzables;

	}

	private Camino construyeCamino(List<Integer> listaNodos) {
		
		Camino camino = new Camino();
		
		for (int i = 0; i < listaNodos.size() - 1; i++) {
			camino.agregarTramo(matrizTuneles[listaNodos.get(i)][listaNodos.get(i + 1)]);
		}

		kmCamino = (double) Math.round(camino.getDistancia() * 100d) / 100d;

		return camino;

	}

	public Camino caminoMinimo(int s, int t, Mina mina) {

		Dijkstra camMin = new Dijkstra(construyeGrafo(mina));

		return construyeCamino(camMin.caminoMinimo(s, t));
	}

	public Camino irAlCarbon(Mina mina, int indiceOrigen) {

		Set<Integer> alcanzados = alcanzables(indiceOrigen, mina);

		ArrayList<Pair<Integer, Double>> caminosMin = new ArrayList<Pair<Integer, Double>>();

		int indPoiAlcanzado = 0;

		Camino caminoMin = new Camino();

		for (POI poi : mina.getPois()) {

			if (poi.getCantCarbon() > 0 && alcanzados.contains(mina.getIndicePoi(poi))) {

				caminoMinimo(indiceOrigen, mina.getIndicePoi(poi), mina);

				caminosMin.add(new Pair<Integer, Double>(mina.getIndicePoi(poi), getKmCamino()));

			}
		}

		if (caminosMin.isEmpty()) {
			return caminoMin;
		} else {

			double mejorCamino = caminosMin.get(0).getValue1();

			for (int i = 0; i < caminosMin.size(); i++) {

				if (mejorCamino > caminosMin.get(i).getValue1()) {

					mejorCamino = caminosMin.get(i).getValue1();
				}

			}
			for (Pair<Integer, Double> camino : caminosMin) {

				if (camino.getValue1().equals(mejorCamino)) {

					caminoMin = caminoMinimo(indiceOrigen, camino.getValue0(), mina);
					indPoiAlcanzado = camino.getValue0();
					caminoMin.setUltimoTramo(indPoiAlcanzado);

				}

			}
		}

		return caminoMin;

	}

	public double getKmCamino() {
		return kmCamino;
	}

}
