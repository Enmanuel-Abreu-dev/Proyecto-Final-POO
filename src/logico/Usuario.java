package logico;

public class Usuario {
	private String id;
    private String nombre;
    private String correo;
    private String password;
    private Institucion myInstitucion;
    private Persona myPersona;
    
	public Usuario(String id, String nombre, String correo, String password, Institucion myInstitucion,
			Persona myPersona) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.password = password;
		this.myInstitucion = myInstitucion;
		this.myPersona = myPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Institucion getMyInstitucion() {
		return myInstitucion;
	}

	public void setMyInstitucion(Institucion myInstitucion) {
		this.myInstitucion = myInstitucion;
	}

	public Persona getMyPersona() {
		return myPersona;
	}

	public void setMyPersona(Persona myPersona) {
		this.myPersona = myPersona;
	}

	public String getId() {
        return id;
    }

}
