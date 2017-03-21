package mx.com.anzen.minio.config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import mx.com.anzen.minio.bean.Credencial;
import mx.com.anzen.minio.interfaces.IConexion; 
 
/**
 * 
 * @author anzen
 *
 *Clase que realiza la conexion hacia el server de minio
 */

public class Conexion implements IConexion{
  
    
	
	/**
	 * Nos trae los atributos de el archivo properties
	 */
    public Credencial credencialesPorperties(){
    	
    	Credencial credencial=new Credencial();
    	Properties propiedades = new Properties();
        InputStream entrada = null;
    	 try {

    	        entrada = new FileInputStream("src/main/resources/configuracion.properties");
 
    	        propiedades.load(entrada); 
    	        credencial.setUrlRest(propiedades.getProperty("anzen.crecencials.urlrest"));
    	        credencial.setUrl(propiedades.getProperty("anzen.crecencials.url"));
    	        credencial.setAccessKey(propiedades.getProperty("anzen.crecencials.accessKey"));
    	        credencial.setSecretKey(propiedades.getProperty("anzen.crecencials.secretKey"));
    	        

    	    } catch (IOException ex) {
    	        ex.printStackTrace();
    	    } finally {
    	        if (entrada != null) {
    	            try {
    	                entrada.close();
    	            } catch (IOException e) {
    	                e.printStackTrace();
    	            }
    	        }
    	    }
    	 return credencial;
    }
    
	
	  /**
	   * Realiza la conexion hacia minio
	   */
	public MinioClient conexionMinio(){ 
		Credencial credenciales= credencialesPorperties();
    	  
		MinioClient minioClient=null;  
		Credencial credencial=null; 
		try {
			if(!credenciales.getUrlRest().equalsIgnoreCase("")){ 
				
				credencial=servicioCredenciales(credenciales.getUrlRest());
				System.out.println("url rest "+credencial.getUrl());
				minioClient = new MinioClient(credencial.getUrl(),
											  credencial.getSecretKey(),
											  credencial.getAccessKey()); 
				  
			}else{ 
				System.out.println("url "+credenciales.getUrl());
				minioClient = new MinioClient(credenciales.getUrl(),
						  credenciales.getAccessKey(),
						  credenciales.getSecretKey()
						  );
			}
		 
		    } catch (MinioException e) {
		      System.out.println("Error: " + e.getMessage());
		    }
		
//		((ConfigurableApplicationContext) appContext).close();
		return minioClient;
	}
	  
	/**
	 * Consume el servicio rest
	 */
	public Credencial servicioCredenciales(String datUrl) {
		
		Object object=null;
		JSONArray arrayObj=null;
		String output=null;  
		String json="";
		JSONParser jsonParser=null;
		Credencial credencial=null;
		
		 try {
				URL url = new URL(datUrl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
 
				while ((output = br.readLine()) != null) { 
					json=json+output; 
				}     
				jsonParser=new JSONParser();
				 
				try {
					credencial=new Credencial();
					object=jsonParser.parse(json.toString());  
					arrayObj=(JSONArray) object;  
					JSONObject obj2 = (JSONObject)arrayObj.get(0); 
					 
					credencial.setAccessKey(obj2.get("secretkey").toString());
					credencial.setSecretKey(obj2.get("accesskey").toString());
					credencial.setUrl(obj2.get("url").toString());
					 
				} catch (ParseException e) { 
					System.out.println("Error: "+e.getMessage());
				}
				conn.disconnect(); 
			  } catch (MalformedURLException e) { 
				System.out.println("Error: "+e.getMessage()); 
			  } catch (IOException e) { 
				System.out.println("Error: "+e.getMessage());

			  }
 
		return credencial;
	}
 

}
