package serviciosBusNow;

import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;



@Path("/Servicios")
public class Servicios {

	@SuppressWarnings("unused")
	private ConfigBD configBD = null;
	
	public Servicios() throws UnknownHostException{
		configBD = new ConfigBD();
	}
	
	@GET
	@Path("/obtenerBusesDisponibles/{busAtasco}")
	@Produces("application/json")
	public ArrayList<Autobus> obtenerBusesDisponibles(@PathParam("busAtasco") String idBusAtascado){
		return Operaciones.obtenerBusesDisponibles(idBusAtascado);
	}
	
}
