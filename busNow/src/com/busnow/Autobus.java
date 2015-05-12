package com.busnow;

public class Autobus {

private String busID;
private double latitud;
private double longitud;

public Autobus(){
	
}

public Autobus(String busID, double latitud, double longitud) {
	super();
	this.busID = busID;
	this.latitud = latitud;
	this.longitud = longitud;
}

public String getBusID() {
	return busID;
}

public double getLatitud() {
	return latitud;
}

public double getLongitud() {
	return longitud;
}

public String toString(){
	return "Bus " + busID + ", Latitud: " + latitud + ", Longitud: " + longitud;
}
	
}
