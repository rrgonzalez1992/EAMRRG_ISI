package serviciosBusNow;

import java.util.ArrayList;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * Clase donde se hallan los metodos referenciados por los servicios
 * 
 * @author Enrique Arauz Morales y Ramón Ramírez González
 *
 */
public class Operaciones {

	/**
	 * Método que calcula los buses que no están en un atasco dado el ID de uno
	 * que sí lo está
	 * 
	 * @param busAtascado
	 *            ID del bus atascado
	 * @return Array con los ID de los buses disponibles
	 */
	public static ArrayList<Autobus> obtenerBusesDisponibles(String busAtascado) {
		int lineaBus = Integer.parseInt("" + busAtascado.charAt(1));
		System.out.println(lineaBus);
		int idBusAtascado = Integer.parseInt("" + busAtascado.charAt(4));
		System.out.println(idBusAtascado);
		DBCollection coleccion = ConfigBD.getDB().getCollection("eventPayload");
		ArrayList<Autobus> buses = new ArrayList<Autobus>();
		ArrayList<Autobus> comprobarBuses = null;
		for (int i = 1; i <= 5; i++) {
			if (i != idBusAtascado) {
				comprobarBuses = new ArrayList<Autobus>();
				DBObject query = new BasicDBObject();
				query.put("BusEvent.busID", "L" + lineaBus + "B0" + i);
				DBObject orden = new BasicDBObject();
				orden.put("_id", -1);
				DBCursor cursor = coleccion.find(query).sort(orden).limit(2);
				while (cursor.hasNext()) {
					Map<?, ?> tupla = cursor.next().toMap();
					BasicDBObject datosTupla = (BasicDBObject) tupla
							.get("BusEvent");
					System.out.println("BUS: " + datosTupla.getString("busID"));
					System.out.println("TIME: "
							+ datosTupla.getString("timestamp"));
					System.out.println("LATITUD: "
							+ datosTupla.getDouble("busLatitud"));
					System.out.println("LONGITUD: "
							+ datosTupla.getDouble("busLongitud"));
					comprobarBuses.add(new Autobus(datosTupla
							.getString("busID"), datosTupla
							.getDouble("busLatitud"), datosTupla
							.getDouble("busLongitud")));
				}
				Autobus bus1 = comprobarBuses.get(0), bus2 = comprobarBuses
						.get(1);
				if (!(bus1.getLatitud() == bus2.getLatitud() && bus1
						.getLongitud() == bus2.getLongitud())) {
					buses.add(comprobarBuses.get(0));
				}
			}
		}
		return buses;
	}

	/**
	 * Método que calcula los buses que no están aparcados dado el ID de uno que
	 * sí lo está
	 * 
	 * @param busAparcado
	 * @return
	 */
	public static ArrayList<Autobus> obtenerBusesNoAparcados(String busAparcado) {
		int lineaBus = Integer.parseInt("" + busAparcado.charAt(1));
		System.out.println(lineaBus);
		int idBusAtascado = Integer.parseInt("" + busAparcado.charAt(4));
		System.out.println(idBusAtascado);
		DBCollection coleccion = ConfigBD.getDB().getCollection("eventPayload");
		ArrayList<Autobus> buses = new ArrayList<Autobus>();
		for (int i = 1; i <= 5; i++) {
			if (i != idBusAtascado) {
				DBObject query = new BasicDBObject();
				query.put("BusEvent.busID", "L" + lineaBus + "B0" + i);
				DBObject orden = new BasicDBObject();
				orden.put("_id", -1);
				DBCursor cursor = coleccion.find(query).sort(orden).limit(1);
				while (cursor.hasNext()) {
					Map<?, ?> tupla = cursor.next().toMap();
					BasicDBObject datosTupla = (BasicDBObject) tupla
							.get("BusEvent");
					System.out.println("BUS: " + datosTupla.getString("busID"));
					System.out.println("TIME: "
							+ datosTupla.getString("timestamp"));
					System.out.println("LATITUD: "
							+ datosTupla.getDouble("busLatitud"));
					System.out.println("LONGITUD: "
							+ datosTupla.getDouble("busLongitud"));
					double latitud = datosTupla.getDouble("busLatitud");
					double longitud = datosTupla.getDouble("busLongitud");
					if (!(latitud == 20 && longitud == 30)) {
						buses.add(new Autobus(datosTupla.getString("busID"),
								latitud, longitud));
					}
				}
			}
		}
		return buses;
	}

	public static ArrayList<Autobus> obtenerInformacionLinea(int linea) {
		DBCollection coleccion = ConfigBD.getDB().getCollection("eventPayload");
		ArrayList<Autobus> informacion = new ArrayList<Autobus>();
		for (int i = 1; i <= 5; i++) {
			DBObject query = new BasicDBObject();
			query.put("BusEvent.busID", "L" + linea + "B0" + i);
			DBObject orden = new BasicDBObject();
			orden.put("_id", -1);
			DBCursor cursor = coleccion.find(query).sort(orden).limit(1);
			while (cursor.hasNext()) {
				Map<?, ?> tupla = cursor.next().toMap();
				BasicDBObject datosTupla = (BasicDBObject) tupla
						.get("BusEvent");
				System.out.println("BUS: " + datosTupla.getString("busID"));
				System.out
						.println("TIME: " + datosTupla.getString("timestamp"));
				System.out.println("LATITUD: "
						+ datosTupla.getDouble("busLatitud"));
				System.out.println("LONGITUD: "
						+ datosTupla.getDouble("busLongitud"));
				double latitud = datosTupla.getDouble("busLatitud");
				double longitud = datosTupla.getDouble("busLongitud");
				informacion.add(new Autobus(datosTupla.getString("busID"),
						latitud, longitud));
			}
		}
		return informacion;
	}

	public static ArrayList<Autobus> obtenerBusesAtascadosLinea(int linea){
		DBCollection coleccion = ConfigBD.getDB().getCollection("eventPayload");
		ArrayList<Autobus> buses = new ArrayList<Autobus>();
		ArrayList<Autobus> comprobarBuses = null;
		for (int i = 1; i <= 5; i++) {
				comprobarBuses = new ArrayList<Autobus>();
				DBObject query = new BasicDBObject();
				query.put("BusEvent.busID", "L" + linea + "B0" + i);
				DBObject orden = new BasicDBObject();
				orden.put("_id", -1);
				DBCursor cursor = coleccion.find(query).sort(orden).limit(2);
				while (cursor.hasNext()) {
					Map<?, ?> tupla = cursor.next().toMap();
					BasicDBObject datosTupla = (BasicDBObject) tupla
							.get("BusEvent");
					System.out.println("BUS: " + datosTupla.getString("busID"));
					System.out.println("TIME: "
							+ datosTupla.getString("timestamp"));
					System.out.println("LATITUD: "
							+ datosTupla.getDouble("busLatitud"));
					System.out.println("LONGITUD: "
							+ datosTupla.getDouble("busLongitud"));
					comprobarBuses.add(new Autobus(datosTupla
							.getString("busID"), datosTupla
							.getDouble("busLatitud"), datosTupla
							.getDouble("busLongitud")));
				}
				Autobus bus1 = comprobarBuses.get(0), bus2 = comprobarBuses
						.get(1);
				if (bus1.getLatitud() == bus2.getLatitud() && bus1
						.getLongitud() == bus2.getLongitud()) {
					buses.add(bus1);
				}
		}
		return buses;
	}
	
}
