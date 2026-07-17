package logico;

import java.util.ArrayList;

public class BolsaTrabajo {
	public static int generadorIdPersona = 0;
	public static int generadorIdSolicitud = 0;
	public static int generadorIdOferta = 0;
	public static int generadorIdInstitucion = 0;
	public static int generadorIdUsuario = 0;
	private ArrayList<Persona> personas;
	private ArrayList<Solicitud> solicitudes;
	private ArrayList<Oferta> ofertas;
	private ArrayList<Institucion> instituciones;
	private ArrayList<Usuario> usuarios;
	private static BolsaTrabajo instance = null;
	
	public BolsaTrabajo() {
		this.personas = new ArrayList<Persona>();
		this.solicitudes = new ArrayList<Solicitud>();
		this.ofertas = new ArrayList<Oferta>();
		this.instituciones = new ArrayList<Institucion>();
	}
	
	
	public BolsaTrabajo getInstace() {
		if (instance == null)
			instance = new BolsaTrabajo();
		
		return instance;
	}


	public ArrayList<Persona> getPersonas() {
		return personas;
	}


	public void setPersonas(ArrayList<Persona> personas) {
		this.personas = personas;
	}


	public ArrayList<Solicitud> getSolicitudes() {
		return solicitudes;
	}


	public void setSolicitudes(ArrayList<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}


	public ArrayList<Oferta> getOfertas() {
		return ofertas;
	}


	public void setOfertas(ArrayList<Oferta> ofertas) {
		this.ofertas = ofertas;
	}


	public ArrayList<Institucion> getInstituciones() {
		return instituciones;
	}


	public void setInstituciones(ArrayList<Institucion> instituciones) {
		this.instituciones = instituciones;
	}


	public static BolsaTrabajo getInstance() {
		return instance;
	}


	public static void setInstance(BolsaTrabajo instance) {
		BolsaTrabajo.instance = instance;
	}


	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}
