package PartesMina;

import java.io.Serializable;

public class POI implements Serializable {

	private static final long serialVersionUID = 4590882659067834314L;
	private String nombre;
	private double latitud;
	private double longitud;
	private double cantCarbon;

	public POI(String nombre, double latitud, double longitud, double cantCarbon) {
		
		this.nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
		this.cantCarbon = cantCarbon;
	}

	public POI() {

	}
	

	public void setNombre(String nombre) {
		if (nombre == "") {
			throw new IllegalArgumentException("Debe completar el nombre del POI");
		}

		this.nombre = nombre;
	}

	public void setLatitud(double latitud) {
		
		if (latitud > 180 || latitud < -180) {
			
			throw new IllegalArgumentException("Latitud invalida");
		}

		this.latitud = latitud;
	}

	public void setLongitud(double longitud) {
		if (longitud > 180 || longitud < -180) {
			
			throw new IllegalArgumentException("Longitud invalida");
		}

		this.longitud = longitud;
	}

	public String getNombre() {
		return nombre;
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}
	

	public double getCantCarbon() {
		return cantCarbon;
	}

	public void setCantCarbon(double cantCarbon) {
		this.cantCarbon = cantCarbon;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + " Latitud: " + latitud + " longitud: " + longitud;
	}
}
