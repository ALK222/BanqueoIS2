package usuarios;

import java.util.ArrayList;
import java.util.List;

import cuenta.Cuenta;

public class Cliente extends Persona{

	private String gestorDni;
	private List<Cuenta> listaCuentas; 

	public Cliente(String dni, String nombre, String domicilio, int tlf, String gestorDni) {
		super(dni, nombre, domicilio, tlf);
		this.gestorDni = gestorDni; 
		this.listaCuentas = new ArrayList<>(); // lo mas probable que asi si no con parametro 
	}

	public String getGestor_dni() {
		return gestorDni;
	}

	public void setGestor_dni(String gestorDni) {
		this.gestorDni = gestorDni;
	}

	public List<Cuenta> getListaCuentas() {
		return listaCuentas;
	}

	public void setListaCuentas(List<Cuenta> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}
	
	
	
	
}
