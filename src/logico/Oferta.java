package logico;

import java.time.LocalDate;

public class Oferta {
    
    private String identificador;
    private Institucion myEmpresa;
    private String puesto;
    private String descripcion;
    private float salario;
    private String modalidad; 
    private String requisitos;
    private LocalDate fechaPublicacion;
    private LocalDate fechaFinalizacion;
    private float conincidencia;
    private boolean estado;

    public Oferta(String identificador, Institucion myEmpresa, String puesto, String descripcion, float salario,
            String modalidad, String requisitos, LocalDate fechaPublicacion, LocalDate fechaFinalizacion,
            float conincidencia, boolean estado) {
        this.identificador = identificador;
        this.myEmpresa = myEmpresa;
        this.puesto = puesto;
        this.descripcion = descripcion;
        this.salario = salario;
        this.modalidad = modalidad;
        this.requisitos = requisitos;
        this.fechaPublicacion = fechaPublicacion;
        this.fechaFinalizacion = fechaFinalizacion;
        this.conincidencia = conincidencia;
        this.estado = estado;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Institucion getMyEmpresa() {
        return myEmpresa;
    }

    public void setMyEmpresa(Institucion myEmpresa) {
        this.myEmpresa = myEmpresa;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public float getConincidencia() {
        return conincidencia;
    }

    public void setConincidencia(float conincidencia) {
        this.conincidencia = conincidencia;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
    
}
