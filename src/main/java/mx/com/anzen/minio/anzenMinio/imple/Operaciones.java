package mx.com.anzen.minio.anzenMinio.imple;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.MinioException;
import io.minio.errors.NoResponseException;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import mx.com.anzen.minio.anzenMinio.config.Conexion;
import mx.com.anzen.minio.anzenMinio.interf.IOperaciones;
import mx.com.anzen.minio.bean.Archivo;

public class Operaciones implements IOperaciones{
	
	/**
	 * Valida si existe el nombre del nodo dado.
	 */
	public boolean existeNodo(String nombreNodo){
		
		Conexion conexion=new Conexion(); 
		MinioClient minioClient=conexion.conexionMinio();
		
		boolean resultado=false;
		try {
			resultado = minioClient.bucketExists(nombreNodo);
			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidBucketNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InsufficientDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ErrorResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	
	/**
	 * sube el archivo en el server minio
	 */
	
	public String subirArchivo(String nodo,String nombreArchivo,String ruta){
		String resultado="";
		 try {
			 Conexion conexion=new Conexion(); 
				MinioClient minioClient=conexion.conexionMinio();
		      
		      try {
				minioClient.putObject(nodo,nombreArchivo,ruta);
				resultado="Archivo Almacenado Correctamente";
			} catch (InvalidKeyException e) {
				resultado="Error: "+e; 
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				resultado="Error: "+e;
				e.printStackTrace();
			} catch (IOException e) {
				resultado="Error: "+e;
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				resultado="Error: "+e;
				e.printStackTrace();
			} 
	  } catch(MinioException e) {
	    	resultado="Error: "+e;
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
				 Conexion conexion=new Conexion(); 
				 MinioClient minioClient=conexion.conexionMinio();
			     bucketList = minioClient.listBuckets();
			} catch (InvalidKeyException e) {
				System.out.println("Error: " + e);
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Error: " + e);
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Error: " + e);
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				System.out.println("Error: " + e);
				e.printStackTrace();
			}
		      
		    } catch (MinioException e) {
		      System.out.println("Error: " + e);
		    }
		return bucketList;
	}


	/**
	 * NOs trae una losta de los archivos dando el nombre del nodo
	 */
	public ArrayList<Archivo> listaArchivos(String nombre) {
			
		Iterable<Result<Item>> myObjects = null;
		ArrayList<Archivo> archivos=new ArrayList<Archivo>();
			
		Conexion conexion=new Conexion(); 
		MinioClient minioClient=conexion.conexionMinio();
		     
		        
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidBucketNameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InsufficientDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoResponseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ErrorResponseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InternalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		          
		     }
					
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
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
		       
			 Conexion conexion=new Conexion(); 
			 MinioClient minioClient=conexion.conexionMinio();
			 
		      boolean found;
			try {
				found = minioClient.bucketExists(nombreNodo);
				 
				if (found) {
					resultado="El nodo ya existe";
			      } else { 
			        minioClient.makeBucket(nombreNodo);
			        resultado="El nodo fue creado con exito";
			      }
			} catch (InvalidKeyException e) {
				resultado="Error: "+e;
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				resultado="Error: "+e;
				e.printStackTrace();
			} catch (IOException e) {
				resultado="Error: "+e;
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				resultado="Error: "+e;
				e.printStackTrace();
			}
		      
		    } catch (MinioException e) {
		    	resultado="Error: "+e;
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
			Conexion conexion=new Conexion(); 
			 MinioClient minioClient=conexion.conexionMinio();

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      
		    } catch (MinioException e) {
		      System.out.println("Error occurred: " + e);
		    }
		return resultado;
	}
	

}
