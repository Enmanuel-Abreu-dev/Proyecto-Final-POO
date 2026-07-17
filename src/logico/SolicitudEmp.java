package logico;

import java.time.LocalDate;

public class SolicitudEmp extends Solicitud{

    private Persona persona;

    public SolicitudEmp(String identificador, boolean estado, Oferta oferta, Persona persona) {
        super(identificador, estado, oferta);
        this.persona = persona;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
