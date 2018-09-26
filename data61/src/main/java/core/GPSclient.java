package core;


import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import de.taimos.gpsd4java.api.ObjectListener;
import de.taimos.gpsd4java.backend.GPSdEndpoint;
import de.taimos.gpsd4java.backend.ResultParser;
import de.taimos.gpsd4java.types.TPVObject;

public class GPSclient {
	
	static final Logger log = LoggerFactory.getLogger(GPSclient.class);
	public static Map<String,String> map=new HashMap<>();
 
	/**
	 * @param args
	 *            the args
	 */
	public static void main() {
		
		try
		{
			String host = "192.168.1.2"; // Base station socket for GPS data
			int port = 2947;
			final GPSdEndpoint ep = new GPSdEndpoint(host, port, new ResultParser());
			ep.addListener(new ObjectListener() {
				
				@Override
				public void handleTPV(final TPVObject tpv) {
					GPSclient.log.info("TPV: {}", tpv);
					
						    	map.put("latitude",String.valueOf(tpv.getLatitude()));
								 map.put("longitude",String.valueOf(tpv.getLongitude()));
								 map.put("altitude",String.valueOf(tpv.getAltitude()));
								 		    }});
			
			ep.start();
			
			GPSclient.log.info("Version: {}", ep.version());
			
			GPSclient.log.info("Watch: {}", ep.watch(true, true));
			
			GPSclient.log.info("Poll: {}", ep.poll());
        	 
		} 
		
		   catch (final Exception e) 
		
		{
			GPSclient.log.error("Problem encountered", e);
		}
	}
}
	
	
