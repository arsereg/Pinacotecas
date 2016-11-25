package CapaLogica;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import CapaAccesoBD.Conector;
public class MultiEscuela {
	

	
		
		/**
	     * @param pnombre the pnombre to set
	     * @param pPaisSede the pPaisSede to set
	     * @param pFechaDeOrigen the pFechaDeOrigen to set
	     * @param pDescripcionEscuela the pDescripcionEscuela to set
	     * @return miEscuela
	     */
		public  Escuela crear(String pnombre,String pPaisSede,LocalDate pFechaDeOrigen, String pDescripcionEscuela) 
				throws java.sql.SQLException,Exception{
			Escuela.setCantEscuelas(contarEscuelasCreados());
			Escuela miEscuela = new Escuela (pnombre,pPaisSede,pFechaDeOrigen,pDescripcionEscuela);
			String sql;
			String fecha = "#" + pFechaDeOrigen.getMonthValue()+ "/" + pFechaDeOrigen.getDayOfMonth()+ "/" +pFechaDeOrigen.getYear() + "#" ; 
			sql="INSERT INTO TCuadro "+
			"(nombreCuadro, dimensionesCuadro, fechaDeOrigen, tecnicaDePinturas, condicionesDeLlegada, condicionActual, costo)"+
			"VALUES ('"+pnombre+"',"+pPaisSede+",'"+pFechaDeOrigen+"',"+pDescripcionEscuela+");";
			Conector.getConector().ejecutarSQL(sql);
			return miEscuela;
		}
		/**
	     *
	     * @return 
	     */
		public int contarEscuelasCreados()throws Exception{
			String sql;
			sql = "SELECT IdEscuela FROM TEscuelas;";
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
	     * @return unaEscuela
	     */
		public  Escuela buscar(String pid) throws java.sql.SQLException,Exception{
			Escuela unaEscuela = null;
			java.sql.ResultSet rs;
			String sql;
			sql = "SELECT * "+
			"FROM TEscuela "+
			"WHERE idEscuela='"+pid+"';";
			rs = Conector.getConector().ejecutarSQL(sql,true);
			if (rs.next()){
				unaEscuela = new Escuela(
					rs.getString("pnombre"),
					rs.getString("ppaisSede"),
					rs.getDate("fechaDeOrigen").toLocalDate(),
					rs.getString("descripcion"),
					rs.getString("idEscuela"));
			} else {
				throw new Exception ("El abono no está registrado en el sistema.");
			}
			rs.close();
			return unaEscuela;
		}
	    public ArrayList<String> obtenerListaEscuela() throws Exception{
	        ArrayList<String> resultado = new ArrayList<String>();
	        String sql;
	        sql = "Select Nombre from tescuela;";
	        ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
	        while(rs.next()){
	        	resultado.add(rs.getString("nombre"));
	        }
	        return resultado;
	    }
	}

