package InterfazGrafica;

import org.openstreetmap.gui.jmapviewer.JMapViewer;


public class Mapa extends JMapViewer {


	private static final long serialVersionUID = 1L;
	private final double LATITUD_INICIAL = -51.5357252; // Coordenadas de la localidad de Rio Turbio, donde realmente hay una mina de carbón. 
	private final double LONGITUD_INICIAL = -72.3005969;
	


	public Mapa() {

		this.setDisplayPositionByLatLon(LATITUD_INICIAL, LONGITUD_INICIAL, 13);
		
	}
	


	
}
