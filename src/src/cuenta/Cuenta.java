package cuenta;

import java.util.List;

import org.json.JSONArray;

import prestamos.Prestamo;
import tarjetas.Tarjeta;

public class Cuenta {
 
	private String titularCuenta; 
	private int numeroCuenta; 
	private double saldo; 
	private String firmaDigital; 
	private JSONArray movimientos; 
	private List<Tarjeta> listaTarjetas; 
	private List<Prestamo> listaPrestamos;
	
	
	
	public Cuenta(String titularCuenta, int numeroCuenta, double saldo, String firmaDigital,List<Tarjeta> listaTarjetas, List<Prestamo> listaPrestamos) {
		this.titularCuenta = titularCuenta;
		this.numeroCuenta = numeroCuenta;
		this.saldo = saldo;
		this.firmaDigital = firmaDigital;
		this.listaTarjetas = listaTarjetas; // lo mas probable es que el ArrayList se crea aqui y no se pasa como parametro 
 		this.listaPrestamos = listaPrestamos;
 		this.movimientos = new JSONArray();
	}
	
	
	public String getTitular_cuenta() {
		return titularCuenta;
	}
	public void setTitular_cuenta(String titularCuenta) {
		this.titularCuenta = titularCuenta;
	}
	public int getNumero_cuenta() {
		return numeroCuenta;
	}
	public void setNumero_cuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public String getFirma_digital() {
		return firmaDigital;
	}
	public void setFirma_digital(String firmaDigital) {
		this.firmaDigital = firmaDigital;
	}
	public List<Tarjeta> getListaTarjetas() {
		return listaTarjetas;
	}
	public void setListaTarjetas(List<Tarjeta> listaTarjetas) {
		this.listaTarjetas = listaTarjetas;
	}
	public List<Prestamo> getListaPrestamos() {
		return listaPrestamos;
	}
	public void setListaPrestamos(List<Prestamo> listaPrestamos) {
		this.listaPrestamos = listaPrestamos;
	}


	public JSONArray getMovimientos() {
		return movimientos;
	}


	public void setMovimientos(JSONArray movimientos) {
		this.movimientos = movimientos;
	} 
	
	
	
	
}
