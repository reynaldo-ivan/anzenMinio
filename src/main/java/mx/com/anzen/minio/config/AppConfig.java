package mx.com.anzen.minio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mx.com.anzen.minio.imple.Operaciones;
import mx.com.anzen.minio.interfaces.IConexion;
import mx.com.anzen.minio.interfaces.IOperaciones;

@Configuration
public class AppConfig {
	
	
	@Bean(name="coreConfig")
	public IOperaciones coreConfig(){
		return new Operaciones();
	}
	 
	
	@Bean(name="conexionConfig")
	public IConexion conexionConfig(){
		return new Conexion();
	}
	 	

}