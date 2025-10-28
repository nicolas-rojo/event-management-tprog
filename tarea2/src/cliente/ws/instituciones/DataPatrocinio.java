
package cliente.ws.instituciones;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataPatrocinio complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataPatrocinio">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="fecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="monto" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="nivel" type="{http://webservices/}nivel" minOccurs="0"/>
 *         <element name="cod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="ctdCupo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataPatrocinio", propOrder = {
    "fecha",
    "monto",
    "nivel",
    "cod",
    "ctdCupo"
})
public class DataPatrocinio {

    protected String fecha;
    protected float monto;
    @XmlSchemaType(name = "string")
    protected Nivel nivel;
    @XmlElement(required = true)
    protected String cod;
    protected int ctdCupo;

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecha(String value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad monto.
     * 
     */
    public float getMonto() {
        return monto;
    }

    /**
     * Define el valor de la propiedad monto.
     * 
     */
    public void setMonto(float value) {
        this.monto = value;
    }

    /**
     * Obtiene el valor de la propiedad nivel.
     * 
     * @return
     *     possible object is
     *     {@link Nivel }
     *     
     */
    public Nivel getNivel() {
        return nivel;
    }

    /**
     * Define el valor de la propiedad nivel.
     * 
     * @param value
     *     allowed object is
     *     {@link Nivel }
     *     
     */
    public void setNivel(Nivel value) {
        this.nivel = value;
    }

    /**
     * Obtiene el valor de la propiedad cod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCod() {
        return cod;
    }

    /**
     * Define el valor de la propiedad cod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCod(String value) {
        this.cod = value;
    }

    /**
     * Obtiene el valor de la propiedad ctdCupo.
     * 
     */
    public int getCtdCupo() {
        return ctdCupo;
    }

    /**
     * Define el valor de la propiedad ctdCupo.
     * 
     */
    public void setCtdCupo(int value) {
        this.ctdCupo = value;
    }

}
