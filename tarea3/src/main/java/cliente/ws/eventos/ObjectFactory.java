
package cliente.ws.eventos;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cliente.ws.usuarios package. 
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

    private final static QName _EdicionRepetidaExcepcion_QNAME = new QName("http://webservices/", "EdicionRepetidaExcepcion");
    private final static QName _EventoNoExisteExcepcion_QNAME = new QName("http://webservices/", "EventoNoExisteExcepcion");
    private final static QName _EventoRepetidoExcepcion_QNAME = new QName("http://webservices/", "EventoRepetidoExcepcion");
    private final static QName _EventoSinCategoriaExcepcion_QNAME = new QName("http://webservices/", "EventoSinCategoriaExcepcion");
    private final static QName _LinkInvalidoExcepcion_QNAME = new QName("http://webservices/", "LinkInvalidoExcepcion");
    private final static QName _TipoDeRegistroRepetidoException_QNAME = new QName("http://webservices/", "TipoDeRegistroRepetidoException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cliente.ws.usuarios
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EdicionRepetidaExcepcion }
     * 
     * @return
     *     the new instance of {@link EdicionRepetidaExcepcion }
     */
    public EdicionRepetidaExcepcion createEdicionRepetidaExcepcion() {
        return new EdicionRepetidaExcepcion();
    }

    /**
     * Create an instance of {@link EventoNoExisteExcepcion }
     * 
     * @return
     *     the new instance of {@link EventoNoExisteExcepcion }
     */
    public EventoNoExisteExcepcion createEventoNoExisteExcepcion() {
        return new EventoNoExisteExcepcion();
    }

    /**
     * Create an instance of {@link EventoRepetidoExcepcion }
     * 
     * @return
     *     the new instance of {@link EventoRepetidoExcepcion }
     */
    public EventoRepetidoExcepcion createEventoRepetidoExcepcion() {
        return new EventoRepetidoExcepcion();
    }

    /**
     * Create an instance of {@link EventoSinCategoriaExcepcion }
     * 
     * @return
     *     the new instance of {@link EventoSinCategoriaExcepcion }
     */
    public EventoSinCategoriaExcepcion createEventoSinCategoriaExcepcion() {
        return new EventoSinCategoriaExcepcion();
    }

    /**
     * Create an instance of {@link LinkInvalidoExcepcion }
     * 
     * @return
     *     the new instance of {@link LinkInvalidoExcepcion }
     */
    public LinkInvalidoExcepcion createLinkInvalidoExcepcion() {
        return new LinkInvalidoExcepcion();
    }

    /**
     * Create an instance of {@link TipoDeRegistroRepetidoException }
     * 
     * @return
     *     the new instance of {@link TipoDeRegistroRepetidoException }
     */
    public TipoDeRegistroRepetidoException createTipoDeRegistroRepetidoException() {
        return new TipoDeRegistroRepetidoException();
    }

    /**
     * Create an instance of {@link DataPatrocinioCompleto }
     * 
     * @return
     *     the new instance of {@link DataPatrocinioCompleto }
     */
    public DataPatrocinioCompleto createDataPatrocinioCompleto() {
        return new DataPatrocinioCompleto();
    }

    /**
     * Create an instance of {@link DataEvento }
     * 
     * @return
     *     the new instance of {@link DataEvento }
     */
    public DataEvento createDataEvento() {
        return new DataEvento();
    }

    /**
     * Create an instance of {@link DataTRegistro }
     * 
     * @return
     *     the new instance of {@link DataTRegistro }
     */
    public DataTRegistro createDataTRegistro() {
        return new DataTRegistro();
    }

    /**
     * Create an instance of {@link DataEdicion }
     * 
     * @return
     *     the new instance of {@link DataEdicion }
     */
    public DataEdicion createDataEdicion() {
        return new DataEdicion();
    }

    /**
     * Create an instance of {@link DataEventoCompleto }
     * 
     * @return
     *     the new instance of {@link DataEventoCompleto }
     */
    public DataEventoCompleto createDataEventoCompleto() {
        return new DataEventoCompleto();
    }

    /**
     * Create an instance of {@link DataEdicionWeb }
     * 
     * @return
     *     the new instance of {@link DataEdicionWeb }
     */
    public DataEdicionWeb createDataEdicionWeb() {
        return new DataEdicionWeb();
    }

    /**
     * Create an instance of {@link DataPatrocinioCompletoArray }
     * 
     * @return
     *     the new instance of {@link DataPatrocinioCompletoArray }
     */
    public DataPatrocinioCompletoArray createDataPatrocinioCompletoArray() {
        return new DataPatrocinioCompletoArray();
    }

    /**
     * Create an instance of {@link DataEventoCompletoArray }
     * 
     * @return
     *     the new instance of {@link DataEventoCompletoArray }
     */
    public DataEventoCompletoArray createDataEventoCompletoArray() {
        return new DataEventoCompletoArray();
    }

    /**
     * Create an instance of {@link DataEdicionWebArray }
     * 
     * @return
     *     the new instance of {@link DataEdicionWebArray }
     */
    public DataEdicionWebArray createDataEdicionWebArray() {
        return new DataEdicionWebArray();
    }

    /**
     * Create an instance of {@link StringArray }
     * 
     * @return
     *     the new instance of {@link StringArray }
     */
    public StringArray createStringArray() {
        return new StringArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EdicionRepetidaExcepcion }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EdicionRepetidaExcepcion }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "EdicionRepetidaExcepcion")
    public JAXBElement<EdicionRepetidaExcepcion> createEdicionRepetidaExcepcion(EdicionRepetidaExcepcion value) {
        return new JAXBElement<>(_EdicionRepetidaExcepcion_QNAME, EdicionRepetidaExcepcion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EventoNoExisteExcepcion }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EventoNoExisteExcepcion }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "EventoNoExisteExcepcion")
    public JAXBElement<EventoNoExisteExcepcion> createEventoNoExisteExcepcion(EventoNoExisteExcepcion value) {
        return new JAXBElement<>(_EventoNoExisteExcepcion_QNAME, EventoNoExisteExcepcion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EventoRepetidoExcepcion }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EventoRepetidoExcepcion }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "EventoRepetidoExcepcion")
    public JAXBElement<EventoRepetidoExcepcion> createEventoRepetidoExcepcion(EventoRepetidoExcepcion value) {
        return new JAXBElement<>(_EventoRepetidoExcepcion_QNAME, EventoRepetidoExcepcion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EventoSinCategoriaExcepcion }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EventoSinCategoriaExcepcion }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "EventoSinCategoriaExcepcion")
    public JAXBElement<EventoSinCategoriaExcepcion> createEventoSinCategoriaExcepcion(EventoSinCategoriaExcepcion value) {
        return new JAXBElement<>(_EventoSinCategoriaExcepcion_QNAME, EventoSinCategoriaExcepcion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkInvalidoExcepcion }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LinkInvalidoExcepcion }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "LinkInvalidoExcepcion")
    public JAXBElement<LinkInvalidoExcepcion> createLinkInvalidoExcepcion(LinkInvalidoExcepcion value) {
        return new JAXBElement<>(_LinkInvalidoExcepcion_QNAME, LinkInvalidoExcepcion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoDeRegistroRepetidoException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TipoDeRegistroRepetidoException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "TipoDeRegistroRepetidoException")
    public JAXBElement<TipoDeRegistroRepetidoException> createTipoDeRegistroRepetidoException(TipoDeRegistroRepetidoException value) {
        return new JAXBElement<>(_TipoDeRegistroRepetidoException_QNAME, TipoDeRegistroRepetidoException.class, null, value);
    }

}
