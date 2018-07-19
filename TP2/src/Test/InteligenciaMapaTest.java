package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import PartesMina.Camino;
import PartesMina.Mina;
import PartesMina.POI;
import PartesMina.Tunel;
import Solver.GrafoPesado;
import Solver.Haversine;
import Solver.InteligenciaMapa;

public class InteligenciaMapaTest {

	//Mina con 4 puntos(vertices) y 4 tuneles(aristas) cuadrilatero donde el punto 3 tiene carbón y su camino minimo desde el cero es pasando por 0,1,3.
	public static Mina construyeMinaTest() {

		Mina mina = new Mina();

		POI poi0 = new POI("0", 0, 0, 0);
		POI poi1 = new POI("1", 1, 1, 0);
		POI poi2 = new POI("2", 2, -1, 0);
		POI poi3 = new POI("3", 2, 1, 1);

		mina.agregarPoi(poi0);
		mina.agregarPoi(poi1);
		mina.agregarPoi(poi2);
		mina.agregarPoi(poi3);

		Tunel tunel01 = new Tunel(poi0, poi1, Haversine.distance(poi0.getLatitud(), poi0.getLongitud(), poi1.getLatitud(), poi1.getLongitud()));
		Tunel tunel02 = new Tunel(poi0, poi2, Haversine.distance(poi0.getLatitud(), poi0.getLongitud(), poi2.getLatitud(), poi2.getLongitud()));
		Tunel tunel23 = new Tunel(poi2, poi3, Haversine.distance(poi2.getLatitud(), poi2.getLongitud(), poi3.getLatitud(), poi3.getLongitud()));
		Tunel tunel13 = new Tunel(poi1, poi3, Haversine.distance(poi1.getLatitud(), poi1.getLongitud(), poi3.getLatitud(), poi3.getLongitud()));

		mina.agregarTunel(tunel01);
		mina.agregarTunel(tunel02);
		mina.agregarTunel(tunel23);
		mina.agregarTunel(tunel13);

		return mina;
	}

	@Test
	public void construyeGrafoTest() {

		InteligenciaMapa inteligenciaMapa = new InteligenciaMapa();
		Mina mina = construyeMinaTest();

		GrafoPesado grafo = inteligenciaMapa.construyeGrafo(mina);

		assertTrue(grafo.isArista(0, 1));
		assertTrue(grafo.isArista(0, 2));
		assertTrue(grafo.isArista(2, 3));
		assertTrue(grafo.isArista(1, 3));

	}

	@Test
	public void alcanzablesTest() {

		InteligenciaMapa inteligenciaMapa = new InteligenciaMapa();
		Mina mina = construyeMinaTest();

		Set<Integer> alcanzablesDe0 = inteligenciaMapa.alcanzables(0, mina);
		Set<Integer> alcanzablesDe1 = inteligenciaMapa.alcanzables(1, mina);
		Set<Integer> alcanzablesDe2 = inteligenciaMapa.alcanzables(2, mina);
		Set<Integer> alcanzablesDe3 = inteligenciaMapa.alcanzables(3, mina);

		Assert.iguales(new int[] { 0, 1, 2, 3 }, alcanzablesDe0);
		Assert.iguales(new int[] { 1, 3 }, alcanzablesDe1);
		Assert.iguales(new int[] { 2, 3 }, alcanzablesDe2);
		Assert.iguales(new int[] { 3 }, alcanzablesDe3);

	}

	@Test
	public void caminoMinimoTest() {

		InteligenciaMapa inteligenciaMapa = new InteligenciaMapa();
		Mina mina = construyeMinaTest();

		Camino caminoMin = inteligenciaMapa.caminoMinimo(0, 3, mina);

		assertTrue(caminoMin.getTramo(0).getOrigen().getNombre().equals("1") && caminoMin.getTramo(0).getDestino().getNombre().equals("3"));
		assertTrue(caminoMin.getTramo(1).getOrigen().getNombre().equals("0") && caminoMin.getTramo(1).getDestino().getNombre().equals("1"));
		assertTrue(caminoMin.getCamino().size() == 2);

	}

	@Test
	public void irAlCarbonTest() {

		InteligenciaMapa inteligenciaMapa = new InteligenciaMapa();
		Mina mina = construyeMinaTest();

		Camino caminoAlCarbon = inteligenciaMapa.irAlCarbon(mina, 0);

		assertTrue(caminoAlCarbon.getTramo(0).getOrigen().getNombre().equals("1") && caminoAlCarbon.getTramo(0).getDestino().getNombre().equals("3"));
		assertTrue(caminoAlCarbon.getTramo(1).getOrigen().getNombre().equals("0") && caminoAlCarbon.getTramo(1).getDestino().getNombre().equals("1"));
		assertTrue(caminoAlCarbon.getCamino().size() == 2);

	}

}
