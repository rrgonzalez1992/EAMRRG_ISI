package esper;

import java.util.HashMap;
import java.util.Map;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleException;
import org.mule.api.lifecycle.Callable;

import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.StatementAwareUpdateListener;
import com.espertech.esper.client.EPServiceProvider;


/**
 * @author Juan Boubeta-Puig <juan.boubeta@uca.es>
 *
 */
public class AddEventPatternToEsperComponent implements Callable { 
	
	public Object onCall(final MuleEventContext eventContext) throws Exception {
		
		Object payload = eventContext.getMessage().getPayload();
		EPStatement pattern = EsperUtils.addNewEventPattern(payload.toString());
		System.out.println("\n===Event pattern added into Esper engine:\n" + payload.toString());
		
		StatementAwareUpdateListener genericListener = new StatementAwareUpdateListener() {
			
			@SuppressWarnings("deprecation")
			public void update(EventBean[] newComplexEvents, EventBean[] oldComplexEvents, 
					EPStatement detectedEventPattern, EPServiceProvider serviceProvider) {
				
				if (newComplexEvents != null) {
					try {
						
						/*
						System.out.println("=====serviceProvider:" + serviceProvider.getURI());
						System.out.println("detectedEventPattern.getName(): " + detectedEventPattern.getName());
						System.out.println("detectedEventPattern.getText(): " + detectedEventPattern.getText());
						System.out.println("detectedEventPattern.getEventType(): " + detectedEventPattern.getEventType());
						System.out.println("detectedEventPattern.getEventType().getName(): " + detectedEventPattern.getEventType().getName());
						System.out.println("detectedEventPattern.getEventType().getEventTypeId(): " + detectedEventPattern.getEventType().getEventTypeId());
						*/
						
						Map<String, Object> messageProperties = new HashMap<String, Object>();
						messageProperties.put("eventPatternName", detectedEventPattern.getEventType().getName());
						
						// Send complex event from Esper engine to ComplexEventReceptionAndDecisionMaking flow.
						// newComplexEvents[0].getUnderlying() is the payload of the complex event detected by this listener.
						// messageProperties is a map containing eventPatternName, which specifies 
						//   the event pattern that has created this complex event.	
						eventContext.getMuleContext().getClient().dispatch("ComplexEventConsumerGlobalVM", 
								newComplexEvents[0].getUnderlying(), messageProperties);
						
						System.out.println("=====newComplexEvents[0].getUnderlying():" + newComplexEvents[0].getUnderlying());
						System.out.println("=====newComplexEvents[0].getFragment(Longitud):" + newComplexEvents[0].getFragment("Autobus"));
						
					} catch (MuleException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		EsperUtils.addListener(genericListener, pattern);
				
		return payload.toString();
	}	

}
