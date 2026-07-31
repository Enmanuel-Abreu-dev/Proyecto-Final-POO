package logico;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import javax.imageio.ImageIO;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class BolsaTrabajo implements Serializable {
	private static final long serialVersionUID = 1L;
	
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
	
    protected Object readResolve() {
        if (instance == null) {
            instance = this; 
        }
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

	public void registrarOferta(Oferta o) {
		ofertas.add(o);
		
	}
	
	public void registrarSolicitud(Solicitud soli) {
		this.solicitudes.add(soli);
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

		if (usuarioActual.getMyInstitucion() == null)
			return null;
		

		for (Usuario users : usuarios) 
		{
			System.out.println("Cantidad usuarios totales: " + usuarios.size());
			System.out.println("Nombre: " + users.getNombre());
			if (users.getMyInstitucion() != null && users.getMyInstitucion().getNombre().equalsIgnoreCase(usuarioActual.getMyInstitucion().getNombre())) 
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
			
			for ( Persona p : personas )
			{
				if ( !p.empleado )
				{
					int cantidadCoincidencias = sumaCoincidencia(p, oferta);
					float totalCoincidencia = (cantidadCoincidencias * 100f) / 85f;
					Coincidencia coincidencia = new Coincidencia(p, totalCoincidencia);
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
	private int sumaCoincidencia ( Persona pers, Oferta oferta )
	{
		int cantidadCoincidencias = 0;
		System.out.println(pers.getNombre());
		System.out.println(pers.getSexo());

		if ( profesionPersona(pers).equalsIgnoreCase(oferta.getPuesto()) )
			cantidadCoincidencias += 20;
		if ( pers.getSexo().equalsIgnoreCase(oferta.getSexo()) )
			cantidadCoincidencias += 5;
		if ( pers.isDispViajar() )							 
			cantidadCoincidencias += 5;
		if ( pers.isDispResidencia() ) 
			cantidadCoincidencias += 5;
		if ( pers.getPais().equalsIgnoreCase(oferta.getPais()) ) 
			cantidadCoincidencias += 10;
		if ( pers.calcularEdad() <= oferta.getEdad() )			 
			cantidadCoincidencias += 5;
		if ( pers.calcularAniosExperiencia() >= oferta.getAniosExperiencia() ) 
			cantidadCoincidencias += 10;
		if ( pers.getDireccion().equalsIgnoreCase(oferta.getUbicacion()) ) 
			cantidadCoincidencias += 5;
		if ( puestoEmpSolicitante(pers) ) 
			cantidadCoincidencias += 20;

		return cantidadCoincidencias;
	}

	/*
		!Verificamos que clase es la persona que esta solicitando y devolvemos y String para comparar con la oferta 
	*/
	private boolean puestoEmpSolicitante ( Persona p )
	{
		if ( p instanceof Universitario ) return true;
		if ( p instanceof Tecnico )	return true;
		if ( p instanceof Obrero ) return true;
		return false;
	}

	private String profesionPersona ( Persona p )
	{
		if ( p.getClass() == Universitario.class )
		{
			Universitario u = (Universitario) p;
			return u.getCarrera();
		}
		else if ( p.getClass() == Tecnico.class )
		{
			Tecnico t = (Tecnico) p;
			return t.getEspecialidad();
		}
		else if ( p.getClass() == Obrero.class )
		{
			Obrero o = (Obrero) p;
			return o.getProfesion();
		}

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
	
	/*
		!Le otorga las credenciales a al nuevo usuario creado 
	*/
	public Usuario crearUsuario( String correo, Institucion inst, Persona persona )
	{
		Random random = new Random();
		int index = correo.indexOf("@");
		String nombreUser = correo.substring(0, index).toUpperCase();
		String passUser = nombreUser.substring(0, 3) + "-" + String.format("%04d", random.nextInt(10000));

		return new Usuario(generarIdUsuario(), nombreUser, correo, passUser, inst, persona);
	} 

	/*
		!Busca la imagen en el buscardor del sistema operativo, la imagen seleccionada, se guarda en una 
		!carpeta especial con un identificador asociado a la imagen
	*/
	public String buscarImagen( String nombre, String id )
	{
		FileDialog fileDialog = new FileDialog((Frame) null, "Seleccionar Imagen: ");
		fileDialog.setFile("*.jpg; *.jpeg; *.png;");
		fileDialog.setVisible(true);

		String directorio = fileDialog.getDirectory();
		String nombreArchivo = fileDialog.getFile();

		if ( nombreArchivo != null )
		{
			File archivo = new File(directorio, nombreArchivo);
			String carpetaDestino = "ImagenesUsers";
			String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf("."));
			String nuevoNombre =  nombre + id + extension;

			try {
				Files.createDirectories(Paths.get(carpetaDestino));

				Path destino = Paths.get(carpetaDestino, nuevoNombre);
				Files.copy(archivo.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);

				System.out.println("Imagen guardada: " + destino.toAbsolutePath());
				return destino.toString();
				
			} catch (IOException e) {
				System.out.println("Error al guardar la Imagen: " + e.getMessage());
				return null;
			}
		}
		else System.out.println("No se selecciono imagen");
		return null;
	}

	/*
		!Carga la imagen que esta asociada a la persona
	*/
	public BufferedImage cargarImagenPersona( Persona p )
	{
		try {
			File archivo = new File(p.getRutaImagen());

			if ( !archivo.exists() )
			{
				System.out.println("No existe el archivo.");
				return null;
			}			

			BufferedImage imagen = ImageIO.read(archivo);
			return imagen;

		} catch (Exception e) {
			System.out.println("Error al cargar la imagen: " + e.getMessage());
			return null;
		}
	}

	/*
		!Carga la imagen que esta asociada a la Institucion
	*/
	public BufferedImage cargarImagenInstitucion( Institucion i )
	{
		try {
			File archivo = new File(i.getRutaImagen());

			if ( !archivo.exists() )
			{
				System.out.println("No existe el archivo.");
				return null;
			}			

			BufferedImage imagen = ImageIO.read(archivo);
			return imagen;
			
		} catch (Exception e) {
			System.out.println("Error al cargar la imagen: " + e.getMessage());
			return null;
		}
	}

	/*
		!Devolvemos todas las ofertas de una empresa en especifico
	*/
	public ArrayList<Oferta> listOfertaEmpresa ( String registroSocialEmp )
	{
		ArrayList<Oferta> lista = new ArrayList<>();
		for ( Oferta o : ofertas )
			if ( o.getMyEmpresa().getRegistroSocial().equalsIgnoreCase(registroSocialEmp) )
				lista.add(o);

		return lista;
	}

	/*
		!Modificamos una empresa 
	*/
	public void modificarEmpresa ( Institucion myInsti )
	{
		int index = buscarEmpresa(myInsti.getIdentificador());
		instituciones.set(index, myInsti);
	}

	public void modificarPersona ( Persona myPerson )
	{
		int index = buscarPersona(myPerson.getIdentificador());
		personas.set(index, myPerson);
	}
	/*
		!buscamos el id de la empresa para poder modificar la empresa
	*/
	private int buscarEmpresa(String identificador) {
		int i = 0, index = 0;
		boolean encontrar = false;
		
		while ( !encontrar && i < instituciones.size() )
		{
			if ( instituciones.get(i).getIdentificador().equalsIgnoreCase(identificador) )
			{
				index = i;
				encontrar = true;
			}
			i++;
		}

		return index;
	}	

	private int buscarPersona(String identificador) {
		int i = 0, index = 0;
		boolean encontrar = false;
		
		while ( !encontrar && i < personas.size() )
		{
			if ( personas.get(i).getIdentificador().equalsIgnoreCase(identificador) )
			{
				index = i;
				encontrar = true;
			}
			i++;
		}

		return index;
	}

	/*
		!Metodo para eliminar la imagen en la ruta especifica
	*/
	public boolean eliminarArchivo ( Institucion i )
	{
		try 
		{
			if ( i.getRutaImagen() == null || i.getRutaImagen().trim().isEmpty() ) return true;
		
			Path ruta = Paths.get(i.getRutaImagen());
			Files.delete(ruta);
			return true;	

		} catch (Exception e) {
			System.out.println("Error al eliminar Imagen");
			return false;
		}
	}
	
	public ArrayList<Oferta> getOfertasRecomendadasByPersona(Persona persona) {
		ArrayList<Oferta> result = new ArrayList<Oferta>();
		
		for (Oferta actual : this.ofertas) {
			if (actual.isEstado() && actual.getPais().equalsIgnoreCase(persona.getPais()) && actual.getAniosExperiencia() == persona.calcularAniosExperiencia())
				result.add(actual);
		}
		return result;
	}

	public static void guardarDatos() 
	{
		try (ObjectOutputStream io = new ObjectOutputStream(new FileOutputStream("save.bin"))) {
			io.writeObject(BolsaTrabajo.getInstance());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Oferta getOfertaById(String id) {
		boolean encontrado = false;
		Oferta result = null;
		int indice = 0;
		
		while (!encontrado && indice < this.ofertas.size()) {
			if (this.ofertas.get(indice).getIdentificador().equalsIgnoreCase(id))
			{
				result = this.ofertas.get(indice);
				encontrado = true;
			}
			indice++;
		}
		
		return result;
	}
	
	public boolean puedeSolicitar(Oferta of, Persona per) {
		Oferta ofertaActual = this.getOfertaById(of.getIdentificador());
		boolean result = true;
		
		for (SolicitudEmp actual : ofertaActual.getSolicitudEmps()) {
			if (actual.getPersona().getIdentificador().equalsIgnoreCase(per.getIdentificador()))
				result = false;
		}
		return result;
	}

}
