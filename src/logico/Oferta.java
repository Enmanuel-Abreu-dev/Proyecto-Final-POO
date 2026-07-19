package logico;

import java.time.LocalDate;
import java.util.ArrayList;

public class Oferta {
    
    private String identificador;
    private Institucion myEmpresa;
    private String puesto;
    private String descripcion;
    private float salario;
    private String modalidad; 
    private String requisitos;
    private int aniosExperiencia;
    private int edad;
    private String ubicacion;
    private String pais;
    private String sexo; 
    private LocalDate fechaPublicacion;
    private LocalDate fechaFinalizacion;
    private ArrayList<SolicitudEmp> solicitudEmps;
    private boolean estado;
    private String tipoContrato;
    private int cantVacante;
    private String profesion;
    
    public Oferta(String identificador, Institucion myEmpresa, String puesto, String descripcion, float salario,
            String modalidad, String requisitos, int aniosExperiencia, int edad, String ubicacion, String pais,
            String sexo, LocalDate fechaFinalizacion,
            String tipoContrato, int cantVacante, String profesion) {
        this.identificador = identificador;
        this.myEmpresa = myEmpresa;
        this.puesto = puesto;
        this.descripcion = descripcion;
        this.salario = salario;
        this.modalidad = modalidad;
        this.requisitos = requisitos;
        this.aniosExperiencia = aniosExperiencia;
        this.edad = edad;
        this.ubicacion = ubicacion;
        this.pais = pais;
        this.sexo = sexo;
        this.fechaPublicacion = LocalDate.now();
        this.fechaFinalizacion = fechaFinalizacion;
        this.estado = false;
        this.tipoContrato = tipoContrato;
        this.cantVacante = cantVacante;
        this.profesion = profesion;
        solicitudEmps = new ArrayList<>();
    }

    public Institucion getMyEmpresa() {
        return myEmpresa;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
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

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public ArrayList<SolicitudEmp> getSolicitudEmps() {
        return solicitudEmps;
    }

    public void setSolicitudEmps(ArrayList<SolicitudEmp> solicitudEmps) {
        this.solicitudEmps = solicitudEmps;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public int getCantVacante() {
        return cantVacante;
    }

    public void setCantVacante(int cantVacante) {
        this.cantVacante = cantVacante;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void registrarSolicitudEmp ( SolicitudEmp solicitante )
    {
        solicitudEmps.add(solicitante);
    }
    
    
    // Managment
    public SolicitudEmp getSolicitudById(String id) {
    	SolicitudEmp solicitudActual = null;
    	int indice = 0;
    	boolean encontrado = false;
    	
    	while (!encontrado && indice < this.solicitudEmps.size()) {
    		solicitudActual = this.solicitudEmps.get(indice);
    		if (solicitudActual.getIdentificador().equalsIgnoreCase(id))
    			encontrado = true;
    		indice++;
    	}
    	return solicitudActual;
    }
    
}
