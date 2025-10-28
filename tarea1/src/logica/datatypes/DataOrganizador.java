package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataOrganizador", propOrder = {
    "nombre",
    "nickname",
    "email",
    "pass",
    "descripcion",
    "url"
})
public class DataOrganizador extends DataUsuario {
    
    @XmlElement(required = true)
    protected String descripcion;
    
    @XmlElement
    protected String url;

    public DataOrganizador() {
        super();
    }
    
    public DataOrganizador(String nombre, String nickname, String email, String pass, 
                          String desc, String url) {
        super(nombre, nickname, email, pass);
        this.descripcion = desc;
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}