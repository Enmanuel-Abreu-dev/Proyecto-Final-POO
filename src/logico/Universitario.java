package logico;

import java.time.LocalDate;

public class Universitario extends Persona{
    
    private String carrera;
    private String universidad;
    
    public Universitario(String identificador, String cedula, String nombre, String apellido, String email,
            String direccion, String sexo, String telefono, String pais, LocalDate fechaNacim, boolean dispViajar,
            boolean dispResidencia, String carrera, String universidad) {
        super(identificador, cedula, nombre, apellido, email, direccion, sexo, telefono, pais, fechaNacim, dispViajar,
                dispResidencia);
        this.carrera = carrera;
        this.universidad = universidad;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

   

    

    
}
