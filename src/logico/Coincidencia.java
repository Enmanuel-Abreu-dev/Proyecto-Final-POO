package logico;

public class Coincidencia {
	private Persona persona;
    private float porcentaje;
   
    public Coincidencia(Persona persona, float porcentaje) {
        this.persona = persona;
        this.porcentaje = porcentaje;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    
    

}
