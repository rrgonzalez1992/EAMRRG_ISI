package serviciosBusNow;

import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


/**
 * Servicios REST
 * @author Enrique Arauz Morales y Ramón Ramírez González
 *
 */
@Path("/Servicios")
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
	@GET
	@Path("/obtenerBusesDisponibles/{busAtasco}")
	@Produces("application/json")
	public ArrayList<Autobus> obtenerBusesDisponibles(@PathParam("busAtasco") String idBusAtascado){
		return Operaciones.obtenerBusesDisponibles(idBusAtascado);
	}
	

	/**
	 * Servicio que devuelve un JSON con los autobuses que no están aparcados dado un id con un bus aparcado
	 * @param idBusAparcado
	 * @return
	 */
	@GET
	@Path("/obtenerBusesNoAparcados/{busAparcado}")
	@Produces("application/json")
	public ArrayList<Autobus> obtenerBusesNoAparcados(@PathParam("busAparcado") String idBusAparcado){
		return Operaciones.obtenerBusesNoAparcados(idBusAparcado);
	}
	
	@GET
	@Path("/obtenerInformacionLinea/{lineaID}")
	@Produces("application/json")
	public ArrayList<Autobus> obtenerInformacionLinea(@PathParam("lineaID") Integer linea){
		return Operaciones.obtenerInformacionLinea(linea.intValue());
	}
	
	@GET
	@Path("/obtenerBusesAtascadosLinea/{lineaID}")
	@Produces("application/json")
	public ArrayList<Autobus> obtenerBusesAtascadosLinea(@PathParam("lineaID") Integer linea){
		return Operaciones.obtenerBusesAtascadosLinea(linea.intValue());
	}
	
}
