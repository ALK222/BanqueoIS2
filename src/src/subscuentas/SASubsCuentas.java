package subscuentas;

import java.util.List;

import cuentadao.control.IFachadaDAOCuentas;
import dominio.Cuenta;
import dominio.Persona;

public class SASubsCuentas implements ISASubsCuentas {
	List<Cuenta> _listaCuentas;
	List<Persona> _listaPersonas;
	IFachadaDAOCuentas cuenta;

	@Override
	public boolean altaCuenta(Cuenta c) {
		return cuenta.altaCuenta(c);

	}

	@Override
	public boolean bajaCuenta(Cuenta c) {
		return cuenta.bajaCuenta(c);

	}

	@Override
	public List<Cuenta> consultarListaCuentas(String titular_cuenta, String dni) {// quitaremos el titular_cuenta
		return cuenta.buscarListaCuentas(titular_cuenta, dni);
	}

	@Override
	public boolean modificarCuenta(Cuenta c) {

		return cuenta.modificarCuentas(c) == true ? true : false;
	}

	@Override
	public Cuenta buscaCuenta(int numeroCuenta) {
		return cuenta.consultarCuenta(numeroCuenta);
	}

}
