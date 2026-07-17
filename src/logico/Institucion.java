package logico;

import java.util.ArrayList;

public class Institucion {
    
    private String identificador;
    private String nombre;
    private String RNC; 
    private String pais;
    private String direccion;
    private String telefono;
    private String email;
    private int cantEmpleado;
    private boolean privado;
    private ArrayList<SolicitudCentro> mySolicitudes;
    private Usuario usuario;

    public Institucion(String identificador, String nombre, String rNC, String pais, String direccion, String telefono,
            String email, int cantEmpleado, boolean privado, ArrayList<SolicitudCentro> mySolicitudes) {
        this.identificador = identificador;
        this.nombre = nombre;
        RNC = rNC;
        this.pais = pais;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.cantEmpleado = cantEmpleado;
        this.privado = privado;
        this.mySolicitudes = mySolicitudes;
        this.usuario = null;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRNC() {
        return RNC;
    }

    public void setRNC(String rNC) {
        RNC = rNC;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCantEmpleado() {
        return cantEmpleado;
    }

    public void setCantEmpleado(int cantEmpleado) {
        this.cantEmpleado = cantEmpleado;
    }

    public boolean isPrivado() {
        return privado;
    }

    public void setPrivado(boolean privado) {
        this.privado = privado;
    }

    public ArrayList<SolicitudCentro> getMySolicitudes() {
        return mySolicitudes;
    }

    public void setMySolicitudes(ArrayList<SolicitudCentro> mySolicitudes) {
        this.mySolicitudes = mySolicitudes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    
    
}
