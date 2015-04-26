package simulador;

import java.util.Random;

public class Autobus extends Thread{
	private String busId;
	private double latitud;
	private double longitud;
	private int linea;
	private static final double inicioX = 30;
	private static final double inicioY = 20;
	private static final double finY1 = 60;
	private static final double finX2 = 70;

	public Autobus(String id, int linea) {
		this.busId = id;
		this.linea = linea;
		longitud = inicioX;
		latitud = inicioY;
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public String getBusId() {
		return busId;
	}	
	public void atasco() throws InterruptedException{
		System.out.println("El bus " + busId + " está en un atasco");
		System.out.println("COMIENZA ATASCO "+busId);
		Thread.sleep(360000);
		System.out.println("TERMINA ATASCO "+busId);
	}
	
	public void aparcar() throws InterruptedException{
		System.out.println("El bus " + busId + " ha aparcado por finalizar su servicio");
		System.out.println("DETENCIÓN DE SERVICIO "+busId);
		Thread.sleep(500000);
		System.out.println("REANUDA SERVICIO "+busId);
	}

	public void run() {
		boolean volviendo = false;
		while(true){
			int atascoRandom = new Random().nextInt(10000);
			if(atascoRandom == 57){
				try {
					atasco();
				} catch (InterruptedException e) {}
			} else {
				if(linea == 1){
					if(latitud < finY1){
						if(!volviendo)
							latitud +=1;
						else
							latitud -=1;
					}
					else if(latitud == finY1){
						volviendo = true;
						latitud -= 1;
					} else if(latitud == inicioY){
						if(volviendo){
							try {
								aparcar();
							} catch (InterruptedException e) {}
						}
						volviendo = false;
						latitud += 1;
					}
				} else if(linea == 2){
					if(longitud < finX2){
						if(!volviendo)
							longitud +=1;
						else
							longitud -=1;
					}
					else if(longitud == finX2){
						volviendo = true;
						longitud -=1;
					} else if(longitud == inicioX){
						if(volviendo){
							try {
								aparcar();
							} catch (InterruptedException e) {}
						}
						volviendo = false;
						longitud += 1;
					}
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
			}
		}
	}

	public String toString() {
		return "Bus " + busId + "\nLatitud: " + latitud + "\nLongitud: "
				+ longitud;

	}
}

