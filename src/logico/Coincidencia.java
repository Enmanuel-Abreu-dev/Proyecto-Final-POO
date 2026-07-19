package logico;

public class Coincidencia {
    private SolicitudEmp persona;
    private float porcentaje;

    public Coincidencia(SolicitudEmp persona, float porcentaje) {
        this.persona = persona;
        this.porcentaje = porcentaje;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public SolicitudEmp getPersona() {
        return persona;
    }

    public void setPersona(SolicitudEmp persona) {
        this.persona = persona;
    }

}
