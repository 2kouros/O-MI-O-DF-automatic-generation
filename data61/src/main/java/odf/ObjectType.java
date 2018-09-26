package odf;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

/**
 * <p>
 * Java class for ObjectType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="ObjectType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{odf.xsd}qlmID" maxOccurs="unbounded"/&gt;
 *         &lt;element ref="{odf.xsd}description" minOccurs="0"/&gt;
 *         &lt;element ref="{odf.xsd}InfoItem" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{odf.xsd}Object" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *       &lt;anyAttribute/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectType", propOrder = {
   "id",
   "description",
   "infoItem",
   "object"
})
@XmlRootElement(name="Object")
public class ObjectType  {
   private static final odf.ObjectFactory odfFactory = new odf.ObjectFactory();

   /**
    *
    */
   
   @XmlElement(required = true)
   protected List<QlmID> id;

   /**
    *
    */
   @XmlElement(name = "Description")
   protected Description description;

   /**
    *
    */
   @XmlElement(name = "InfoItem")
   protected List<InfoItemType> infoItem;

   /**
    *
    */
   @XmlElementWrapper(name="Objects")
   @XmlElement(name = "Object")
   
   protected List<ObjectType> object;

   /**
    *
    */
   @XmlAttribute(name = "type")
   @XmlSchemaType(name = "anySimpleType")
   protected String type;
   @XmlAnyAttribute
   private Map<QName, String> otherAttributes = new HashMap<QName, String>();

   
   private static PropertyChangeSupport propertySupport;

   /**
    *
    */
   public static final String PROP_DESCRIPTION_PROPERTY = "descriptionProperty";

   /**
    *
    */
   public static final String PROP_OBJECTS_PROPERTY = "objectsProperty";

   /**
    *
    */
   public static final String PROP_INFOITEMS_PROPERTY = "infoitemsProperty";
   
   /**
    *
    */
   public ObjectType() {
      propertySupport = new PropertyChangeSupport(this);
   }
   
   /**
    * Gets the value of the id property.
    *
    * <p>
    * This accessor method returns a reference to the live list, not a snapshot.
    * Therefore any modification you make to the returned list will be present
    * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
    * for the id property.
    *
    * <p>
    * For example, to add a new item, do as follows:
    * <pre>
    *    getId().add(newItem);
    * </pre>
    *
    *
    * <p>
    * Objects of the following type(s) are allowed in the list {@link QlmID }
    *
    *
    * @return
    */
   public List<QlmID> getId() {
      if (id == null) {
         id = new ArrayList<QlmID>();
      }
      return this.id;
   }

   /**
    * Gets the value of the description property.
    *
    * @return possible object is {@link Description }
    *
    */
   public Description getDescription() {
      return description;
   }

   /**
    * Sets the value of the description property.
    *
    * @param value allowed object is {@link Description }
    *
    */
   public void setDescription(Description value) {
      Description prev = this.description;
      this.description = value;
      propertySupport.firePropertyChange(PROP_DESCRIPTION_PROPERTY, this.description, prev);
   }

   /**
    * Gets the value of the infoItem property.
    *
    * <p>
    * This accessor method returns a reference to the live list, not a snapshot.
    * Therefore any modification you make to the returned list will be present
    * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
    * for the infoItem property.
    *
    * <p>
    * For example, to add a new item, do as follows:
    * <pre>
    *    getInfoItem().add(newItem);
    * </pre>
    *
    *
    * <p>
    * Objects of the following type(s) are allowed in the list
     * {@link InfoItemType }
    *
    *
    * @return
    */
   public List<InfoItemType> getInfoItem() {
      if (infoItem == null) {
         infoItem = new ArrayList<InfoItemType>();
      }
      return this.infoItem;
   }

   /**
    * Gets the value of the object property.
    *
    * <p>
    * This accessor method returns a reference to the live list, not a snapshot.
    * Therefore any modification you make to the returned list will be present
    * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
    * for the object property.
    *
    * <p>
    * For example, to add a new item, do as follows:
    * <pre>
    *    getObject().add(newItem);
    * </pre>
    *
    *
    * <p>
    * Objects of the following type(s) are allowed in the list
     * {@link ObjectType }
    *
    *
    * @return
    */
  
   public List<ObjectType> getObject() {
      if (object == null) {
         object = new ArrayList<ObjectType>();
      }
      return this.object;
   }

   /**
    * Gets the value of the type property.
    *
    * @return possible object is {@link String }
    *
    */
   public String getType() {
      return type;
   }

   /**
    * Sets the value of the type property.
    *
    * @param value allowed object is {@link String }
    *
    */
   public void setType(String value) {
      this.type = value;
   }

   /**
    * Gets a map that contains attributes that aren't bound to any typed
    * property on this class.
    *
    * <p>
    * the map is keyed by the name of the attribute and the value is the string
    * value of the attribute.
    *
    * the map returned by this method is live, and you can add new attribute by
    * updating the map directly. Because of this design, there's no setter.
    *
    *
    * @return always non-null
    */
   public Map<QName, String> getOtherAttributes() {
      return otherAttributes;
   }

   /**
    *
    * @param info
    */
   public void addInfoItem(InfoItemType info) {
      List<InfoItemType> prev = getInfoItem();
      getInfoItem().add(info);
      propertySupport.firePropertyChange(PROP_INFOITEMS_PROPERTY, this.description, prev);
   }

   /**
    *
    * @param o
    */
  
   public void addObject(ObjectType o) {
	   
      List<ObjectType> prev = getObject();
      getObject().add(o);
      propertySupport.firePropertyChange(PROP_INFOITEMS_PROPERTY, this.description, prev);
   }

  
   
   
}
