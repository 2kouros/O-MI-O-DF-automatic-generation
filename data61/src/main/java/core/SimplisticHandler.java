package core;

import java.util.ArrayList;
import java.util.Collection;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.io.Tcp.ConnectionClosed;
import akka.io.Tcp.Received;



public class SimplisticHandler extends UntypedActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Received) {
            final String data = ((Received) msg).data().utf8String();
           
           if(data.startsWith("ID"))
           {
        	   String local=  data.replace("ID:", "");
        	 
        		   
        		   if(JenaReasoner.sparqlQuery(local))
        			   {
        			   long start=System.currentTimeMillis();
                	   for(int i=0; i<10;i++)
                	   { 
        			   
        			   
        			   Register.RegisterMsg();
        			   }
                	   long end =System.currentTimeMillis();
                	   System.out.println("Query time:" + (end-start));
        	   }
        	   
        	   
           }
           else if(data.startsWith("Data")) {
        	   String string;
          		string = data.replace("Data:", "");
          		
          		String[] values =string.split(";");
          		long start2=System.currentTimeMillis();
          		Odfmessage.main(values);  
          		long end2=System.currentTimeMillis();
          		System.out.println("Creating message time:" + (end2-start2));
          		
           }
           }
          
        else if (msg instanceof ConnectionClosed) {
        	log.info("In SimplisticHandlerActor - Connection close: " + msg);
            getContext().stop(getSelf());
        }}}
    

	
	
	