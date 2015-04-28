package serviciosBusNow;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class ConfigBD {

	private static MongoCredential credential = null;
	private static MongoClient mongoClient = null;
	private static DB db = null;
	
	public static DB getDB() {
		return db;
	}

	public ConfigBD() throws UnknownHostException{
		credential = MongoCredential.createCredential("EAMRRG", "busNow", "EAMRRG".toCharArray());
		mongoClient = new MongoClient(new ServerAddress("localhost"), Arrays.asList(credential));
		db = mongoClient.getDB("busNow");
	}
	
	public DBCollection rescatarDatos(){
		return db.getCollection("eventPayload");
	}
	
}
