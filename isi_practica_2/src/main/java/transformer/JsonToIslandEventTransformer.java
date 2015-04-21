package transformer;

import org.mule.api.MuleMessage;
import org.mule.api.annotations.ContainsTransformerMethods;
import org.mule.api.transformer.TransformerException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.mule.transformer.AbstractMessageTransformer;


/**
 * @author Juan Boubeta-Puig <juan.boubeta@uca.es>
 *
 */
@ContainsTransformerMethods
public class JsonToIslandEventTransformer extends AbstractMessageTransformer
{
	@Override
	public synchronized Map<String, Object> transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {

		// LinkedHashMap will iterate in the order in which the entries were put into the map
		Map<String, Object> eventMap = new LinkedHashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = null;
		
		try {
			jsonNode = mapper.readTree(message.getPayloadAsString());
			
			// Get the common attributes for all ThingSpeak feeds.			
				
			// Event payload is a nested map
			Map<String, Object> eventPayload = new LinkedHashMap<String, Object>();
						
			eventPayload.put("islandId", jsonNode.get("channel").get("id").asInt());
			eventPayload.put("islandName", jsonNode.get("channel").get("name").getTextValue());
			eventPayload.put("timestamp", jsonNode.get("feeds").path(0).get("created_at").getTextValue());
			eventPayload.put("sensor_L0_N0", Integer.parseInt(jsonNode.get("feeds").path(0).get("field1").getTextValue()));
			eventPayload.put("sensor_L0_N1", Integer.parseInt(jsonNode.get("feeds").path(0).get("field2").getTextValue()));
			eventPayload.put("sensor_L0_N2", Integer.parseInt(jsonNode.get("feeds").path(0).get("field3").getTextValue()));
			eventPayload.put("sensor_L1_N0", Integer.parseInt(jsonNode.get("feeds").path(0).get("field4").getTextValue()));
			eventPayload.put("sensor_L1_N1", Integer.parseInt(jsonNode.get("feeds").path(0).get("field5").getTextValue()));
			eventPayload.put("sensor_L1_N2", Integer.parseInt(jsonNode.get("feeds").path(0).get("field6").getTextValue()));
			eventPayload.put("temperature", Integer.parseInt(jsonNode.get("feeds").path(0).get("field7").getTextValue()));		
			eventPayload.put("blocked", Integer.parseInt(jsonNode.get("feeds").path(0).get("field8").getTextValue()));

			eventMap.put("IslandEvent", eventPayload);
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("===IslandEvent created: " + eventMap);
		return eventMap;
	}
}