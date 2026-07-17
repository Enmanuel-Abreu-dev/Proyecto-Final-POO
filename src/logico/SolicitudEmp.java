package logico;

import java.time.LocalDate;

public class SolicitudEmp extends Solicitud{

    private Persona persona;
    private Institucion empresa;

    public SolicitudEmp(String identificador, LocalDate fecha, boolean estado, Oferta oferta, Persona persona,
            Institucion empresa) {
        super(identificador, fecha, estado, oferta);
        this.persona = persona;
        this.empresa = empresa;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Institucion getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Institucion empresa) {
        this.empresa = empresa;
    }

    

    

}
