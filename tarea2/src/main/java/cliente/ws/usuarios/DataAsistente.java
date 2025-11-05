
package cliente.ws.usuarios;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataAsistente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="dataAsistente">
 *   <complexContent>
 *     <extension base="{http://webservices/}dataUsuario">
 *       <sequence>
 *         <element name="apellido" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="fechaNac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataAsistente", propOrder = {
    "apellido",
    "fechaNac"
})
public class DataAsistente
    extends DataUsuario
{

    @XmlElement(required = true)
    protected String apellido;
    protected String fechaNac;

    /**
     * Gets the value of the apellido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Sets the value of the apellido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido(String value) {
        this.apellido = value;
    }

    /**
     * Gets the value of the fechaNac property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaNac() {
        return fechaNac;
    }

    /**
     * Sets the value of the fechaNac property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaNac(String value) {
        this.fechaNac = value;
    }

}
