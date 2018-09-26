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

public class Register {
	
	public static final ArrayList<String> FullDescription=JenaReasoner.getList();
	public static final List<String> ObjectOneDescription=FullDescription.subList(0, 4);
	public static final List<String> ObjectTwoDescription=FullDescription.subList(4, 8);
	public static final List<String> ObjectThreeDescription=FullDescription.subList(8, 12);
	public static final List<String> ObjectFourDescription=FullDescription.subList(12, 16);
	public static final List<String> ObjectFiveDescription=FullDescription.subList(16,20);
	public static final odf.ObjectFactory odfcreator=new odf.ObjectFactory();
	public static final omi.ObjectFactory omicreator=new omi.ObjectFactory();
	public final static String ODF = "odf";
	
	
	
public static void RegisterMsg () throws IOException, JAXBException, DatatypeConfigurationException, XMLStreamException, FactoryConfigurationError {
	
	JAXBContext context = JAXBContext.newInstance("odf:omi");
	  Marshaller marshaller = context.createMarshaller();
	  Unmarshaller unmarshaller=context.createUnmarshaller();
	  marshaller.setProperty("jaxb.formatted.output",Boolean.TRUE);
	  marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "odf.xsd omi.xsd");
	 
	 ObjectType o= new ObjectType();
     o.addObject(createSensorBox());
 
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

 
  public static ObjectType createSensorBox() throws DatatypeConfigurationException{
	  
    ObjectType me= odfcreator.createObjectType();
    QlmID d= new QlmID();
    d.setValue("waspmote_gases");
    me.getId().add(d);
    Vector<InfoItemType> infoItems = new Vector<InfoItemType>();

me.setType("sosa:Platform");
   
   infoItems.add(createSensor(ObjectOneDescription));
    infoItems.add(createSensor(ObjectTwoDescription));
    infoItems.add(createSensor(ObjectThreeDescription));
    infoItems.add(createSensor(ObjectFourDescription));
    infoItems.add(location("lat",ObjectFiveDescription));
    infoItems.add(location("long",ObjectFiveDescription));
    infoItems.add(location("alt",ObjectFiveDescription));
    me.getInfoItem().addAll(infoItems);
   
	return me; 
  }

  public static InfoItemType createSensor(List<String> desc) throws DatatypeConfigurationException{
	  
      Description d=new Description();
      d.setValue(desc.get(2));
      d.setType("sosa:ObservableProperty");
      InfoItemType sensor=odfcreator.createInfoItemType();
      sensor.setName(desc.get(0));
      sensor.setType("sosa:Sensor");
	  sensor.setDescription(d);
	  
	  
	  
	  InfoItemType.MetaData metadata= odfcreator.createInfoItemTypeMetaData();
	    InfoItemType form=odfcreator.createInfoItemType();
	    form.setName("format");
	    form.setType("xs:string");
	    ValueType valuetype=odfcreator.createValueType();
	    valuetype.setType("sosa:hasSimpleResult");
	    valuetype.setValue(desc.get(1));
	    form.getValue().add(valuetype);
	    
	    InfoItemType units=odfcreator.createInfoItemType();
	    units.setName("unit");
	    units.setType("xs:string");
	    ValueType valuetyp=odfcreator.createValueType();
	    valuetyp.setType("qu:unit");
	    valuetyp.setValue(desc.get(3));
	    units.getValue().add(valuetyp);
	    
	    metadata.getInfoItem().add(form);
	    metadata.getInfoItem().add(units);
	    sensor.setMetaData(metadata);
 
       return( sensor);
   
  }
  
  public static InfoItemType location(String name, List<String> desc ) throws DatatypeConfigurationException{
	  InfoItemType location =odfcreator.createInfoItemType();
	  location.setType("geo:Point");
	  location.setName(name);
	  Description d=new Description();
      d.setValue(desc.get(2));
      d.setType("sosa:ObservableProperty");
      location.setDescription(d);
      
    InfoItemType.MetaData metadata= odfcreator.createInfoItemTypeMetaData();
    InfoItemType form=odfcreator.createInfoItemType();
    form.setName("format");
    form.setType("xs:string");
    ValueType valuetype=odfcreator.createValueType();
    valuetype.setType("sosa:hasSimpleResult");
    valuetype.setValue(desc.get(1));
    form.getValue().add(valuetype);
    
    InfoItemType units=odfcreator.createInfoItemType();
    units.setName("unit");
    units.setType("xs:string");
    ValueType valuetyp=odfcreator.createValueType();
    valuetyp.setType("qu:unit");
    valuetyp.setValue(desc.get(3));
    units.getValue().add(valuetyp);
    
    metadata.getInfoItem().add(form);
    metadata.getInfoItem().add(units);
    location.setMetaData(metadata);
	  
	  return location;
	}}