package CapaLogica;


import CapaAccesoBD.Conector;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class MultiPintor {

    /**
     * @param pNombreDelPintor the pNombreDelPintor to set
     * @param pAlias the pAlias to set
     * @param pNacionalidad the pNacionalidad to set
     * @param pPaisDeNacimiento the pPaisDeNacimiento to set
     * @param pCiudadDeNacimiento the pCiudadDeNacimiento to set
     * @param pFechaDeNacimiento the pFechaDeNacimiento to set
     * @param pFechaDeFallecimiento the pFechaDeFallecimiento to set
     * @param pmiMaestro the pmiMaestro to set
     * @param pIdEscuela the pIdEscuela to set
     * @return pintor
     */
    public Pintor crear(String pNombreDelPintor, String pAlias, String pNacionalidad, String pPaisDeNacimiento, String pCiudadDeNacimiento, LocalDate pFechaDeNacimiento, LocalDate pFechaDeFallecimiento, String pmiMaestro, String pIdEscuela) throws java.sql.SQLException, Exception {
        Pintor.setCantPintores(contarCantPintores());
        Pintor pintor = new Pintor(pNombreDelPintor, pAlias, pNacionalidad, pPaisDeNacimiento, pCiudadDeNacimiento, pFechaDeNacimiento, pFechaDeFallecimiento, pmiMaestro, pIdEscuela);

        String sql;
        String fechaN = "#" + pFechaDeNacimiento.getMonthValue() + "/" + pFechaDeNacimiento.getDayOfMonth() + "/" + pFechaDeNacimiento.getYear() + "#";
        String fechaF = "#" + pFechaDeFallecimiento.getMonthValue() + "/" + pFechaDeFallecimiento.getDayOfMonth() + "/" + pFechaDeFallecimiento.getYear() + "#";
        sql = "INSERT INTO TPintor "
                + "(IdPintor, nombrePintor, alias, nacionalidad, paisNacionalidad, ciudad_Nacimiento, fecha_Nacimiento, fechas_Fallecimiento, miMaestro)"
                + "VALUES ('" + pintor.getIdPintor() + "','" + pNombreDelPintor + "','" + pAlias + "','" + pNacionalidad + "','" + pPaisDeNacimiento + "','" + pCiudadDeNacimiento + "','" + fechaN + "','" + fechaF + "','" + pmiMaestro + "','" + pIdEscuela + "');";
        Conector.getConector().ejecutarSQL(sql);
        return pintor;
    }

    /**
     * @param pid the pid to set
     *
     * @return pintor
     */
    public Pintor buscar(String pid) throws
            java.sql.SQLException, Exception {
        Pintor pintor = null;
        java.sql.ResultSet rs;
        String sql;
        sql = "SELECT  IdPintor, nombrePintor,alias,nacionalidad,paisNacionalidad,ciudad_Nacimiento,fecha_Nacimiento, fechas_Fallecimiento, miMaestro, idEscuela"
                + "FROM TPintor"
                + "WHERE idPintor='" + pid + "';";
        rs = Conector.getConector().ejecutarSQL(sql, true);
        if (rs.next()) {

            pintor = new Pintor(
                    rs.getString("nombrePintor"),
                    rs.getString("alias"),
                    rs.getString("nacionalidad"),
                    rs.getString("paisNacimiento"),
                    rs.getString("ciudad_Nacimiento"),
                    rs.getDate("fecha_Nacimiento").toLocalDate(),
                    rs.getDate("fechas_Fallecimiento").toLocalDate(),
                    rs.getString("miMaestro"),
                    rs.getString("idEscuela"),
                    rs.getString("idpintor"));
                    
        } else {
            throw new Exception("El pintor no está registrado.");
        }
        rs.close();
        return pintor;
    }

    /**
     * @param pIdMecena the pIdMecena to set
     *
     * @return pintores
     */
    public ArrayList<Pintor> buscarPorMecena(String pIdMecena) throws java.sql.SQLException, Exception {
        java.sql.ResultSet rs;

        String sql;

        Pintor unPintor = null;
        ArrayList pintores = null;
        sql = "SELECT * "
                + "FROM TPintor "
                + "WHERE idMecena in (SELECT idMecena FROM Ti_PintorxMecena WHERE idMecena = " + pIdMecena + ");";
        rs = Conector.getConector().ejecutarSQL(sql, true);
        pintores = new ArrayList<>();
        while (rs.next()) {
            unPintor = new Pintor(
                    rs.getString("nombrePintor"),
                    rs.getString("alias"),
                    rs.getString("nacionalidad"),
                    rs.getString("paisNacionalidad"),
                    rs.getString("ciudad_Nacimiento"),
                    rs.getDate("fecha_Nacimiento").toLocalDate(),
                    rs.getDate("fechas_Fallecimiento").toLocalDate(),
                    rs.getString("IdPintor"),
                    rs.getString("miMaestro"));
            pintores.add(unPintor);
        }
        rs.close();
        return pintores;

    }

    /**
     *
     * @return size
     */
    public int contarCantPintores() throws Exception {
        String sql;
        sql = "SELECT IdPintor FROM TPintor ";
        java.sql.ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
        int size = 0;
        while (rs.next()) {
            size++;
        }
        return size;
    }

    /**
     * @param pIdPintor the pIdPintor to set
     * @return resul
     */
    public ArrayList[] obtenerInformacion(String pIdPintor) throws java.sql.SQLException, Exception {

        MultiPintor unMultiPintor = new MultiPintor();
        Pintor unPintor = unMultiPintor.buscar(pIdPintor);
        String idMiMaestro = unPintor.getMiMaestro();
        Pintor miMaestro = unMultiPintor.buscar(idMiMaestro);
        ArrayList<Pintor> unMaestro = new ArrayList();
        unMaestro.add(miMaestro);
        ArrayList<Mecena> listaDeMecenas = (new MultiMecena()).buscarPorPintor(pIdPintor);
        String idEscuela = unPintor.getIdEscuela();
        ArrayList<Escuela> Escuela = new ArrayList();
        Escuela.add((new MultiEscuela()).buscar(idEscuela));
        ArrayList<Cuadro> listaDeCuadros = (new MultiCuadro()).obtenerCuadrosPorPintor(pIdPintor);
        ArrayList[] resul = new ArrayList[4];
        resul[1] = listaDeCuadros;
        resul[2] = Escuela;
        resul[3] = unMaestro;
        resul[4] = listaDeMecenas;

        return resul;
    }

    /**
     * @param pIdMecena the pIdMecena to set
     * @return resultado
     */
    public ArrayList<String> obtenerListaPintoresPorMecena(String pIdMecena) throws Exception {
        ArrayList<String> resultado = new ArrayList<String>();
        String sql;
        sql = sql = "SELECT IdPintor "
                + "FROM TPintor "
                + "WHERE idMecena in (SELECT idMecena FROM Ti_PintorxMecena WHERE idMecena = " + pIdMecena + ");";
        ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
        while (rs.next()) {
            resultado.add(rs.getString("IdPintor"));
        }
        return resultado;
    }

    /**
     *
     * @return resultado
     */
    public ArrayList<String> obtenerListaPintores() throws Exception {
        ArrayList<String> resultado = new ArrayList<String>();
        String sql;
        sql = "SELECT nombrePintor from tPintor;";
        ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
        while (rs.next()) {
            resultado.add(rs.getString("nombrePintor"));
        }
        return resultado;
    }
    
    /**
     *@param pidEscuela
     * @return resultado
     */

    public ArrayList<Pintor> obtenerTotalPintores(String pidEscuela) throws Exception {
        ArrayList<Pintor> resultado = new ArrayList<Pintor>();
        String sql;
        sql = "SELECT * from tPintor WHERE idEscuela = " + pidEscuela + ");";
        ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
        while (rs.next()) {
            Pintor unPintor = new Pintor(
                    rs.getString("nombrePintor"),
                    rs.getString("alias"),
                    rs.getString("nacionalidad"),
                    rs.getString("paisNacionalidad"),
                    rs.getString("ciudad_Nacimiento"),
                    rs.getDate("fecha_Nacimiento").toLocalDate(),
                    rs.getDate("fechas_Fallecimiento").toLocalDate(),
                    rs.getString("IdPintor"),
                    rs.getString("miMaestro"));
            resultado.add(unPintor);
        }
        return resultado;
    }
    


}
