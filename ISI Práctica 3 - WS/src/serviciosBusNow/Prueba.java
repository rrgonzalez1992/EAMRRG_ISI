package serviciosBusNow;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class Prueba {

	public static void main(String[] args) throws UnknownHostException {
		ConfigBD configBD = new ConfigBD();
		DBCollection coleccion = configBD.rescatarDatos();
		DBCursor cursor = coleccion.find();
		while (cursor.hasNext()){
		Map<?, ?> objeto = cursor.next().toMap();
		BasicDBObject object = (BasicDBObject) objeto.get("BusEvent");
		System.out.println(object.toString());
		String idBUS = object.getString("busID");
		double latitud = object.getDouble("busLatitud");
		double longitud = object.getDouble("busLongitud");
		Date date = (Date) object.getDa
		Autobus bus = new Autobus(idBUS, latitud, longitud);
		}
	}

}
