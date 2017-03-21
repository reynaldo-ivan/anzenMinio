package mx.com.anzen.minio.interfaces;

import io.minio.MinioClient;
import mx.com.anzen.minio.bean.Credencial;

public interface IConexion {
	
	public Credencial credencialesPorperties();
	
	public MinioClient conexionMinio();
	  
	public Credencial servicioCredenciales(String url);
	
	

}
