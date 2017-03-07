package mx.com.anzen.minio.bean;

import mx.com.anzen.minio.interfaces.IOperaciones;

public class PrincipalBean {
	
	private IOperaciones iOperaciones;
	private Credencial credencial;
	
	
	public IOperaciones getiOperaciones() {
		return iOperaciones;
	}
	public void setiOperaciones(IOperaciones iOperaciones) {
		this.iOperaciones = iOperaciones;
	}
	public Credencial getCredencial() {
		return credencial;
	}
	public void setCredencial(Credencial credencial) {
		this.credencial = credencial;
	}
	
	
	

}
