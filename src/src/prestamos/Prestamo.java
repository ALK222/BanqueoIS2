package prestamos;

import java.util.Date;

public class Prestamo {
	
	private double cantidadSolicitada; 
	private Date plazoDevolucion;
	private int aval; 
	private String profesion; 
	private boolean firmaSeguroDefuncion; 
	private Estado estadoPrestamo;
	
	
	public Prestamo(double cantidadSolicitada, Date plazoDevolucion, int aval, String profesion,boolean firmaSeguroDefuncion, Estado estadoPrestamo) {
		this.cantidadSolicitada = cantidadSolicitada;
		this.plazoDevolucion = plazoDevolucion;
		this.aval = aval;
		this.profesion = profesion;
		this.firmaSeguroDefuncion = firmaSeguroDefuncion;
		this.estadoPrestamo = estadoPrestamo;
	}
	
	
	public double getCantidad_solicitada() {
		return cantidadSolicitada;
	}
	public void setCantidad_solicitada(double cantidadSolicitada) {
		this.cantidadSolicitada = cantidadSolicitada;
	}
	public Date getPlazo_devolucion() {
		return plazoDevolucion;
	}
	public void setPlazo_devolucion(Date plazoDevolucion) {
		this.plazoDevolucion = plazoDevolucion;
	}
	public int getAval() {
		return aval;
	}
	public void setAval(int aval) {
		this.aval = aval;
	}
	public String getProfesion() {
		return profesion;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	public boolean isFirma_seguro_defuncion() {
		return firmaSeguroDefuncion;
	}
	public void setFirma_seguro_defuncion(boolean firmaSeguroDefuncion) {
		this.firmaSeguroDefuncion = firmaSeguroDefuncion;
	}
	public Estado getEstado_prestamo() {
		return estadoPrestamo;
	}
	public void setEstado_prestamo(Estado estadoPrestamo) {
		this.estadoPrestamo = estadoPrestamo;
	}
	
	
	
}
