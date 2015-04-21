package transformer;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;


/**
 * @author Juan Boubeta-Puig <juan.boubeta@uca.es>
 *
 */
public class ComplexEventToWSTransformer extends AbstractMessageTransformer {

	public Object transformMessage(MuleMessage message, String encoding) throws
		TransformerException {
		
		// To implement the transformation
		
		// Example 1: 
		/*
		Object[] msg = new Object[1];
		msg[0] = ((HashMap<String, Object>) message.getPayload()).get("islandName").toString();
		message.setPayload(msg);
		return message;
		*/
		
		// Example 2: 
		message.setPayload("10010");
		return message;
	}
	
}