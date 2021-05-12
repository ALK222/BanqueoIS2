package subscuentas;

import java.util.List;

import cuentadao.control.Cuenta;
import cuentadao.control.IFachadaDAOCuentas;
import usuariosdao.control.Persona;

public class SASubsCuentas implements ISASubsCuentas {
	List<Cuenta> _listaCuentas;
	List<Persona> _listaPersonas;
	IFachadaDAOCuentas cuenta;

	public boolean altaCuenta(Cuenta c) {
		return tarjeta.altaCuenta(c);

	}

	public boolean bajaCuenta(int num_cuenta) {
		return cuenta.bajaCuenta(num_cuenta);

	}

	public List<Cuenta> consultarListaCuentas(String titular_cuenta, String dni){// quitaremos el titular_cuenta
		
		return cuenta.consultarListaCuentas(titular_cuenta, String dni);
	}

	public Cuenta buscarCuenta(int num_cuenta) {

		return cuenta.buscarCuenta(num_cuenta);
	}

	public boolean modificarCuenta(Cuenta c) {

		return cuenta.modificarCuenta(t) == null ? null : true;
	}

	@Override
	public Cuenta buscaCuenta(int numeroCuenta) {
		return null;
	}


}
