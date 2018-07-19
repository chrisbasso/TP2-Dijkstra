package Solver;

import java.awt.Color;

import java.util.ArrayList;
import java.util.Iterator;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;


import InterfazGrafica.Mapa;
import PartesMina.Camino;
import PartesMina.Mina;
import PartesMina.POI;
import PartesMina.Tunel;

public class CapaVectorial {

	public void dibujaPoi(POI poi, Mapa mapa) {

		Coordinate coordenadas = new Coordinate(poi.getLatitud(), poi.getLongitud());
		MapMarker marker = new MapMarkerDot(poi.getNombre(), coordenadas);

		mapa.addMapMarker(marker);

		if (poi.getCantCarbon() > 0) {
			marker.getStyle().setBackColor(Color.BLACK);
		}
		
		//Si se quiere agregar una imagen en vez del marker tipo circulo:

		// ImageIcon icon = new ImageIcon("imagenes\\con_carbon.png");
		// ImageIcon icon2 = new ImageIcon("imagenes\\sin_carbon.png");
		//
		//
		// if (poi.getCantCarbon()>0) {
		//
		// IconMarker carrito = new IconMarker(coordenadas,
		// icon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		// mapa.addMapMarker(carrito);
		// }else {
		//
		//
		// IconMarker carrito = new IconMarker(coordenadas,
		// icon2.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		// mapa.addMapMarker(carrito);
		// }
		//

	}

	public void dibujarTodosLosPois(Mina mina, Mapa mapa) {

		for (POI poi : mina.getPois()) {

			dibujaPoi(poi, mapa);

		}

	}

	//Dibuja una flecha en direccion al destino del tunel
	public void dibujaTunel(Tunel tunel, Mapa mapa, Color color) {

		ArrayList<Coordinate> coordenadas = new ArrayList<Coordinate>();

		// Toma las coordenadas de los extremos del tunel (Origen y Destino)
		double latO = tunel.getOrigen().getLatitud();
		double longO = tunel.getOrigen().getLongitud();
		double latD = tunel.getDestino().getLatitud();
		double longD = tunel.getDestino().getLongitud();

		// Agrega las coordenadas de los extremos
		coordenadas.add(new Coordinate(latO, longO));
		coordenadas.add(new Coordinate(latD, longD));

		// Angulo de la pendiente con trigonometria usando ArcoTangente
		double pendiente = Math.toDegrees((Math.atan(((latD - latO) / (longD - longO)))));

		// Correccion de grados para cuadrante 2
		if (pendiente <= 0) {
			pendiente += 180;
		}

		// Correccion de grados para cuadrante 3 y 4
		if (latD < latO) {
			pendiente += 180;
		}

		// Convierte a radianes la pendiente mas el valor del giro de la flecha
		double rotacionFlecha = 170; //cantidad de giro en grados de la flecha con respecto a la pendiente de la recta
		double radians = Math.toRadians(pendiente + rotacionFlecha); 

		double tamañoFlecha = 0.003;
		double radRotacionAristaFlecha = 0.37;

		// Agrega las coordenadas para dar forma a la flecha
		coordenadas.add(new Coordinate(latD + (tamañoFlecha * Math.sin(radians)), longD + (tamañoFlecha * Math.cos(radians))));
		coordenadas.add(new Coordinate(latD + (tamañoFlecha * Math.sin(radians + radRotacionAristaFlecha)), longD + (tamañoFlecha * Math.cos(radians + radRotacionAristaFlecha))));
		coordenadas.add(new Coordinate(latD, longD));

		MapPolygon poligono = new MapPolygonImpl(coordenadas);
		poligono.getStyle().setColor(color);
		poligono.getStyle().setBackColor(color);

		mapa.addMapPolygon(poligono);

	}

	public void dibujarTodosLosTuneles(Mina tuneles, Mapa mapa, Color color) {

		for (Tunel tunel : tuneles.getTuneles()) {

			dibujaTunel(tunel, mapa, color);

		}

	}

	public void dibujarCaminoMinimo(Camino camino, Mapa mapa) {

		Color color = Color.RED;

		//Elimina el camino minimo marcado anteriormente, para actualizar
		Iterator<MapPolygon> it = mapa.getMapPolygonList().iterator();
		while (it.hasNext()) {
			MapPolygon elem = it.next();
			if (elem.getColor().equals(color)) {
				it.remove();
			}
		}

		for (Tunel tunel : camino.getCamino()) {

			dibujaTunel(tunel, mapa, color);

		}

	}

}
