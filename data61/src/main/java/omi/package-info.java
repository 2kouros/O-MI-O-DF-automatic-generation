@javax.xml.bind.annotation.XmlSchema (
        namespace="omi.xsd",
        xmlns = {
                @javax.xml.bind.annotation.XmlNs(prefix = "omi",
                        namespaceURI="omi.xsd"),
             
                @javax.xml.bind.annotation.XmlNs(prefix="sosa",
                namespaceURI="http://www.w3.org/ns/sosa"),
                @javax.xml.bind.annotation.XmlNs(prefix="geo",
                namespaceURI="http://www.w3.org/2003/01/geo/wgs84_pos#"),
                @javax.xml.bind.annotation.XmlNs(prefix="qu",
                namespaceURI="http://purl.oclc.org/NET/ssnx/qu/qu#"),
        },
        location = "omi.xsd omi.xsd",
        elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED
)
package omi;
