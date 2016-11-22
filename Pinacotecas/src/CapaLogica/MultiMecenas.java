/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import CapaAccesoDB.Conector;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Gino
 */
public class MultiMecenas {
    public  Mecena buscar(String pid) throws java.sql.SQLException,Exception{
		Mecena unaMecena = null;
		ResultSet rs;
		String sql;
		sql = "SELECT * "+
		"FROM TMecenas "+
		"WHERE idmecena='"+pid+"';";
		rs = Conector.getConector().ejecutarSQL(sql,true);
		if (rs.next()){
                        unaMecena = new Mecena(
				rs.getString("nombre_Mecena"),
				rs.getString("pais_Origen"),
				rs.getString("ciudad_Origen"),
				rs.getDate("fecha_Fallecimiento").toLocalDate(),                                
                                rs.getString("IDMecena"));
		} else {
			throw new Exception ("El abono no está registrado en el sistema.");
		}
		rs.close();
		return unaMecena;
	}
    
    public  ArrayList<Mecena> buscarPorPintor(String pidPintor) throws java.sql.SQLException,Exception{
		ResultSet rs;
		String sql;
		Mecena unaMecena=null;
		ArrayList<Mecena> mecenas=null;
		sql="SELECT * "+
		"FROM TMecenas "+
		"WHERE idmecena in (Select idmecena from Ti_PintorXMecena where idPintor = " + pidPintor + ");";
		rs = Conector.getConector().ejecutarSQL(sql,true);
		mecenas= new ArrayList<Mecena>();
		while (rs.next()){
			unaMecena = new Mecena(
				rs.getString("nombre_Mecena"),
				rs.getString("pais_Origen"),
				rs.getString("ciudad_Origen"),
				rs.getDate("fecha_Fallecimiento").toLocalDate(),                                
                                rs.getString("IDMecena"));
				mecenas.add(unaMecena);
		}
		rs.close();
		return mecenas;
	}
    
    public  Mecena crear(String pnombreMecena, String ppaisDeOrigen, String pciudadDeOrigen, LocalDate pfechaFallecimiento) throws java.sql.SQLException,Exception{
                Mecena.setCantMecenas(contarMecenasCreadas());
		Mecena mecena = new Mecena(pnombreMecena, ppaisDeOrigen, pciudadDeOrigen, pfechaFallecimiento);
		String sql;
                String fecha = "#" +pfechaFallecimiento.getMonthValue() + "/" + pfechaFallecimiento.getDayOfMonth() + "/" + pfechaFallecimiento.getYear() +  "#";
		sql="INSERT INTO TMecenas "+
		"(IDMecena, nombre_Mecena, pais_Origen, ciudad_Origen, fecha_Fallecimiento)"+
		"VALUES ('"+mecena.getIdMecena()+"','"+pnombreMecena+"','"+ppaisDeOrigen+"','"+pciudadDeOrigen+"',"+fecha+");";
		Conector.getConector().ejecutarSQL(sql);
		return mecena;
	}
    
    public int contarMecenasCreadas() throws Exception{
        String sql;
        sql = "SELECT IDMecena FROM TMecenas;";
        ResultSet rs = Conector.getConector().ejecutarSQL(sql,true);
        int size = 0;
        while(rs.next()){
            size++;
        }
        return size;
    }
    
    public ArrayList<String> obtenerListaMecenas() throws Exception{
        ArrayList<String> resultado = new ArrayList<String>();
        String sql;
        sql = "Select nombre_mecena from tmecenas;";
        ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
        while(rs.next()){
            resultado.add(rs.getString("nombre_mecena"));
        }
        return resultado;
    }
}
