package CapaLogica;

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Adriana_2
 * Created: Nov 24, 2016
 */

import CapaLogica.Cuadro;
import CapaLogica.Escuela;
import CapaLogica.Mecena;
import CapaLogica.MultiCuadro;
import CapaLogica.MultiEscuela;
import CapaLogica.MultiMecena;
import CapaLogica.MultiPinacoteca;
import CapaLogica.MultiPintor;
import CapaLogica.Pinacoteca;
import CapaLogica.Pintor;
import java.time.LocalDate;
import java.util.ArrayList;

public class Gestor {

    public void pinacotecaAgregar(double pmetros, LocalDate pfechaInauguracion, String pnombre, String pid) throws Exception {
        new MultiPinacoteca().crear(pmetros, pfechaInauguracion, pnombre);
    }

    public void escuelaAgregar(String pnombre, String pPaisSede, LocalDate pFechaDeOrigen, String pDescripcionEscuela, String pid) throws Exception {
        new MultiEscuela().crear(pnombre, pPaisSede, pFechaDeOrigen, pDescripcionEscuela);
    }

    public void pintorAgregar(String pNombreDelPintor, String pAlias, String pNacionalidad, String pPaisDeNacimiento, String pCiudadDeNacimiento, LocalDate pFechaDeNacimiento, LocalDate pFechaDeFallecimiento, String pid, String pmiMaestro, String pIdEscuela) throws Exception {
        new MultiPintor().crear(pNombreDelPintor, pAlias, pNacionalidad, pPaisDeNacimiento, pCiudadDeNacimiento, pFechaDeNacimiento, pFechaDeFallecimiento, pmiMaestro, pIdEscuela);
    }

    public void cuadroAgregar(String pNombreCuadro, String pDimensionesCuadro, LocalDate pfechaCreacion, String pTecnicaDePintura, String pCondicionDeLlegada, String pCondicionActual, double pCosto, String pid) throws Exception {
        new MultiCuadro().crear(pNombreCuadro, pDimensionesCuadro, pfechaCreacion, pTecnicaDePintura, pCondicionDeLlegada, pCondicionActual, pCosto);
    }

    public void mecenaAgregar(String pnombreMecena, String ppaisDeOrigen, String pciudadDeOrigen, LocalDate pfechaFallecimiento, String pid) throws Exception {
        new MultiMecena().crear(pnombreMecena, ppaisDeOrigen, pciudadDeOrigen, pfechaFallecimiento);
    }

    public Pintor buscarPintor(String pIdPintor) throws Exception {

        Pintor unPintor = null;
        unPintor = (new MultiPintor()).buscar(pIdPintor);

        return unPintor;
    }

    public Pinacoteca buscarPinacoteca(String pIdPinacoteca) throws Exception {

        Pinacoteca unaPinac = null;

        unaPinac = (new MultiPinacoteca()).buscar(pIdPinacoteca);

        return unaPinac;
    }

    public Escuela buscarEscuela(String pidEscuela) throws Exception {

        Escuela miEscuela = null;

        miEscuela = (new MultiEscuela()).buscar(pidEscuela);

        return miEscuela;

    }

    public Cuadro buscarCuadro(String pidCuadro) throws Exception {

        Cuadro miCuadro = null;

        miCuadro = (new MultiCuadro()).buscar(pidCuadro);

        return miCuadro;

    }
    
    public Mecena  buscarMecena(String pidMecena) throws Exception {

        Mecena unMecena = null;

        unMecena = (new MultiMecena()).buscar(pidMecena);

        return unMecena;

    }

    public ArrayList<Cuadro> pinacotecaBuscarCuadros(String pidCuadro) throws Exception {
        ArrayList<Cuadro> losCuadros = null;
        losCuadros = (new MultiPinacoteca()).buscarPorCuadros(pidCuadro);
        return losCuadros;
    }

    public ArrayList<String> obtenerListaCuadrosPorPinac(String pidPinac) throws Exception {

        ArrayList<String> listaNombreCuadros = new ArrayList<>();

        listaNombreCuadros = (new MultiCuadro()).obtenerListaCuadros(pidPinac);

        return listaNombreCuadros;
    }

