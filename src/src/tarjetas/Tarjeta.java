package tarjetas;

import java.util.Date;

public class Tarjeta {

	private String titular; 
	private int pin; 
	private boolean estado; 
	private int numTarjeta; 
	private Date fechaCad; 
	private Tipo tipoTarjeta;
	
	
	
	public Tarjeta(String titular, int pin, boolean estado, int numTarjeta, Date fechaCad, Tipo tipoTarjeta) {
		this.titular = titular;
		this.pin = pin;
		this.estado = estado;
		this.numTarjeta = numTarjeta;
		this.fechaCad = fechaCad;
		this.tipoTarjeta = tipoTarjeta;
	}
	
	
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public int getNum_tarjeta() {
		return numTarjeta;
	}
	public void setNum_tarjeta(int numTarjeta) {
		this.numTarjeta = numTarjeta;
	}
	public Date getFechaCad() {
		return fechaCad;
	}
	public void setFechaCad(Date fechaCad) {
		this.fechaCad = fechaCad;
	}
	public Tipo getTipo_tarjeta() {
		return tipoTarjeta;
	}
	public void setTipo_tarjeta(Tipo tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	} 
	
	
	
	
}
