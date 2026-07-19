package logico;

public class SolicitudCentro extends Solicitud {
    
    private Institucion centro;
    private Persona persona;
    private String Mensaje;

    public SolicitudCentro(String identificador, Oferta oferta, Institucion centro, Persona persona,
            String mensaje) {
        super(identificador, oferta);
        this.centro = centro;
        this.persona = persona;
        Mensaje = mensaje;
    }

    public Institucion getCentro() {
        return centro;
    }

    public void setCentro(Institucion centro) {
        this.centro = centro;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }
}
