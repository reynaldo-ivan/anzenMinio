package mx.com.anzen.minio.anzenMinio.config;

import io.minio.MinioClient;
import io.minio.errors.MinioException; 



/**
 * 
 * @author anzen
 *
 *Clase que realiza la conexion hacia el server de minio
 */

public class Conexion {
	
	
//	public MinioClient conexionMinio(String url, String accessKey, String secretKey){
		public MinioClient conexionMinio(){
		
		MinioClient minioClient=null;
		
		try {
		       minioClient = new MinioClient("http://127.0.0.1:9000", "Z7CQXCQO484CLJ3L7CJU", "i7rJaPg6lRuFbqU+Q72taf1E6Jij0mHJ4lbTv7fF");
 
		    } catch (MinioException e) {
		      System.out.println("Error occurred: " + e);
		    }
		
		return minioClient;
	}

}
