package core;

import java.io.IOException;
import java.net.InetSocketAddress;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import core.Server;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class Main {

   

	public static void main(String[] args) throws IOException, JAXBException, DatatypeConfigurationException, XMLStreamException, FactoryConfigurationError  {
     
		ActorSystem serverActorSystem = ActorSystem.create("ServerActorSystem");   //initiation of actor
        ActorRef serverActor = serverActorSystem.actorOf(Server.props(null), "serverActor");
    	
        
        
        //testing with random input values 
        
    String string= "1;2;3;4";   //test values
    	String[] values =string.split(";");
    	
    	
    	if(JenaReasoner.sparqlQuery("waspmote_gases"))
		   {
		  
		   
		   
		   Register.RegisterMsg();  // initiate the registration process
		   
		   
		   }
    	Odfmessage.main(values);   
        GPSclient.main();         //initiate the GPSclient

}}