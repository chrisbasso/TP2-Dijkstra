package PartesMina;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Mina implements Serializable {

	private static final long serialVersionUID = 5820673528275671961L;

	private ArrayList<Tunel> tuneles;
	private ArrayList<POI> pois;
	

	public Mina() {

		this.tuneles = new ArrayList<Tunel>();
		this.pois = new ArrayList<POI>();

	}
	public void agregarPoi(POI poi) {
		pois.add(poi);
	}

	public void agregarTunel(Tunel tunel) {
		tuneles.add(tunel);
	}

	public void eliminarPoiYtunelesAnidados(int i) {

		Iterator<Tunel> it = tuneles.iterator();
		while (it.hasNext()) {
			Tunel elem = it.next();
			if (elem.getOrigen().getNombre().equals(pois.get(i).getNombre()) || elem.getDestino().getNombre().equals(pois.get(i).getNombre())) {
				it.remove();
			}
		}

		pois.remove(i);

	}

	public void eliminarTunel(int i) {
		tuneles.remove(i);
	}
	
	public int getIndicePoi(POI poi) {
		for (int i = 0; i < pois.size(); i++) {
			if (pois.get(i).equals(poi)) {
				return i;
			}
				
		}
			
		throw new IllegalArgumentException("No existe el POI en el mapa");
	}

	public ArrayList<Tunel> getTuneles() {
		return tuneles;
	}

	public void setTuneles(ArrayList<Tunel> tuneles) {
		this.tuneles = tuneles;
	}

	public ArrayList<POI> getPois() {
		return pois;
	}

	public void setPois(ArrayList<POI> pois) {
		this.pois = pois;
	}

	@Override
	public String toString() {
		return "Mina [tuneles=" + tuneles + ", pois=" + pois + "]";
	}

}