    public ArrayList<Cuadro> obtenerCuadrosPorPinac(String pidPinac) throws Exception {

        ArrayList<Cuadro> listaCuadros = new ArrayList<>();

        listaCuadros = (new MultiCuadro()).obtenerListaCuadrosPorPinacoteca(pidPinac);
        return listaCuadros;
    }

    public void cuadroEliminar(String pidCuadro) throws Exception {
        Cuadro unCuadro;
        unCuadro = (new MultiCuadro()).buscar(pidCuadro);
        (new MultiCuadro()).eliminar(unCuadro);
    }

    public void cuadroModificar(Cuadro punCuadro) throws Exception {
        String idDeUnCuadro = punCuadro.getIdCuadro();
        this.cuadroEliminar(idDeUnCuadro);
        this.cuadroAgregar(punCuadro.getNombreCuadro(), punCuadro.getDimensionesCuadro(), punCuadro.getFechaCreacion(), punCuadro.getTecnicaDePintura(), punCuadro.getCondicionDeLlegada(), punCuadro.getCondicionActual(), punCuadro.getCosto(), punCuadro.getIdCuadro());

    }

    /*Siempre va el pintor de primero*/
    public ArrayList pintorYpinacotecaPorCuadro(String pidCuadro) throws Exception {
        ArrayList resultado = new ArrayList();
        Pintor cuadroDePintor = (new MultiCuadro()).buscarPintorPorCuadro(pidCuadro);
        Pinacoteca cuadroDeUnaPinacoteca = (new MultiCuadro()).buscarPinacotecaPorCuadro(pidCuadro);
        resultado.add(cuadroDePintor);
        resultado.add(cuadroDeUnaPinacoteca);

        return resultado;
    }

    /*Se devuelve: los cuadros, la escuela, los maestros y los mecenas*/
    public ArrayList<ArrayList> informacionDeUnPintor(String pidPintor) throws Exception {
        Pintor unPintor = (new MultiPintor()).buscar(pidPintor);
        ArrayList<ArrayList> informacion = new ArrayList<ArrayList>();
        ArrayList<Cuadro> listaDeCuadros = (new MultiCuadro()).obtenerCuadrosPorPintor(pidPintor);
        ArrayList<Escuela> escuela = new ArrayList<Escuela>();
        escuela.add((new MultiEscuela()).buscar(unPintor.getIdEscuela()));
        ArrayList<Pintor> miMaestro = new ArrayList<Pintor>();
        miMaestro.add((new MultiPintor()).buscar(unPintor.getMiMaestro()));
        ArrayList <Mecena> misMecenas = (new MultiMecena()).buscarPorPintor(pidPintor);
        informacion.add(listaDeCuadros);
        informacion.add(escuela);
        informacion.add(miMaestro);
        informacion.add(misMecenas);
        
        return informacion;
        
    }
    
      public ArrayList<String> obtenerListaDeMecena() throws Exception {

        ArrayList<String> listaMecenas = new ArrayList<String>();

        listaMecenas = (new MultiMecena()).obtenerListaMecenas();

        return listaMecenas;
    }
      
     public ArrayList<String> obtenerListaDePintor() throws Exception {

        ArrayList<String> listaPintores = new ArrayList<String>();

        listaPintores = (new MultiPintor()).obtenerListaPintores();

        return listaPintores;
    }
     
     public ArrayList<String> obtenerListaDePinacotecas() throws Exception {

        ArrayList<String> listaPinacotecas = new ArrayList<String>();

        listaPinacotecas = (new MultiPinacoteca()).obtenerListaPinacotecas();

        return listaPinacotecas;
    }
     
     public ArrayList<String> obtenerListaDeEscuelas() throws Exception {

        ArrayList<String> listaEscuelas = new ArrayList<String>();

        listaEscuelas = (new MultiEscuela()).obtenerListaEscuela();

        return listaEscuelas;
    }
     
     public ArrayList<String> obtenerListaDeCuadros() throws Exception {

        ArrayList<String> listaCuadros = new ArrayList<String>();

        listaCuadros = (new MultiCuadro()).obtenerListaCuadros();

        return listaCuadros;
    }
    
    

}
