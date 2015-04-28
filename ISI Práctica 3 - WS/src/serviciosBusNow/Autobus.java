package serviciosBusNow;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
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
	
}
