package logico;

import java.time.LocalDate;


public class Tecnico extends Persona {
    
    private String especialidad;
    private String politecnico;
    
    public Tecnico(String identificador, String cedula, String nombre, String apellido, String email, String direccion,
            String sexo, String telefono, String pais, String rutaImagen, LocalDate fechaNacim, boolean dispViajar,
            boolean dispResidencia, String especialidad, String politecnico) {
        super(identificador, cedula, nombre, apellido, email, direccion, sexo, telefono, pais, rutaImagen, fechaNacim,
                dispViajar, dispResidencia);
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
