package PartesMina;

import java.util.ArrayList;

public class Camino {

	private ArrayList<Tunel> camino;
	private int ultimoTramo;

	public Camino() {
		camino = new ArrayList<Tunel>();
	}

	public void agregarTramo(Tunel tramo) {
		camino.add(tramo);
	}

	public double getDistancia() {

		double dist = 0;

		for (Tunel tunel : camino) {

			if (tunel == null) {
				throw new NullPointerException("No existe tunel");
			} else {
				dist += tunel.getDistancia();
			}

		}

		return dist;
	}

	public int cantTramos() {
		return camino.size();
	}

	public Tunel getTramo(int i) {
		return camino.get(i);
	}

	public int getUltimoTramo() {
		return ultimoTramo;
	}

	public void setUltimoTramo(int ultimoTramo) {
		this.ultimoTramo = ultimoTramo;
	}

	public ArrayList<Tunel> getCamino() {
		return camino;
	}

	public void set_camino(ArrayList<Tunel> camino) {
		this.camino = camino;
	}

	@Override
	public String toString() {
		return "Camino [camino=" + camino + "]";
	}

}
