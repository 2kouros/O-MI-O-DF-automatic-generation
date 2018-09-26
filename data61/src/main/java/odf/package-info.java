@javax.xml.bind.annotation.XmlSchema (
        namespace="odf.xsd",
        xmlns = {
                @javax.xml.bind.annotation.XmlNs(prefix = "",
                        namespaceURI="odf.xsd"),
                
                @javax.xml.bind.annotation.XmlNs(prefix="sosa",
                namespaceURI="http://www.w3.org/ns/sosa"),
                @javax.xml.bind.annotation.XmlNs(prefix="geo",
                namespaceURI="http://www.w3.org/2003/01/geo/wgs84_pos#"),
                @javax.xml.bind.annotation.XmlNs(prefix="qu",
                namespaceURI="http://purl.oclc.org/NET/ssnx/qu/qu#"),
        },
        location = "odf.xsd odf.xsd",
        elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED
)
package odf;