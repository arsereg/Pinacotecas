package CapaAccesoBD;

/**
 *Clase Conector
 *@version 1.0
 *@author Laura Monge Izaguirre
 *Clase que inicializa la conexión con
 *los valores correctos y
 *permite manejar una única
 *conexión para todo el proyecto y
 *
 */
 
public class Conector{
	//atributo de la clase	
	private static AccesoDB conectorBD = null;
	
	/**
	 *Método estático que devuelve el 
	 *objeto AccesoBD para que sea utilizado
	 *por las clases
	 *@return objeto del tipo AccesoBD del paquete 
	 *CapaAccesoDatos
	 */
	 
//	public static AccesoBD getConector() throws 
//	java.sql.SQLException,Exception{
//		if (conectorBD == null){			
////			conectorBD = new AccesoBD("sun.jdbc.odbc.JdbcOdbcDriver","jdbc:odbc:BDCxC","sa","jass2002");
//			conectorBD = new AccesoBD("sun.jdbc.odbc.JdbcOdbcDriver","jdbc:odbc:BDCxCAccess","","");
//		}
//		return conectorBD;
//	}
//	
	
	public static AccesoDB getConector() throws java.sql.SQLException,Exception{
		if(conectorBD==null){
			conectorBD = new AccesoDB("net.ucanaccess.jdbc.UcanaccessDriver","jdbc:ucanaccess://C:/Users/Gino/Desktop/BaseDeDatos/prueba/BaseDeDatos.accdb");
		}
		return conectorBD;
	}
	
}