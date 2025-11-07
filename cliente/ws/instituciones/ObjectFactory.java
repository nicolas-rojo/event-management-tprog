
package cliente.ws.instituciones;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cliente.ws.instituciones package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _InstitucionRepetidaException_QNAME = new QName("http://webservices/", "InstitucionRepetidaException");
    private final static QName _PatrocinioRepetidoException_QNAME = new QName("http://webservices/", "PatrocinioRepetidoException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cliente.ws.instituciones
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InstitucionRepetidaException }
     * 
     * @return
     *     the new instance of {@link InstitucionRepetidaException }
     */
    public InstitucionRepetidaException createInstitucionRepetidaException() {
        return new InstitucionRepetidaException();
    }

    /**
     * Create an instance of {@link PatrocinioRepetidoException }
     * 
     * @return
     *     the new instance of {@link PatrocinioRepetidoException }
     */
    public PatrocinioRepetidoException createPatrocinioRepetidoException() {
        return new PatrocinioRepetidoException();
    }

    /**
     * Create an instance of {@link DataInstitucion }
     * 
     * @return
     *     the new instance of {@link DataInstitucion }
     */
    public DataInstitucion createDataInstitucion() {
        return new DataInstitucion();
    }

    /**
     * Create an instance of {@link DataPatrocinio }
     * 
     * @return
     *     the new instance of {@link DataPatrocinio }
     */
    public DataPatrocinio createDataPatrocinio() {
        return new DataPatrocinio();
    }

    /**
     * Create an instance of {@link DataInstitucionArray }
     * 
     * @return
     *     the new instance of {@link DataInstitucionArray }
     */
    public DataInstitucionArray createDataInstitucionArray() {
        return new DataInstitucionArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InstitucionRepetidaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link InstitucionRepetidaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "InstitucionRepetidaException")
    public JAXBElement<InstitucionRepetidaException> createInstitucionRepetidaException(InstitucionRepetidaException value) {
        return new JAXBElement<>(_InstitucionRepetidaException_QNAME, InstitucionRepetidaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PatrocinioRepetidoException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PatrocinioRepetidoException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "PatrocinioRepetidoException")
    public JAXBElement<PatrocinioRepetidoException> createPatrocinioRepetidoException(PatrocinioRepetidoException value) {
        return new JAXBElement<>(_PatrocinioRepetidoException_QNAME, PatrocinioRepetidoException.class, null, value);
    }

}
