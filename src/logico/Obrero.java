package logico;

import java.time.LocalDate;
import java.util.ArrayList;

public class Obrero extends Persona{
    
    private String profesion;

    public Obrero(String identificador, String cedula, String nombre, String apellido, String email, String direccion,
            String telefono, LocalDate fechaNacim, ArrayList<Experiencia> experiencia,
            ArrayList<SolicitudEmp> solicitudEmps, boolean dispViajar, boolean dispResidencia, boolean empleado,
            String profesion) {
        super(identificador, cedula, nombre, apellido, email, direccion, telefono, fechaNacim, experiencia,
                solicitudEmps, dispViajar, dispResidencia, empleado);
        this.profesion = profesion;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    
    
}
