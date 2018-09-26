package core;

import odf.*;
import omi.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.io.StringWriter;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.bind.JAXBContext;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.stream.FactoryConfigurationError;

import javax.xml.stream.XMLStreamException;


import core.JenaReasoner;
import java.util.ArrayList;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

public class Odfmessage {
	
	public static final ArrayList<String> FullDescription=JenaReasoner.getList();
	public static final List<String> ObjectOneDescription=FullDescription.subList(0, 4);
	public static final List<String> ObjectTwoDescription=FullDescription.subList(4, 8);
	public static final List<String> ObjectThreeDescription=FullDescription.subList(8, 12);
	public static final List<String> ObjectFourDescription=FullDescription.subList(12, 16);
	public static final List<String> ObjectFiveDescription=FullDescription.subList(16,20);
	public static final odf.ObjectFactory odfcreator=new odf.ObjectFactory();
	public static final omi.ObjectFactory omicreator=new omi.ObjectFactory();
	public final static String ODF = "odf";
	
	
	
public static void main (String[] values) throws IOException, JAXBException, DatatypeConfigurationException, XMLStreamException, FactoryConfigurationError {
	
	JAXBContext context = JAXBContext.newInstance("odf:omi");
	  Marshaller marshaller = context.createMarshaller();
	  Unmarshaller unmarshaller=context.createUnmarshaller();
	  marshaller.setProperty("jaxb.formatted.output",Boolean.TRUE);
	  marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "odf.xsd omi.xsd");
	 
	 ObjectType o= new ObjectType();
     o.addObject(createSensorBox(values));
 
	OmiEnvelope envelope= omicreator.createOmiEnvelope();
	WriteRequest write =omicreator.createWriteRequest();
	write.setMsgformat(ODF);
	write.setMsg(o);
	
	envelope.setWrite(write);
	envelope.setTtl(10);
	envelope.setVersion("1.0");

    StringWriter sw = new StringWriter();
  
   marshaller.marshal(envelope,sw);
   marshaller.marshal(envelope,System.out);
   HttpConn.sendPOST(sw);
     
}

 
  public static ObjectType createSensorBox(String[] values) throws DatatypeConfigurationException{
	  
    ObjectType me= odfcreator.createObjectType();
    QlmID d= new QlmID();
    d.setValue("waspmote_gases");
    me.getId().add(d);
    Vector<InfoItemType> infoItems = new Vector<InfoItemType>();

me.setType("sosa:Platform");
   
   infoItems.add(createSensor(ObjectOneDescription,values[0]));
    infoItems.add(createSensor(ObjectTwoDescription,values[1]));
    infoItems.add(createSensor(ObjectThreeDescription,values[2]));
    infoItems.add(createSensor(ObjectFourDescription,values[3]));
    infoItems.add(location("lat",GPSclient.map.get("latitude")));
    infoItems.add(location("long",GPSclient.map.get("longitude")));
    infoItems.add(location("alt",GPSclient.map.get("altitude")));
    me.getInfoItem().addAll(infoItems);
   
	return me; 
  }

  public static InfoItemType createSensor(List<String> desc,String val) throws DatatypeConfigurationException{
	  
     // Description d=new Description();
     // d.setValue(desc.get(2));
     // d.setType("sosa:ObservableProperty");
      InfoItemType sensor=odfcreator.createInfoItemType();
      sensor.setName(desc.get(0));
      sensor.setType("sosa:Sensor");
	 // sensor.setDescription(d);
      ValueType valuetype=odfcreator.createValueType();
      valuetype.setType("sosa:hasSimpleResult");
	  valuetype.setValue(val);
	  valuetype.setDateTime(getXMLGregorianCalendarNow());

	  sensor.getValue().add(valuetype);
 
       return( sensor);
   
  }
  
  public static InfoItemType location(String name, String value ) throws DatatypeConfigurationException{
	  InfoItemType location =odfcreator.createInfoItemType();
	  location.setType("geo:Point");
	  ValueType valuetype=odfcreator.createValueType();
	  location.setName(name);
      valuetype.setType("geo:"+ name);	  
	  valuetype.setValue(value);
	  valuetype.setDateTime(getXMLGregorianCalendarNow());
	  location.getValue().add(valuetype);
	  
	  return location;
	}

  public static XMLGregorianCalendar getXMLGregorianCalendarNow()    throws DatatypeConfigurationException
  {
      GregorianCalendar gregorianCalendar = new GregorianCalendar();
      DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
      XMLGregorianCalendar now = 
          datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
      return now;
  }
 
}
 