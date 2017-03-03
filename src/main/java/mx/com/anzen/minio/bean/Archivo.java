package mx.com.anzen.minio.bean;

import java.util.Date;

public class Archivo {
	
	
	private Date fecha;
	private int numeroArchivos;
	private String nombreArchivo;
	
	
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getNumeroArchivos() {
		return numeroArchivos;
	}
	public void setNumeroArchivos(int numeroArchivos) {
		this.numeroArchivos = numeroArchivos;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	
	
	
	
	
	

}
