
package cliente.ws.usuarios;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for estado.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>{@code
 * <simpleType name="estado">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="Ingresada"/>
 *     <enumeration value="Confirmado"/>
 *     <enumeration value="Rechazado"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "estado")
@XmlEnum
public enum Estado {

    @XmlEnumValue("Ingresada")
    INGRESADA("Ingresada"),
    @XmlEnumValue("Confirmado")
    CONFIRMADO("Confirmado"),
    @XmlEnumValue("Rechazado")
    RECHAZADO("Rechazado");
    private final String value;

    Estado(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Estado fromValue(String v) {
        for (Estado c: Estado.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
