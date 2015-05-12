package serviciosBusNow;

import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.jws.WebParam;
import javax.jws.WebService;



/**
 * Servicios REST
 * @author Enrique Arauz Morales y Ramón Ramírez González
 *
 */
@WebService(targetNamespace = "http://serviciosBusNow/", portName = "ServiciosPort", serviceName = "ServiciosService")
public class Servicios {

	@SuppressWarnings("unused")
	private ConfigBD configBD = null;
	
	public Servicios() throws UnknownHostException{
		configBD = new ConfigBD();
	}
	
	/**
	 * Servicio que devuelve un JSON con los autobuses que no están atascados dado un id con un bus atascado
	 * @param idBusAtascado
	 * @return
	 */
	public String obtenerBusesDisponibles(@WebParam(name = "arg0") String idBusAtascado){
		String devolucion = "";
		ArrayList<String> busesDisponibles = Operaciones.obtenerBusesDisponibles(idBusAtascado);
		for(String bus: busesDisponibles){
			devolucion += bus;
		}
		return devolucion;
	}
	

	/**
	 * Servicio que devuelve un JSON con los autobuses que no están aparcados dado un id con un bus aparcado
	 * @param idBusAparcado
	 * @return
	 */
	public String obtenerBusesNoAparcados(@WebParam(name = "arg0") String idBusAparcado){
		String devolucion = "";
		ArrayList<String> busesDisponibles = Operaciones.obtenerBusesNoAparcados(idBusAparcado);
		for(String bus: busesDisponibles){
			devolucion += bus;
		}
		return devolucion;
	}
	
}
