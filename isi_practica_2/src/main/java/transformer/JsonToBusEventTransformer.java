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
public class JsonToBusEventTransformer extends AbstractMessageTransformer
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
						
			eventPayload.put("channelId", jsonNode.get("channel").get("id").asInt());
			eventPayload.put("busName", jsonNode.get("channel").get("name").getTextValue());
			eventPayload.put("timestamp", jsonNode.get("feeds").path(0).get("created_at").getTextValue());
			eventPayload.put("busID", jsonNode.get("feeds").path(0).get("field1").getTextValue());
			eventPayload.put("busLatitud", Double.parseDouble(jsonNode.get("feeds").path(0).get("field2").getTextValue()));
			eventPayload.put("busLongitud", Double.parseDouble(jsonNode.get("feeds").path(0).get("field3").getTextValue()));
			eventMap.put("BusEvent", eventPayload);
			
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

		System.out.println("===BusEvent created: " + eventMap);
		return eventMap;
	}
}