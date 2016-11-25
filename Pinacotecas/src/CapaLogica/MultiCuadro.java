package CapaLogica;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import CapaAccesoBD.Conector;

public class MultiCuadro {
	
	
		 /**
	     * @param pNombreCuadro the pNombreCuadro to set
	     * @param pDimensionesCuadro the pDimensionesCuadro to set
	     * @param pfechaCreacion the pfechaCreacion to set
	     * @param ptecnicaPintura the pectnicaPitura to set
	     * @param pCondicionLlegada the pCondicionLlegada to set
	     * @param pCondicionActual the pCondicionActual to set
	     * @return miCuadro
	     */
		public  Cuadro crear(String pNombreCuadro,String pDimensionesCuadro ,LocalDate pfechaCreacion ,String pTecnicaDePintura, String pCondicionDeLlegada, String pCondicionActual,double pCosto) 
				throws java.sql.SQLException,Exception{
			Cuadro.setCantCuadros(contarCuadrosCreados());
			Cuadro miCuadro = new Cuadro (pNombreCuadro,pDimensionesCuadro,pfechaCreacion,pTecnicaDePintura,pCondicionDeLlegada,pCondicionActual,pCosto);
			String sql;
			String fecha = "#" + pfechaCreacion.getMonthValue()+ "/" + pfechaCreacion.getDayOfMonth()+ "/" +pfechaCreacion.getYear() + "#" ; 
			sql="INSERT INTO TCuadro "+
			"(idCuadro, nombreCuadro, dimensionesCuadro, fechaCreacion, tecnicaDePinturas, condicionesDeLlegada, condicionActual, costo)"+
			"VALUES ('"+miCuadro.getIdCuadro()+"','"+pNombreCuadro+"',"+pDimensionesCuadro+",'"+pfechaCreacion+"',"+pTecnicaDePintura+",'"+pCondicionDeLlegada+"',"+pCondicionActual+",'"+pCosto+"');";
			Conector.getConector().ejecutarSQL(sql);
			return miCuadro;
		}
		/**
	     * @return size
	     */
		public int contarCuadrosCreados()throws Exception{
			String sql;
			sql = "SELECT IdCuadros FROM TCuadros;";
			java.sql.ResultSet rs = Conector.getConector().ejecutarSQL(sql,true);
			int size =0;
			while(rs.next()){
				size++;
			}
			return size;
			
		}
		/**
	     * @param pid the pid to set
	     * 
	     * @return unCuadro
	     */
		public  Cuadro buscar(String pid) throws java.sql.SQLException,Exception{
			Cuadro unCuadro = null;
			java.sql.ResultSet rs;
			String sql;
			sql = "SELECT * "+
			"FROM TCuadro "+
			"WHERE idCuadro='"+pid+"';";
			rs = Conector.getConector().ejecutarSQL(sql,true);
			if (rs.next()){
				unCuadro = new Cuadro(
					rs.getString("nombreCuadro"),
					rs.getString("dimensionesCuadro"),
					rs.getDate("fechaCreacion").toLocalDate(),
					rs.getString("tecnica_Pintura"),
					rs.getString("condicion_Llegada"),
					rs.getString("condicion_Actual"),
					rs.getDouble("costo"),
					rs.getString("idCuadro"));
			} else {
				throw new Exception ("El abono no está registrado en el sistema.");
			}
			rs.close();
			return unCuadro;
		}
		/**
	     * @param pidCuadro the pidCuadro to set
	     * 
	     * @return unPintor
	     */
		
