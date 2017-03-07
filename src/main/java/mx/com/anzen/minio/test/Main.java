package mx.com.anzen.minio.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.Result;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import mx.com.anzen.minio.bean.Archivo;
import mx.com.anzen.minio.bean.PrincipalBean;
import mx.com.anzen.minio.config.AppConfig;
import mx.com.anzen.minio.config.Conexion;
import mx.com.anzen.minio.imple.Operaciones;
import mx.com.anzen.minio.interfaces.IOperaciones;

public class Main {
	
	
	
	
	
	
	public static void main(String[] args) {
		
		ApplicationContext appContext=null;
		IOperaciones beanPrincipal=null;
		
		appContext=new AnnotationConfigApplicationContext(AppConfig.class);
		beanPrincipal=(IOperaciones) appContext.getBean("coreConfig");
		
//		String res=beanPrincipal.crearNodo("rr");
//		System.out.println("res "+res);
		
		String url=beanPrincipal.crearUrl("banca", "/home/anzen/Escritorio/contrato.doc", 30); 
		System.out.println(url);
		
		
		 /**
		  * Nos traeun ObjectStat con los datos del archivo
		  */
		
		
//		ObjectStat object= beanPrincipal.datosArchivo("banca","contrato.doc"); 
//		System.out.println("nodo alm "+object.bucketName()+"\ntipo archivo "+object.contentType()+"\nnombre arch  " +object.name()+"\nfecha creacion "+object.createdTime());
		
		
		((ConfigurableApplicationContext) appContext).close();
		
		Conexion conexion=new Conexion();
		Operaciones operaciones=new Operaciones();
		
		//Valida si existe el nodo o no
//		boolean resultado=operaciones.existeNodo("rey");
//		System.out.println("resultado  "+resultado);
		
		
		//Nos permite subir un archivo a el server minio
//		String resultadosubir=operaciones.subirArchivo("docker","contrato.doc","/home/anzen/Escritorio/contrato.doc");
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
//		String resultadoNodo= operaciones.crearNodo("anzen"); 
//		System.out.println("resss "+resultadoNodo);
		
		
		
		//nos genera una url del archivo 
//		String url= operaciones.generaUrl("rey","anzen.jpg",20);
//		System.out.println(url);
		 
//		String url= operaciones.generaUrlPut("rey","anzen.jpg",30);
//		System.out.println(url);
		
//		String url=operaciones.generaUrl("rey", "contrato.doc", "/home/anzen/Escritorio/contrato.doc", 30);
//		System.out.println(url);
	 
		
		
		
	}

}
