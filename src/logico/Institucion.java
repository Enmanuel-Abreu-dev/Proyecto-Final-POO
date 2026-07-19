package logico;

import java.util.ArrayList;

public class Institucion {
    
    private String identificador;
    private String nombre;
    private String RNC; 
    private String pais;
    private String registroSocial;
    private String direccion;
    private String telefono;
    private String email;
    private int cantEmpleado;
    private boolean privado;
    private ArrayList<SolicitudCentro> mySolicitudes;
    private ArrayList<Oferta> myOfertas;
    private Usuario usuario;
    
    public Institucion(String identificador, String nombre, String rNC, String pais, String registroSocial,
            String direccion, String telefono, String email, int cantEmpleado, boolean privado, Usuario usuario) {
        this.identificador = identificador;
        this.nombre = nombre;
        RNC = rNC;
        this.pais = pais;
        this.registroSocial = registroSocial;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.cantEmpleado = cantEmpleado;
        this.privado = privado;
        this.usuario = usuario;
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

    public ArrayList<Oferta> getMyOfertas() {
        return myOfertas;
    }

    public void setMyOfertas(ArrayList<Oferta> myOfertas) {
        this.myOfertas = myOfertas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getRegistroSocial() {
        return registroSocial;
    }

    public void setRegistroSocial(String registroSocial) {
        this.registroSocial = registroSocial;
    }

    // Managment:
    public void publicarOferta(Oferta o) {
    	o.setEstado(true);
    	this.myOfertas.add(o);
    }
    
    public boolean cerrarOferta(String idOferta) {
    	Oferta ofertaActual = null;
    	int indice = 0;
    	boolean encontrado = false;
    	
    	while (!encontrado && indice < this.myOfertas.size()) {
    		ofertaActual = this.myOfertas.get(indice);
    		if (ofertaActual.getIdentificador().equalsIgnoreCase(idOferta)) {
    			encontrado = true;
    		}
    		indice++;
    	}
    	
    	if (ofertaActual != null) {
    		ofertaActual.setEstado(false);
    		return true;
    	}
    	return false;
    }
    
    public Oferta getOfertaById(String id) {
    	Oferta ofertaActual = null;
    	int indice = 0;
    	boolean encontrado = false;
    	
    	while (!encontrado && indice < this.myOfertas.size()) {
    		ofertaActual = this.myOfertas.get(indice);
    		if (ofertaActual.getIdentificador().equalsIgnoreCase(id))
    			encontrado = true;
    		indice++;
    	}
    	return ofertaActual;
    }
}
