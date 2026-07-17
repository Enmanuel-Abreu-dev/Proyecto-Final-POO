package logico;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tecnico extends Persona {
    
    private String especialidad;
    private String politecnico;

    public Tecnico(String identificador, String cedula, String nombre, String apellido, String email, String direccion,
            String telefono, LocalDate fechaNacim, ArrayList<Experiencia> experiencia,
            ArrayList<SolicitudEmp> solicitudEmps, boolean dispViajar, boolean dispResidencia, boolean empleado,
            String especialidad, String politecnico) {
        super(identificador, cedula, nombre, apellido, email, direccion, telefono, fechaNacim, experiencia,
                solicitudEmps, dispViajar, dispResidencia, empleado);
        this.especialidad = especialidad;
        this.politecnico = politecnico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getPolitecnico() {
        return politecnico;
    }

    public void setPolitecnico(String politecnico) {
        this.politecnico = politecnico;
    }

    
    
    

}
