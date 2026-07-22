package logico;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class BolsaTrabajo {
	public static int generadorIdPersona = 0;
	public static int generadorIdSolicitud = 0;
	public static int generadorIdOferta = 0;
	public static int generadorIdInstitucion = 0;
	public static int generadorIdUsuario = 0;
	private static Usuario usuarioActual;
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
	
	
	public static BolsaTrabajo getInstance() {
		if (instance == null)
			instance = new BolsaTrabajo();
		
		return instance;
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(Usuario usuarioActual) {
		BolsaTrabajo.usuarioActual = usuarioActual;
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
	
	public void registrarUsuario ( Usuario u )
	{
		this.usuarios.add(u);
	}

	/*
	 Se encarga de verificar que existe un usuario que concuerde con los datos recibidos.
	 @return null si no se encontro, o el usuario encontrado si lo encontro. 
	*/
	public boolean iniciarSesion(String usuario, String pass) {
		for ( Usuario user : usuarios )
		{
			if ( user.getNombre().equals(usuario) && user.getPassword().equals(pass) )
			{	
				setUsuarioActual(user);
				return true;
			}
		}
		return false;
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
	private Institucion obtenerEmpresaSeccion ()
	{
		Institucion institucion = null;

		for ( Usuario users : usuarios )
		{
			if ( users.getMyInstitucion().getNombre().equalsIgnoreCase(usuarioActual.getMyInstitucion().getNombre()) )
				institucion = users.getMyInstitucion();
		}

		return institucion;
	}

	/*
		!Calculamos la coincidencia con de las solicitudes enviadas a la oferta dada devolviendo
		!un arreglo de la lista de las coincidencia de mayor a menor 
	*/
	public ArrayList<Coincidencia> calcularCoincidencia( String nombreOferta )
	{
		Institucion institucion = obtenerEmpresaSeccion();
		ArrayList<Coincidencia> listaMacheo = new ArrayList<>();

		if ( institucion != null && estadoOferta(nombreOferta, institucion) )
		{
			Oferta oferta = buscarOfertabyNombre(institucion.getMyOfertas(), nombreOferta);
			
			for ( SolicitudEmp emp : oferta.getSolicitudEmps() )
			{
				if ( emp.estado )
				{
					int cantidadCoincidencias = sumaCoincidencia(emp, oferta);
					float totalCoincidencia = (cantidadCoincidencias * 100f) / 9;
					Coincidencia coincidencia = new Coincidencia(emp, totalCoincidencia);
					listaMacheo.add(coincidencia);
				}else continue;
			}
		}
		listaMacheo.sort(Comparator.comparing(Coincidencia::getPorcentaje).reversed());
		return listaMacheo;
	}

	/*
		!Revisamos que la oferta exista y que siga abierta para poder calcular su
		!coincidencia, con respecto a los postulantes
	*/
	private boolean estadoOferta ( String nombreOferta, Institucion institucion )
	{
		for( Oferta o : institucion.getMyOfertas() )
			if ( o.getPuesto().equalsIgnoreCase(nombreOferta) && o.isEstado() )
				return true;
		return false;
	}

	/*
		!BUscamos la oferta especifica que se va a calcular la coincidencia
	 */
	private Oferta buscarOfertabyNombre ( ArrayList<Oferta> listOferta, String nombreOferta )
	{
		for ( Oferta o : listOferta )
			if( o.getPuesto().equalsIgnoreCase(nombreOferta) )
				return o;
		return null;
	} 

	/*
		!Sumamos todas laas coincidencia que se a encontrado en ese solicitante y la oferta realizada 
	*/
	private int sumaCoincidencia ( SolicitudEmp emp, Oferta oferta )
	{
		int cantidadCoincidencias = 0;

		if ( emp.getModalidad().equalsIgnoreCase(oferta.getModalidad()) )	 cantidadCoincidencias++;
		if ( emp.getPersona().getSexo().equalsIgnoreCase(oferta.getSexo()) ) cantidadCoincidencias++;
		if ( emp.getPersona().isDispResidencia() )							 cantidadCoincidencias++;
		if ( emp.getPersona().isDispViajar() )                               cantidadCoincidencias++;
		if ( emp.getPersona().getPais().equalsIgnoreCase(oferta.getPais()) ) cantidadCoincidencias++;
		if ( emp.getPersona().calcularEdad() <= oferta.getEdad() )			 cantidadCoincidencias++;
		if ( emp.getPersona().calcularAniosExperiencia() >= oferta.getAniosExperiencia() ) cantidadCoincidencias++;
		if ( emp.getPersona().getDireccion().equalsIgnoreCase(oferta.getUbicacion()) ) cantidadCoincidencias++;
		if ( oferta.getProfesion().equalsIgnoreCase(puestoEmpSolicitante(emp)) ) cantidadCoincidencias++;

		return cantidadCoincidencias;
	}

	/*
		!Verificamos que clase es la persona que esta solicitando y devolvemos y String para comparar con la oferta 
	*/
	private String puestoEmpSolicitante ( SolicitudEmp emp )
	{
		if ( emp.getPersona() instanceof Universitario ) return "Universitario";
		if ( emp.getPersona() instanceof Tecnico )	return "Tecnico";
		if ( emp.getPersona() instanceof Obrero ) return "Obrero";
		return null;
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
	
	public Usuario crearUsuario( String correo, Institucion inst, Persona persona )
	{
		Random random = new Random();
		int index = correo.indexOf("@");
		String nombreUser = correo.substring(0, index).toUpperCase();
		String passUser = nombreUser.substring(0, 3) + "-" + String.format("%04d", random.nextInt(10000));

		return new Usuario(generarIdUsuario(), nombreUser, correo, passUser, inst, persona);
	} 
}
