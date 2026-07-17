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
		!Buscamos la Empresa que ha iniciado la seccion, asi optenemos la empresa que esta loggeada.
	 */
	private Institucion obtenerEmpresaSeccion ( String usuario, String pass )
	{
		Usuario user = iniciarSesion(usuario, pass);
		Institucion institucion = null;

		for ( Usuario users : usuarios )
		{
			if ( users.getMyInstitucion().getNombre().equalsIgnoreCase(user.getMyInstitucion().getNombre()) )
				institucion = users.getMyInstitucion();
		}

		return institucion;
	}
	
	/*
	 @param Recibe un string con el siguiente formato: "campo: dato"
	 Se encarga de filtrar las ofertas en base a una consigna.
	 @return Un arreglo con las ofertas filtradas. 
	*/
	public ArrayList<Oferta> buscarOfertas(String filtro) {
		boolean modalidad = false, salario = false, requisitos = false, puesto = false;
		if (filtro.contains("modalidad"))
			modalidad = true;
		if (filtro.contains("salario"))
			salario = true;
		if (filtro.contains("requisitos"))
			requisitos = true;
		if (filtro.contains("puesto"))
			puesto = true;
		
		int pos = filtro.indexOf(":") + 2;
		String buscado = filtro.substring(pos);
		
		ArrayList<Oferta> result = new ArrayList<Oferta>();
		for (Oferta ofertaActual : this.ofertas) {
			if (modalidad && ofertaActual.getModalidad().equalsIgnoreCase(buscado)) 
				result.add(ofertaActual);
			if (salario && ("" + ofertaActual.getSalario()).equalsIgnoreCase(buscado))
				result.add(ofertaActual);
			if (requisitos && ofertaActual.getRequisitos().equalsIgnoreCase(buscado))
				result.add(ofertaActual);
			if (puesto && ofertaActual.getPuesto().equalsIgnoreCase(buscado))
				result.add(ofertaActual);
		}
		
		return result;
	}
}
