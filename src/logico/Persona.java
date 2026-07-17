package logico;

import java.time.LocalDate;
import java.util.ArrayList;

abstract class Persona {

    protected String identificador;
    protected String cedula;
    protected String nombre;
    protected String apellido;
    protected String email;
    protected String direccion;
    protected String telefono;
    protected LocalDate fechaNacim;
    protected ArrayList<Experiencia> experiencia;
    protected ArrayList<SolicitudEmp> solicitudEmps;
    protected boolean dispViajar;
    protected boolean dispResidencia;
    protected boolean empleado;
    protected Usuario usuario;

    public Persona(String identificador, String cedula, String nombre, String apellido, String email, String direccion,
            String telefono, LocalDate fechaNacim, ArrayList<Experiencia> experiencia,
            ArrayList<SolicitudEmp> solicitudEmps, boolean dispViajar, boolean dispResidencia, boolean empleado) {
        this.identificador = identificador;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNacim = fechaNacim;
        this.experiencia = experiencia;
        this.solicitudEmps = solicitudEmps;
        this.dispViajar = dispViajar;
        this.dispResidencia = dispResidencia;
        this.empleado = empleado;
        this.usuario = null;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacim() {
        return fechaNacim;
    }

    public void setFechaNacim(LocalDate fechaNacim) {
        this.fechaNacim = fechaNacim;
    }

    public ArrayList<Experiencia> getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(ArrayList<Experiencia> experiencia) {
        this.experiencia = experiencia;
    }

    public ArrayList<SolicitudEmp> getSolicitudEmps() {
        return solicitudEmps;
    }

    public void setSolicitudEmps(ArrayList<SolicitudEmp> solicitudEmps) {
        this.solicitudEmps = solicitudEmps;
    }

    public boolean isDispViajar() {
        return dispViajar;
    }

    public void setDispViajar(boolean dispViajar) {
        this.dispViajar = dispViajar;
    }

    public boolean isDispResidencia() {
        return dispResidencia;
    }

    public void setDispResidencia(boolean dispResidencia) {
        this.dispResidencia = dispResidencia;
    }

    public boolean isEmpleado() {
        return empleado;
    }

    public void setEmpleado(boolean empleado) {
        this.empleado = empleado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    

}
