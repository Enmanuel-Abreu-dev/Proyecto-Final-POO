package logico;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BolsaTrabajo {
	public static int generadorIdPersona = 1;
	public static int generadorIdSolicitud = 1;
	public static int generadorIdOferta = 1;
	public static int generadorIdInstitucion = 1;
	public static int generadorIdUsuario = 1;
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
		this.usuarios = new ArrayList<Usuario>();
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
	
	// Managment:
	public String generarIdOferta() {
		generadorIdOferta++;
		return "OFF-" + generadorIdOferta;  
	}
	
	public String generarIdPersona() {
		generadorIdPersona++;
		return "PER-" + generadorIdPersona; 
	}
	
	public String generarIdUsuario() {
		generadorIdUsuario++;
		return "USU-" + generadorIdUsuario;
	}
	
	public String generarIdInstitucion() {
		generadorIdInstitucion++;
		return "INS-" + generadorIdInstitucion;
	}
	
	public String generarIdSolicitud() {
		generadorIdSolicitud++;
		return "SOL-" + generadorIdSolicitud;
	}
	
	public void registrarPersona(Persona p) {
		this.personas.add(p);
	}
	
	public void registrarInstitucion(Institucion i) {
		this.instituciones.add(i);
	}
	
	/*
	 Se encarga de verificar que existe un usuario que concuerde con los datos recibidos.
	 @return null si no se encontro, o el usuario encontrado si lo encontro. 
	*/
	public Usuario iniciarSesion(String usuario, String pass) {
		Usuario actual = null;
		int contador = 0;
		boolean encontrado = false;
		
		while (!encontrado && contador < this.usuarios.size()) {
			actual = this.usuarios.get(contador);
			if (actual.getNombre().equalsIgnoreCase(usuario) && actual.getPassword().equalsIgnoreCase(pass))
				encontrado = true;
			
			contador++;
		}
		
		return actual;
	}
	
	/*
	 Se encarga de listar las solicitudes segun si referencian a la persona recibida.
 	 @return un arreglo con dichas solicitudes
	*/
	public ArrayList<SolicitudEmp> listarSolicitudesPorPersona(Persona p) {
		ArrayList<SolicitudEmp> result = new ArrayList<SolicitudEmp>();
		
		for (Solicitud actual : this.solicitudes) {
			if (actual instanceof SolicitudEmp) {
				SolicitudEmp soli = (SolicitudEmp)actual;
				if (soli.getPersona().getIdentificador().equalsIgnoreCase(p.getIdentificador())) 
					result.add(soli);
			}
		}
		
		return result;
	}
	
	/*
	 Se encarga de listar las solicitudes de empleados segun si referencian a la oferta recibida.
	 @return un arreglo con dichas solicitudes. 
	*/
	public ArrayList<SolicitudEmp> listarCandidatosPorOferta(Oferta o) {
		ArrayList<SolicitudEmp> result = new ArrayList<SolicitudEmp>();
		
		for (Solicitud solicitudActual : this.solicitudes) {
			if (solicitudActual instanceof SolicitudEmp) {
				SolicitudEmp solicitud = (SolicitudEmp)solicitudActual;
				if (solicitud.getOferta().getIdentificador().equalsIgnoreCase(o.getIdentificador())) 
					result.add(solicitud);
			}
		}
		
		return result;
	}
	
	/*
	 Se encarga de crear y agregar a la lista de solicitudes una nueva solicitud con los datos recibidos.
	 @return la solicitud creada.
	*/
	public SolicitudEmp crearSolicitud(Persona p, Oferta o, Institucion e) {
		SolicitudEmp result = new SolicitudEmp(this.generarIdSolicitud(), true, o, p, e);
		this.solicitudes.add(result);
		return result;
	}
	
}
