package logico;

public class SolicitudEmp extends Solicitud{

    private Persona persona;
    private String rangoSalarial;
    private String Modalidad;

    public SolicitudEmp(String identificador, boolean estado, Oferta oferta, Persona persona, String rangoSalarial,
            String modalidad) {
        super(identificador, estado, oferta);
        this.persona = persona;
        this.rangoSalarial = rangoSalarial;
        Modalidad = modalidad;
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
        return Modalidad;
    }

    public void setModalidad(String modalidad) {
        Modalidad = modalidad;
    }

    
   
}
