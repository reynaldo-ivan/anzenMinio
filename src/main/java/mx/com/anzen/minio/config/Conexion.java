package mx.com.anzen.minio.config;

import io.minio.MinioClient;
import io.minio.errors.MinioException;
import mx.com.anzen.minio.interfaces.IConexion; 



/**
 * 
 * @author anzen
 *
 *Clase que realiza la conexion hacia el server de minio
 */

public class Conexion implements IConexion{
	
	
//	public MinioClient conexionMinio(String url, String accessKey, String secretKey){
		public MinioClient conexionMinio(){
		
		MinioClient minioClient=null;
		
		try {
		       minioClient = new MinioClient("https://play.minio.io:9000", "Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG");
 
		    } catch (MinioException e) {
		      System.out.println("Error occurred: " + e);
		    }
		
		return minioClient;
	}

}
