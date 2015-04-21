package esper;

import java.util.Map;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.StatementAwareUpdateListener;


/**
 * @author Juan Boubeta-Puig <juan.boubeta@uca.es>
 *
 */
public class EsperUtils {
	
	private static EPServiceProvider epService;
			
	public static EPServiceProvider getService() {
		
		synchronized(EsperUtils.class) {
			
			if (epService == null) {
				
				Configuration config = new Configuration();										
				epService = EPServiceProviderManager.getDefaultProvider(config);
				System.out.println("Running Esper engine");
			
			}
		}
		
		return epService;
	}

	public static boolean eventTypeExists(String eventTypeName) {
		return getService().getEPAdministrator().getConfiguration().isEventTypeExists(eventTypeName);
	}
	
	public static void addNewEventType(String eventTypeName, Map<String, Object> eventTypeMap) {
		getService().getEPAdministrator().getConfiguration().addEventType(eventTypeName, eventTypeMap);
	}

	public static EPStatement addNewEventPattern(String eventPattern) throws Exception {
		return getService().getEPAdministrator().createEPL(eventPattern);
	}
	
	public static void addListener(StatementAwareUpdateListener listener, EPStatement eventPattern) throws Exception {
		eventPattern.addListener(listener);
	}
	
	public static void sendEvent(Map<String,Object> eventMap, String eventTypeName) {
		getService().getEPRuntime().sendEvent(eventMap, eventTypeName);
	}

}
