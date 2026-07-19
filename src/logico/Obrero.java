package logico;

import java.time.LocalDate;

public class Obrero extends Persona{
    
    private String profesion;

    public Obrero(String identificador, String cedula, String nombre, String apellido, String email, String direccion,
            String sexo, String telefono, String pais, LocalDate fechaNacim, boolean dispViajar, boolean dispResidencia,
            String profesion) {
        super(identificador, cedula, nombre, apellido, email, direccion, sexo, telefono, pais, fechaNacim, dispViajar,
                dispResidencia);
        this.profesion = profesion;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    

    
    
}
