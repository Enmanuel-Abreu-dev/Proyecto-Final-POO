package logico;

import java.io.Serializable;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
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
	
	// Managment:
	/*
	 Verifica que la contraseña recibida sea igual a la guardad
	 @return confirmacion de coincidencia 
	*/
	public boolean autenticar(String pass) {
		return this.password.equals(pass);
	}
	
	/*
	 Se encarga de cambiar el pass a una nueva verificando la anterior coincida con la anterior recibida
	 @return confirmacion de si se pudo cambiar o no el pass 
	*/
	public boolean cambiarPassword(String actual, String nueva) {
		if (!this.password.equals(actual) || !this.password.equals(nueva))
			return false;
		
		this.password = nueva;
		return true;
	}
}
