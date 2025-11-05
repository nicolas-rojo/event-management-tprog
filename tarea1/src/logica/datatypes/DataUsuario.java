package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataUsuario", propOrder = {
    "nombre",
    "nickname",
    "email",
    "pass"
})
@XmlSeeAlso({
    DataAsistente.class,
    DataOrganizador.class
})
public class DataUsuario {
    
    @XmlElement(required = true)
    protected String nombre;
    
    @XmlElement(required = true)
    protected String nickname;
    
    @XmlElement(required = true)
    protected String email;
    
    @XmlElement(required = true)
    protected String pass;

    public DataUsuario() {}

    public DataUsuario(String nombre, String nickname, String email, String pass) {
        this.nombre = nombre;
        this.nickname = nickname;
        this.email = email;
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return nickname + " - " + nombre + " (" + email + ")";
    }
}