		public  Pintor buscarPintorPorCuadro(String pidCuadro) throws java.sql.SQLException,Exception{
			java.sql.ResultSet rs;
			String sql;
			Pintor unPintor=null;
			sql="SELECT * "+
			"FROM pintor "+
			"WHERE idPintor='"+pidCuadro+"';";
			Conector.getConector().ejecutarSQL(sql);
			rs = Conector.getConector().ejecutarSQL(sql,true);
			while (rs.next()){
				unPintor = new Pintor(
					rs.getString("nombre Pintor"),
					rs.getString("alias"),
					rs.getString("nacionalidad"),
					rs.getString("paisNacimientos"),
					rs.getString("ciudad Nacimiento"),
					rs.getDate("fechaNacimiento").toLocalDate(),
					rs.getDate("fecha fallecimeinto").toLocalDate(),
					rs.getString("id Pintor"),
					rs.getString("pmiMaestro"));
			}
			rs.close();
			return unPintor;
		}
		/**
	     * @param pidCuadro the pidCuadro to set
	     * 
	     * @return unaPinacoteca
	     */
		public  Pinacoteca buscarPinacotecaPorCuadro(String pidCuadro) throws java.sql.SQLException,Exception{
			java.sql.ResultSet rs;
                        String sql;
                        Pinacoteca unaPinacoteca = null;
                        sql="SELECT * "+
                        "FROM pinacoteca "+
                        "WHERE idPinacoteca='"+pidCuadro+"';";
                        Conector.getConector().ejecutarSQL(sql);
                        rs = Conector.getConector().ejecutarSQL(sql,true);
                        while (rs.next()){
                                unaPinacoteca = new Pinacoteca(
                                        rs.getDouble("metros"),
                                        rs.getDate("fecha inaguracion").toLocalDate(),
                                        rs.getString("nombre"),
                                        rs.getString("id Pinacoteca"));
                        }
                        rs.close();
                        return unaPinacoteca;	
	}
		/**
	     * @param pcuadroOriginal the pcuadroOriginal to set
	     * @param pcuadroActualizacion the pcuadroActualizacion to set
	     */
		public  void modificar(Cuadro pcuadroOriginal,Cuadro pcuadroActualizacion) throws java.sql.SQLException,Exception{
			String sql;
			String idCuadro = pcuadroOriginal.getIdCuadro();		
			this.eliminar(pcuadroOriginal);
			pcuadroActualizacion.setIdCuadro(idCuadro);	
			LocalDate fechaActualizada = pcuadroActualizacion.getFechaCreacion();
			String nombre= pcuadroActualizacion.getNombreCuadro();
			String dimensiones = pcuadroActualizacion.getDimensionesCuadro();
			String fechaCreacion = "#" + fechaActualizada.getMonthValue()+ "/" + fechaActualizada.getDayOfMonth()+ "/" +fechaActualizada.getYear() + "#" ; 
			String tecnica = pcuadroActualizacion.getTecnicaDePintura();
			String condicionLlegada = pcuadroActualizacion.getCondicionDeLlegada();
			String condicionActual = pcuadroActualizacion.getCondicionActual();
			double costo = pcuadroActualizacion.getCosto();
			
			sql="INSERT INTO TCuadro "+
					"(idCuadro, nombreCuadro, dimensionesCuadro, fechaCreacion, tecnicaDePinturas, condicionesDeLlegada, condicionActual, costo)"+
					"VALUES ('"+idCuadro+"','"+nombre+"',"+dimensiones+",'"+fechaCreacion+"',"+tecnica+",'"+condicionLlegada+"',"+condicionActual+",'"+costo+"');";
			
			Conector.getConector().ejecutarSQL(sql); 	
		}
		/**
	     * @param pcuadro the pcuadro to set
	     * 
	     */
		public void eliminar (Cuadro pcuadro) throws java.sql.SQLException,Exception{
			java.sql.ResultSet rs;
			String sql;
			sql= "DELETE FROM TCuadro "+
				 "WHERE idCuadro='" + pcuadro.getIdCuadro()+"'";
			Conector.getConector().ejecutarSQL(sql);
		}
		/**
	     * @param pidPinac the pidPinac to set
	     * 
	     * @return resultado
	     */
		public ArrayList<Cuadro> obtenerListaCuadrosPorPinacoteca(String pidPinac) throws Exception{
			ArrayList<Cuadro> resultado = new ArrayList<Cuadro>();
			String sql;
			sql = "Select nombreCuadro "+
					"from tcuadros "+
					"where id_Pinacoteca in (Select idpinacoteca from ti_CuadroXPinacoteca where idpinacoteca = '"+pidPinac+"')";
			ResultSet rs =Conector.getConector().ejecutarSQL(sql,true);
			while(rs.next()){
				Date unaFecha= rs.getDate("fechaCracion");
				LocalDate fechaConvertida = unaFecha.toLocalDate();
				Cuadro unCuadro= new Cuadro(rs.getString("NombreCuadro"),rs.getString("dimensionesCuadro"),fechaConvertida ,rs.getString("tecnicaPintura"),rs.getString("condicionDeLlegada"),rs.getString("condicionActual"),rs.getDouble("pCosto"),rs.getString("idCuadro")); 
			resultado.add(unCuadro);	
			}
			return resultado;
		}
		/**
	     * @param pidPintor the pidPintor to set
	     * 
	     * @return resultado
	     */
		public ArrayList<Cuadro> obtenerCuadrosPorPintor(String pidPintor) throws Exception{
			ArrayList<Cuadro> resultado = new ArrayList<Cuadro>();
			String sql;
			sql = "Select nombreCuadro "+
					"from tcuadros "+
					"where id_Pintor ='"+pidPintor+"'";
			ResultSet rs =Conector.getConector().ejecutarSQL(sql,true);
			while(rs.next()){
				Date unaFecha= rs.getDate("fechaCracion");
				LocalDate fechaConvertida = unaFecha.toLocalDate();
				Cuadro unCuadro= new Cuadro(rs.getString("NombreCuadro"),rs.getString("dimensionesCuadro"),fechaConvertida ,rs.getString("tecnicaPintura"),rs.getString("condicionDeLlegada"),rs.getString("condicionActual"),rs.getDouble("pCosto"),rs.getString("idCuadro")); 
			resultado.add(unCuadro);	
			}
			return resultado;
		}
		/**
	     * @param pidPinac the pidPinac to set
	     * 
	     * @return resultado
	     */
		public ArrayList<String> obtenerListaCuadros(String pidPinac) throws Exception{
			ArrayList<String> resultado = new ArrayList<String>();
			String sql;
			sql = "Select nombreCuadro "+
					"from tcuadros;"+
					"where id_Pinac ='" +pidPinac +"'";
			ResultSet rs =Conector.getConector().ejecutarSQL(sql,true);
			while(rs.next()){
			resultado.add(rs.getString("NombreCuadro"));	
			}
			return resultado;
		}
                
	    public ArrayList<String> obtenerListaCuadros() throws Exception{
	        ArrayList<String> resultado = new ArrayList<String>();
	        String sql;
	        sql = "Select nombreCuadro from tcuadros;";
	        ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
	        while(rs.next()){
	        	 resultado.add(rs.getString("nombreCuadro"));
	        }
	        return resultado;
	    }
            
            public ArrayList<String> obtenerListaCuadrosPorPintor(String pid) throws Exception{
	        ArrayList<String> resultado = new ArrayList<String>();
	        String sql;
	        sql = "Select nombreCuadro from tcuadros where id_pintor = '"+pid+"';";
	        ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
	        while(rs.next()){
	        	 resultado.add(rs.getString("nombreCuadro"));
	        }
	        return resultado;
	    }
}

