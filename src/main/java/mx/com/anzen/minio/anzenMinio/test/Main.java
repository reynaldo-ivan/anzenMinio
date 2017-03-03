package mx.com.anzen.minio.anzenMinio.test;

import java.util.ArrayList;
import java.util.List;

import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import mx.com.anzen.minio.anzenMinio.config.Conexion;
import mx.com.anzen.minio.anzenMinio.imple.Operaciones;
import mx.com.anzen.minio.bean.Archivo;

public class Main {
	
	
	
	public static void main(String[] args) {
		
		Conexion conexion=new Conexion();
		Operaciones operaciones=new Operaciones();
		 
		
		//Valida si existe el nodo o no
		boolean resultado=operaciones.existeNodo("rey");
		System.out.println("resultado  "+resultado);
		
		
		//Nos permite subir un archivo a el server minio
//		String resultadosubir=operaciones.subirArchivo("docker","k1.jpeg","/home/anzen/Imágenes/k1.jpeg");
//		System.out.println(resultadosubir);
		
		
		//nos trae todos los nodos que existen
//		List<Bucket> lista= operaciones.listaNodos(); 
//		for (Bucket bucket : lista) {
//	        System.out.println(bucket.creationDate() + ", " + bucket.name());
//	      }
		
		
		
		//nos trae la fecha y el nombre de todos los archivos de un nodo dado 
//		ArrayList<Archivo> listaOb= operaciones.listaArchivos("rey");
//		
//		for(int i=0;i<listaOb.size();i++){
//			System.out.println("nombre del archivo "+listaOb.get(i).getNombreArchivo()+"------------- fecha de creacion "+listaOb.get(i).getFecha());
//		}
		 
//		RemoveBucket
		//Crea un nodo nuevo
		String resultadoNodo= operaciones.crearNodo("anzen"); 
		System.out.println("resss "+resultadoNodo);
		
	 
		
		
		
	}

}
