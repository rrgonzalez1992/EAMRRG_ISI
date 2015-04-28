package serviciosBusNow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class Operaciones {

	public Map<Date, Autobus> rescatarTodosDatos() {
		DBCollection coleccion = ConfigBD.getDB().getCollection("eventPayload");
		DBCursor cursor = coleccion.find();
		Map<Date, Autobus> buses = new HashMap<Date, Autobus>();
		while (cursor.hasNext()) {
			Map<?, ?> objeto = cursor.next().toMap();
			BasicDBObject object = (BasicDBObject) objeto.get("BusEvent");
			Date date = object.getDate("timestamp");
			String idBUS = object.getString("busID");
			double latitud = object.getDouble("busLatitud");
			double longitud = object.getDouble("busLongitud");
			buses.put(date, new Autobus(idBUS, latitud, longitud));
		}
		return buses;

	}

}
