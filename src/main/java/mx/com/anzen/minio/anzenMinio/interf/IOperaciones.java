package mx.com.anzen.minio.anzenMinio.interf;

import java.util.ArrayList;
import java.util.List;

import io.minio.messages.Bucket;
import mx.com.anzen.minio.bean.Archivo;

public interface IOperaciones {
	
	public boolean existeNodo(String nombreNodo);
	
	public String subirArchivo(String nodo,String nombreArchivo,String ruta);
	
	public List<Bucket> listaNodos();
	
	public ArrayList<Archivo> listaArchivos(String nombre);
	
	public String crearNodo(String nombreNodo);

}
