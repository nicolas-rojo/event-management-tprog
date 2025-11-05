package logica.datatypes;

import java.time.LocalDate;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataAsistente", propOrder = {
    "apellido",
    "fechaNac"
})
public class DataAsistente extends DataUsuario {

    @XmlElement(required = true)
    protected String apellido;

    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    protected LocalDate fechaNac;

    public DataAsistente() {
        super();
    }

    public DataAsistente(String nombre, String nickname, String email, String pass,
                        String apellido, LocalDate fechaNac) {
        super(nombre, nickname, email, pass);
        this.apellido = apellido;
        this.fechaNac = fechaNac;
    }

    public String getApellido() { return this.apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public LocalDate getFechaNac() { return this.fechaNac; }
    public void setFechaNac(LocalDate fechaNac) { this.fechaNac = fechaNac; }
}
