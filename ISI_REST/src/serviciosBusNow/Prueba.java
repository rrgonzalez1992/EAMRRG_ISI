package serviciosBusNow;

import java.net.UnknownHostException;
import java.util.ArrayList;

public class Prueba {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws UnknownHostException {
		ConfigBD configBD = new ConfigBD();
		Operaciones operaciones = new Operaciones();
		ArrayList<Autobus> buses = Operaciones.obtenerBusesDisponibles("L1B05");
		for (Autobus bus: buses){
			System.out.println(bus.getBusID());
		}
		}
}
