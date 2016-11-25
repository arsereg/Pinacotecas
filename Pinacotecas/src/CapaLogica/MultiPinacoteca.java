package CapaLogica;


import CapaAccesoBD.Conector;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Adriana Bartels
 */
public class MultiPinacoteca {
    
    /**
     * @param pMetros the pMetros to set
     * @param pFechaInag the pFechaInag to set
     * @param pNombre the pNombre to set
     * @return pinacoteca
     */

    public Pinacoteca crear(double pMetros, LocalDate pFechaInag, String pNombre) throws java.sql.SQLException, Exception {
        Pinacoteca.setCantPinacotecas(contarCantPinacotecas());
        Pinacoteca pinacoteca = new Pinacoteca(pMetros, pFechaInag, pNombre);
        String sql;
        String fechaN = "#" + pFechaInag.getMonthValue() + "/" + pFechaInag.getDayOfMonth() + "/" + pFechaInag.getYear() + "#";
        sql = "INSERT INTO TPinacoteca "
                + "(IdPinacoteca, metros, fecha_Inauguracion, nombrePinacoteca)"
                + "VALUES ('" + pinacoteca.getIdPinac() + "','" + pMetros + "','" + fechaN + "','" + pNombre + "');";

        Conector.getConector().ejecutarSQL(sql);

        return pinacoteca;
    }
    
      
     /**
     * @param pIdCuadro the pIdCuadro to set
     * 
     * @return cuadros
     */

    public ArrayList<Cuadro> buscarPorCuadros(String pIdCuadro) throws java.sql.SQLException,Exception {
        java.sql.ResultSet rs;

        String sql;

        Cuadro unCuadro = null;
        ArrayList cuadros = null;
        sql = "SELECT * "
                + "FROM TCuadros "
                + "WHERE IdCuadro in (SELECT IdCuadro FROM Ti_CuadroxPinacoteca WHERE IdCuadro = " + pIdCuadro + ");";
        rs = Conector.getConector().ejecutarSQL(sql, true);
        cuadros = new ArrayList<>();
        while (rs.next()) {
            unCuadro = new Cuadro(
                    rs.getString("nombreCuadro"),
                    rs.getString("dimensionesCuadro"),
                    rs.getDate("fechaCreacion").toLocalDate(),
                    rs.getString("tecnica_Pintura"),
                    rs.getString("condicion_Llegada"),
                    rs.getString("condicion_Actual"),
                    rs.getDouble("costo"));
            cuadros.add(unCuadro);
        }
        rs.close();
        return cuadros;

    }
    
    /**
     * 
     * @return size
     */

    public int contarCantPinacotecas() throws Exception {
        String sql;
        sql = "SELECT IdPinacoteca FROM TPinacoteca ";
        java.sql.ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
        int size = 0;
        while (rs.next()) {
            size++;
        }
        return size;
    }
    
    /**
     * @return resultado
     */
    
     public ArrayList<String> obtenerListaPinacotecas() throws Exception{
        ArrayList<String> resultado = new ArrayList<String>();
        String sql;
        sql = "SELECT nombre_Pinacoteca from tPinacoteca;";
        ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
        while(rs.next()){
            resultado.add(rs.getString("nombre_Pinacoteca"));
        }
        return resultado;
    }
     
     
     public Pinacoteca buscar(String pid) throws Exception{
        Pinacoteca pinacoteca = null;
        java.sql.ResultSet rs;
        String sql;
        sql = "SELECT metros,fecha_Inauguracion,nombre_Pinacoteca"
                + "FROM TPinacoteca"
                + "WHERE IdPinacoteca ='" + pid + "';";
        rs = Conector.getConector().ejecutarSQL(sql, true);
        if (rs.next()) {

            pinacoteca = new Pinacoteca(
                    rs.getDouble("metros"),
                    rs.getDate("fecha_Inauguracion").toLocalDate(),
                    rs.getString("nombre_Pinacoteca"));
        } else {
            throw new Exception("La pinacoteca no está registrada.");
        }
        rs.close();
        return pinacoteca;
    }
     
}
