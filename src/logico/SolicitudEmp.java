package logico;

public class SolicitudEmp extends Solicitud{

    private Persona persona;
    private String rangoSalarial;
    private String modalidad;

    public SolicitudEmp(String identificador, Oferta oferta, Persona persona, String rangoSalarial,
            String modalidad) {
        super(identificador, oferta);
        this.persona = persona;
        this.rangoSalarial = rangoSalarial;
        this.modalidad = modalidad;

    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getRangoSalarial() {
        return rangoSalarial;
    }

    public void setRangoSalarial(String rangoSalarial) {
        this.rangoSalarial = rangoSalarial;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }
    
}
