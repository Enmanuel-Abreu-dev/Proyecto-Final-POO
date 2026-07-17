package logico;

import java.time.LocalDate;

public class Experiencia {
    
    private String identificador;
    private String institucion;
    private String cargo;
    private LocalDate fechaInicio; 
    private LocalDate fechaFinalizacion;
    private String especialidad;

    public Experiencia(String identificador, String institucion, String cargo, LocalDate fechaInicio, LocalDate fechaFinalizacion, String especialidad) {
        this.identificador = identificador;
        this.institucion = institucion;
        this.cargo = cargo;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
        this.especialidad = especialidad;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    
    
}
