package esper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.mule.api.annotations.param.Payload;


/**
 * @author Juan Boubeta-Puig <juan.boubeta@uca.es>
 *
 */
public class SendEventToEsperComponent {

	@SuppressWarnings("unchecked")
	public synchronized void sendEventToEsper(@Payload Map<String, Object> eventMap) { 
		
		String eventTypeName = (String) eventMap.keySet().toArray()[0];
		// System.out.println("===eventTypeName: " + eventTypeName);
		
		Map<String, Object> eventPayload = new HashMap<String, Object>();
		eventPayload = (Map<String, Object>) eventMap.get(eventTypeName);
		
		Map<String, Object> eventPayloadTypeMap = new HashMap<String, Object>();
		eventPayloadTypeMap = getEventType(eventPayload);
		// System.out.println("===eventPayloadTypeMap: " + eventPayloadTypeMap);

		if (!EsperUtils.eventTypeExists(eventTypeName)) {
			EsperUtils.addNewEventType(eventTypeName, eventPayloadTypeMap);
			System.out.println("***" + eventTypeName + " event type added to Esper engine");
		}
								
		EsperUtils.sendEvent(eventPayload, eventTypeName);
		System.out.println("\nSending event '" + eventTypeName + "' to Esper: " + eventPayload);
	}

	
	private Map<String, Object> getEventType(Map<String, Object> eventMap) {
		   
		// LinkedHashMap will iterate in the order in which the entries were put into the map
		Map<String, Object> eventTypeMap = new LinkedHashMap<String, Object>();

		for (String key : eventMap.keySet()) {
			
			Object value = eventMap.get(key);
               
			if (value instanceof Map) {               
				@SuppressWarnings("unchecked")
				Map<String, Object> nestedEventProperty = getEventType((Map<String, Object>) value);
				
				eventTypeMap.put(key, nestedEventProperty);
			} else {
				eventTypeMap.put(key, value.getClass());
			}
		}

		return eventTypeMap;
	}
}
