package subscuentas;

import java.io.IOException;
import java.util.List;

import common.exception.UserException;
import common.misc.Pair;
import cuentadao.control.FachadaDAOCuentas;
import cuentadao.control.IFachadaDAOCuentas;
import dominio.Cuenta;
import dominio.Persona;

/**
 * Interprete del DAOCuentas
 */
public class SASubsCuentas implements ISASubsCuentas {
	List<Cuenta> _listaCuentas;
	List<Persona> _listaPersonas;
	IFachadaDAOCuentas cuenta;

	public SASubsCuentas() {
		cuenta = new FachadaDAOCuentas();
	}

	@Override
	public int altaCuenta(Cuenta c) {
		try {
			if (cuenta.consultarCuenta(c.getNumeroCuenta()) != null) {
				return 1;
			} else {
				cuenta.altaCuenta(c);
				return 0;
			}

		} catch (IOException e) {
			return 10;
		}
	}

	@Override
	public int bajaCuenta(Cuenta c) {
		try {
			if (cuenta.consultarCuenta(c.getNumeroCuenta()) == null) {
				return 1;
			} else {
				return cuenta.bajaCuenta(c) ? 0 : 1;
			}

		} catch (IOException e) {
			return 10;
		}

	}

	@Override
	public Pair<List<Cuenta>, Integer> consultarListaCuentas(String titularCuenta, String dni) {// quitaremos el
																								// titular_cuenta
		try {
			List<Cuenta> listaAux = cuenta.buscarListaCuentas(titularCuenta, dni);
			if (listaAux == null) {
				return new Pair<List<Cuenta>, Integer>(null, 1);
			} else {
				return new Pair<List<Cuenta>, Integer>(listaAux, 0);
			}

		} catch (IOException e) {
			return new Pair<List<Cuenta>, Integer>(null, 10);
		} catch (UserException e) {
			return new Pair<List<Cuenta>, Integer>(null, 2);
		}
	}

	@Override
	public int modificarCuenta(Cuenta c) {

		try {
			if (!cuenta.modificarCuentas(c)) {
				return 1;
			} else {
				return 0;
			}

		} catch (IOException e) {
			return 10;
		}
	}

	@Override
	public Pair<Cuenta, Integer> buscaCuenta(int numeroCuenta) {
		try {
			Cuenta cuentaBuscada = cuenta.consultarCuenta(numeroCuenta);
			if (cuentaBuscada == null) {
				return new Pair<Cuenta, Integer>(null, 1);
			} else {
				cuenta.consultarCuenta(numeroCuenta);
				return new Pair<Cuenta, Integer>(cuentaBuscada, 0);
			}

		} catch (IOException e) {
			return new Pair<Cuenta, Integer>(null, 10);
		}
	}

}
