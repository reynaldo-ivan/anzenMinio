package mx.com.anzen.minio.imple;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.xmlpull.v1.XmlPullParserException;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.Result;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.MinioException;
import io.minio.errors.NoResponseException;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import mx.com.anzen.minio.bean.Archivo;
import mx.com.anzen.minio.config.AppConfig;
import mx.com.anzen.minio.interfaces.IConexion;
import mx.com.anzen.minio.interfaces.IOperaciones;


/**
 * 
 * @author anzen
 *
 *clase que contiene los metodos con las diferentes operaciones
 */
public class Operaciones implements IOperaciones{ 
	
	ApplicationContext appContext=null;
	IConexion beanConexion=null;
	
	/**
	 * Valida si existe el nombre del nodo dado.
	 */
	public boolean existeNodo(String nombreNodo){
		
		appContext=new AnnotationConfigApplicationContext(AppConfig.class);
		beanConexion=(IConexion) appContext.getBean("conexionConfig"); 
		MinioClient minioClient=beanConexion.conexionMinio();
		
		boolean resultado=false;
		try {
			resultado = minioClient.bucketExists(nombreNodo);
			
		} catch (InvalidKeyException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (InvalidBucketNameException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (InsufficientDataException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (NoResponseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ErrorResponseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (InternalException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		((ConfigurableApplicationContext) appContext).close();
		return resultado;
	}
	
	
	/**
	 * sube el archivo en el server minio
	 */
	
	public String subirArchivo(String nodo,String ruta){
		String resultado="";
		 
		File archivo = new File(ruta); 
		 try {
			appContext=new AnnotationConfigApplicationContext(AppConfig.class);
		    beanConexion=(IConexion) appContext.getBean("conexionConfig"); 
			MinioClient minioClient=beanConexion.conexionMinio();
		        
		      try {
				minioClient.putObject(nodo,archivo.getName(),ruta);
				resultado="Ok";
			} catch (InvalidKeyException e) {
				resultado="Error: "+e.getMessage(); 
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				resultado="Error: "+e.getMessage();
				e.printStackTrace();
			} catch (IOException e) {
				resultado="Error: "+e.getMessage();
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				resultado="Error: "+e.getMessage();
				e.printStackTrace();
			} 
	  } catch(MinioException e) {
	    	resultado="Error: "+e.getMessage();
	  }
		 
		 return resultado;
	}

	
	/**
	 * Nos da una lista de los nodos que existen
	 */

	public List<Bucket> listaNodos() {
		 
		List<Bucket> bucketList = null;
		 try { 
			try {
				appContext=new AnnotationConfigApplicationContext(AppConfig.class);
			    beanConexion=(IConexion) appContext.getBean("conexionConfig"); 
				MinioClient minioClient=beanConexion.conexionMinio();
				 
				 
			     bucketList = minioClient.listBuckets();
			} catch (InvalidKeyException e) {
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
			}
		      
		    } catch (MinioException e) {
		      System.out.println("Error: " + e.getMessage());
		    }
		return bucketList;
	}


	/**
	 * NOs trae una lista de los archivos dando el nombre del nodo
	 */
	public ArrayList<Archivo> listaArchivos(String nombre) {
			
		Iterable<Result<Item>> myObjects = null;
		ArrayList<Archivo> archivos=new ArrayList<Archivo>();
			
		appContext=new AnnotationConfigApplicationContext(AppConfig.class);
	    beanConexion=(IConexion) appContext.getBean("conexionConfig"); 
		MinioClient minioClient=beanConexion.conexionMinio();
		      
		        
		try {
			myObjects = minioClient.listObjects(nombre);
			
			
			for (Result<Item> result : myObjects) {
		          Item item;
				try {
					Archivo archivo=new Archivo();
					item = result.get(); 
					 
					archivo.setFecha(item.lastModified());
					archivo.setNumeroArchivos(item.size());
					archivo.setNombreArchivo(item.objectName());
					  
					archivos.add(archivo); 
				} catch (InvalidKeyException e) {
					System.out.println("Error: "+e.getMessage());
					e.printStackTrace();
				} catch (InvalidBucketNameException e) {
					System.out.println("Error: "+e.getMessage());
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					System.out.println("Error: "+e.getMessage());
					e.printStackTrace();
				} catch (InsufficientDataException e) {
					System.out.println("Error: "+e.getMessage());
					e.printStackTrace();
				} catch (NoResponseException e) {
					System.out.println("Error: "+e.getMessage());
					e.printStackTrace();
				} catch (ErrorResponseException e) {
					System.out.println("Error: "+e.getMessage());
					e.printStackTrace();
				} catch (InternalException e) {
					System.out.println("Error: "+e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("Error: "+e.getMessage());
					e.printStackTrace();
				}
		          
		     }
					
			} catch (XmlPullParserException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			}
		         
		 
		return archivos;
	}

	/**
	 * Nos permite crear el nodo
	 */

	public String crearNodo(String nombreNodo) {
		String resultado="";
		 try {
		       
			 appContext=new AnnotationConfigApplicationContext(AppConfig.class);
			 beanConexion=(IConexion) appContext.getBean("conexionConfig"); 
			 MinioClient minioClient=beanConexion.conexionMinio();
			 
		      boolean found;
			try {
				found = minioClient.bucketExists(nombreNodo);
				 
				if (found) {
					resultado="ya existe";
			      } else { 
			        minioClient.makeBucket(nombreNodo);
			        resultado="Ok";
			      }
			} catch (InvalidKeyException e) {
				resultado="Error: "+e.getMessage();
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				resultado="Error: "+e.getMessage();
				e.printStackTrace();
			} catch (IOException e) {
				resultado="Error: "+e.getMessage();
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				resultado="Error: "+e.getMessage();
				e.printStackTrace();
			}
		      
		    } catch (MinioException e) {
		    	resultado="Error: "+e.getMessage();
		    }
		return resultado;
	}
	
	/**
	 * 
	 * @param nombreNodo
	 * @return
	 * 
	 * Nos permite eliminar en nodo
	 * 
	 */
	public String eliminaNodo(String nombreNodo){
		String resultado="";
		try {
			appContext=new AnnotationConfigApplicationContext(AppConfig.class);
		    beanConexion=(IConexion) appContext.getBean("conexionConfig"); 
			MinioClient minioClient=beanConexion.conexionMinio();

		      boolean found;
			try {
				found = minioClient.bucketExists(nombreNodo);
				if (found) {
			        minioClient.removeBucket(nombreNodo);
			        resultado="El nodo fue eliminado";
			      } else {
			    	resultado="El nodo fue eliminado";
			      }
			} catch (InvalidKeyException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			}
		      
		    } catch (MinioException e) {
		    	System.out.println("Error: "+e.getMessage());
		    }
		return resultado;
	}
	
	/**
	 * 
	 * @param nombreNodo
	 * @param nombreArchivo
	 * @param tiempoVida
	 * @return url
	 * 
	 * nos genera una url 
	 * 
	 * 
	 */
	public String generaUrl(String nombreNodo,String nombreArchivo, int tiempoVida){
		String url = null;
		try {
			appContext=new AnnotationConfigApplicationContext(AppConfig.class);
		    beanConexion=(IConexion) appContext.getBean("conexionConfig"); 
			MinioClient minioClient=beanConexion.conexionMinio();  
			   
		    try {
				url = minioClient.presignedGetObject(nombreNodo, nombreArchivo,tiempoVida);
			} catch (InvalidKeyException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			}  
		    } catch (MinioException e) {
		      System.out.println("Error occurred: " + e.getMessage());
		    }
		  
		return url;
	}
	
	
	
	/**
	 * 
	 * @param nombreNodo
	 * @param nombreArchivo
	 * @param tiempoVida
	 * @return
	 * 
	 * genera la url usando el metodo Put
	 */
	
	
	public String generaUrlPut(String nombreNodo,String nombreArchivo, int tiempoVida){
		String url = null;
		try {
			appContext=new AnnotationConfigApplicationContext(AppConfig.class);
		    beanConexion=(IConexion) appContext.getBean("conexionConfig"); 
			MinioClient minioClient=beanConexion.conexionMinio();   

			
		       try {
				url = minioClient.presignedPutObject(nombreNodo,nombreArchivo,tiempoVida);
			} catch (InvalidKeyException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			}
		       
		    } catch (MinioException e) {
		      System.out.println("Error : " + e.getMessage());
		    }
		return url;
	}
	
	
	
	/**
	 * metodo que nos sube el archivo, valida si existe el archivo, si no existe el nodo lo crea y sube el archivo
	 */
	
	public String crearUrl(String nodo,String ruta,int tiempoVida){
		String url=null;
		
		File archivo = new File(ruta); 
		
		String nodoCrea=crearNodo(nodo);
		
		if("ya existe".equalsIgnoreCase(nodoCrea)||"Ok".equalsIgnoreCase(nodoCrea)){
			
			String resultado=subirArchivo( nodo, ruta); 
			if(resultado.equalsIgnoreCase("Ok")){
				url=generaUrl(nodo, archivo.getName(), tiempoVida);
				
//				String respuestaElimina=eliminaArchivo(nodo,archivo.getName());
//				System.out.println("Eliminacion: "+respuestaElimina);
				
			}else{
				url=resultado;
			}
		}
		  
		return url;
		
	}
	
	
	/**
	 * elimina el archivo. pasandole como parametro el nombre del nodo y el nombre del archivo.
	 */
	public String eliminaArchivo(String nodo, String nombreArchivo){
		String resultado="";
		try { 
			appContext=new AnnotationConfigApplicationContext(AppConfig.class);
		    beanConexion=(IConexion) appContext.getBean("conexionConfig"); 
			MinioClient minioClient=beanConexion.conexionMinio();  
			
			  try {
				minioClient.removeObject(nodo,nombreArchivo);
				resultado="Ok";
				
			} catch (InvalidKeyException e) {
				resultado=e.getMessage();
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				resultado=e.getMessage();
				e.printStackTrace();
			} catch (IOException e) {
				resultado=e.getMessage();
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				resultado=e.getMessage();
				e.printStackTrace();
			} 
		    } catch (MinioException e) {
		    	resultado=e.getMessage();
		    }
		return resultado;
	}
	
	
	/**
	 * nos trae los datos de el archivo pasandole como parametro el nombre del nodo y el nombre del archivo.
	 */
	public ObjectStat datosArchivo(String nodo, String nombreArchivo){
		ObjectStat objectStat=null;
		 
		appContext=new AnnotationConfigApplicationContext(AppConfig.class);
	    beanConexion=(IConexion) appContext.getBean("conexionConfig"); 
		MinioClient minioClient=beanConexion.conexionMinio();  
		
		try {
		      try {
				objectStat = minioClient.statObject(nodo, nombreArchivo);
			} catch (InvalidKeyException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			} 
		    } catch (MinioException e) {
		    	System.out.println("Error: "+e.getMessage());
		    }
		 
		return objectStat;
		
	}

}
