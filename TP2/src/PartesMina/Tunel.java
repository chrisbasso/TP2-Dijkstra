package PartesMina;

import java.io.Serializable;


public class Tunel implements Serializable{


	private static final long serialVersionUID = 2553646661629900312L;
	private POI origen;
    private POI destino;
    private double distancia;
    

    public Tunel(POI origen, POI destino, double distancia){
        
    	this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;

    }

    public Tunel()
    {

    }

	public POI getOrigen() {
		return origen;
	}

	public void setOrigen(POI origen) {
		this.origen = origen;
	}

	public POI getDestino() {
		return destino;
	}

	public void setDestino(POI destino) {
		this.destino = destino;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	@Override
	public String toString() {
		return "Tunel [origen=" + origen + ", destino=" + destino + ", distancia=" + distancia + "]";
	}

}
