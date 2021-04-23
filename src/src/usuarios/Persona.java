package usuarios;

public class Persona {

	protected String dni;
	protected String nombre; 
	protected String domicilio; 
	protected String email; 
	protected int tlf;
	
	Persona(String dni, String nombre, String domicilio, int tlf){
		this.dni = dni; 
		this.nombre = nombre; 
		this.domicilio = domicilio; 
		this.tlf = tlf; 
	}
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTlf() {
		return tlf;
	}
	public void setTlf(int tlf) {
		this.tlf = tlf;
	} 
	
}
