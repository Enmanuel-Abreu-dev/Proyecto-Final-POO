package logico;

import java.time.LocalDate;
import java.util.ArrayList;

public class Universitario extends Persona{
    
    private String carrera;
    private String universidad;

    public Universitario(String identificador, String cedula, String nombre, String apellido, String email,
            String direccion, String telefono, LocalDate fechaNacim, ArrayList<Experiencia> experiencia,
            ArrayList<SolicitudEmp> solicitudEmps, boolean dispViajar, boolean dispResidencia, boolean empleado,
            String carrera, String universidad) {
        super(identificador, cedula, nombre, apellido, email, direccion, telefono, fechaNacim, experiencia,
                solicitudEmps, dispViajar, dispResidencia, empleado);
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
