package CapaLogica;


import CapaLogica.Cuadro;
import CapaLogica.Escuela;
import CapaLogica.Mecena;
import CapaLogica.MultiCuadro;
import CapaLogica.MultiEscuela;
import CapaLogica.MultiMecena;
import java.time.*;
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
public class Pintor {

    private String idPintor;
    private String nombrePintor;
    private String alias;
    private String nacionalidad;
    private String paisDeNacimiento;
    private String ciudadDeNacimiento;
    private LocalDate fechaDeNacimiento;
    private LocalDate fechaDeFallecimiento;
    private String miMaestro;
    private String idEscuela;
    private static int cantPintores = 0;
    private Escuela escuela;
    private static String prefijo = "PINT- ";
    private ArrayList<String> listaCuadros;
    private ArrayList<Cuadro> misCuadros;
    private ArrayList<String> listaIdMecenasPorPintor;
    private ArrayList<Mecena> listaDeMecenasPorPintor;

    /**
     * Constructor con los datos de un pintor con el id enviado
     *
     * @param pNombreDelPintor to set
     * @param pAlias to set
     * @param pNacionalidad to set
     * @param pPaisDeNacimiento to set
     * @param pCiudadDeNacimiento to set
     * @param pFechaDeNacimiento to set
     * @param pFechaDeFallecimiento to set
     * @param pid to set
     * @param pmiMaestro to set
     */
    public Pintor(String pNombreDelPintor, String pAlias, String pNacionalidad, String pPaisDeNacimiento, String pCiudadDeNacimiento, LocalDate pFechaDeNacimiento, LocalDate pFechaDeFallecimiento, String pmiMaestro, String pidEscuela,String pid) throws Exception {
        this.setNombrePintor(pNombreDelPintor);
        this.setAlias(pAlias);
        this.setNacionalidad(pNacionalidad);
        this.setPaisDeNacimiento(pPaisDeNacimiento);
        this.setCiudadDeNacimiento(pCiudadDeNacimiento);
        this.setFechaDeFallecimiento(pFechaDeNacimiento);
        this.setFechaDeFallecimiento(pFechaDeFallecimiento);
        this.setIdPintor(pid);
        this.setMiMaestro(pmiMaestro);
        this.idEscuela = pidEscuela;
        listaCuadros = (new MultiCuadro().obtenerListaCuadros(pid));
        misCuadros = null;
        listaIdMecenasPorPintor = (new MultiMecena().obtenerListaMecenasPorPintor(pid));
        listaDeMecenasPorPintor = null;

    }

    /**
     * Constructor con los datos de un pintor con el id generado
     *
     * @param pNombreDelPintor to set
     * @param pAlias to set
     * @param pNacionalidad to set
     * @param pPaisDeNacimiento to set
     * @param pCiudadDeNacimiento to set
     * @param pFechaDeNacimiento to set
     * @param pFechaDeFallecimiento to set
     * @param pmiMaestro to set
     */
    public Pintor(String pNombreDelPintor, String pAlias, String pNacionalidad, String pPaisDeNacimiento, String pCiudadDeNacimiento, LocalDate pFechaDeNacimiento, LocalDate pFechaDeFallecimiento, String pmiMaestro, String pidEscuela) throws Exception {

        this(pNombreDelPintor, pAlias, pNacionalidad, pPaisDeNacimiento, pCiudadDeNacimiento, pFechaDeNacimiento, pFechaDeFallecimiento, pmiMaestro, pidEscuela,generarIdPintor());
    }

    /*SETS*/
    /**
     * @param pid the pid to set
     */
    private void setIdPintor(String pid) {
        this.idPintor = pid;
    }

    /**
     * @param nombrePintor the nombrePintor to set
     */
    public void setNombrePintor(String nombrePintor) {
        this.nombrePintor = nombrePintor;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * @param nacionalidad the nacionalidad to set
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * @param paisDeNacimiento the paisDeNacimiento to set
     */
    public void setPaisDeNacimiento(String paisDeNacimiento) {
        this.paisDeNacimiento = paisDeNacimiento;
    }

    /**
     * @param ciudadDeNacimiento the ciudadDeNacimiento to set
     */
    public void setCiudadDeNacimiento(String ciudadDeNacimiento) {
        this.ciudadDeNacimiento = ciudadDeNacimiento;
    }

    /**
     * @param fechaDeNacimiento the fechaDeNacimiento to set
     */
    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    /**
     * @param fechaDeFallecimiento the fechaDeFallecimiento to set
     */
    public void setFechaDeFallecimiento(LocalDate fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    /**
     * @param miMaestro the miMaestro to set
     */
    public void setMiMaestro(String miMaestro) {
        this.miMaestro = miMaestro;
    }

    /**
     * @param pescuela the pIdEscuela to set
     */
    public void setEscuela(Escuela pescuela) {
        this.escuela = pescuela;
    }

    /**
     * @param listaDeCuadrosPorPintor the listaDeCuadrosPorPintor to set
     */
    public void setListaDeCuadrosPorPintor(ArrayList<String> listaDeCuadrosPorPintor) {
        this.listaCuadros = listaDeCuadrosPorPintor;
    }

    /**
     * @param listaIdCuadrosPorPintor the idCuadrosPorPintor to set
     */
    public void setIdCuadrosPorPintor(ArrayList<String> listaIdCuadrosPorPintor) {
        this.listaCuadros = listaIdCuadrosPorPintor;
    }

    /**
     * @param listaDeMecenasPorPintor the listaDeMecenasPorPintor to set
     */
    public void setListaDeMecenasPorPintor(ArrayList<Mecena> listaDeMecenasPorPintor) {
        this.listaDeMecenasPorPintor = listaDeMecenasPorPintor;
    }

    /**
     * @param listaIdMecenasPorPintor the idMecenasPorPintor to set
     */
    public void setIdMecenasPorPintor(ArrayList<String> listaIdMecenasPorPintor) {
        this.listaIdMecenasPorPintor= listaIdMecenasPorPintor;
    }

    /**
     * @param cantPintores the cantPintores to set
     */
    public static void setCantPintores(int cantPintores) {
        Pintor.cantPintores = cantPintores;
    }

    /**
     * @param idEscuela the idEscuela to set
     */
    public void setIdEscuela(String idEscuela) {
        this.idEscuela = idEscuela;
    }

    /*GETS*/
    /**
     * @return idPintor
     */
    public String getIdPintor() {
        return idPintor = prefijo + ++cantPintores;
    }

    /**
     * @return nombrePintor
     */
    public String getNombrePintor() {
        return nombrePintor;
    }

    /**
     * @return alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @return nacionalidad
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * @return paisDeNacimiento
     */
    public String getPaisDeNacimiento() {
        return paisDeNacimiento;
    }

    /**
     * @return ciudadDeNacimiento
     */
    public String getCiudadDeNacimiento() {
        return ciudadDeNacimiento;
    }

    /**
     * @return fechaDeNacimiento
     */
    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    /**
     * @return fechaDeFallecimiento
     */
    public LocalDate getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    /**
     * @return miMaestro
     */
    public String getMiMaestro() {
        return miMaestro;
    }

    /**
     * @return escuela
     */
    public Escuela getEscuela() throws Exception {
        if (escuela == null) {
            setEscuela((new MultiEscuela()).buscar(getIdEscuela()));
        }
        return escuela;
    }

    /**
     * @return listaDeCuadrosPorPintor
     */
    public ArrayList<String> getIdCuadros() throws Exception {
        if (listaCuadros == null) {
            setListaDeCuadrosPorPintor(new MultiCuadro().obtenerListaCuadrosPorPintor(this.getIdPintor()));
        }
        return listaCuadros;
    }

    /**
     * @return listaDeMecenasPorPintor
     */
    public ArrayList<Mecena> getListaDeMecenasPorPintor() throws Exception {

        if (listaDeMecenasPorPintor == null) {
            setListaDeMecenasPorPintor(new MultiMecena().buscarPorPintor(this.getIdPintor()));
        }
        return listaDeMecenasPorPintor;
    }

    /**
     * @return listaIdMecenasPorPintor
     */
    public ArrayList<String> getIdMecenasPorPintor() throws Exception {

        if (listaIdMecenasPorPintor== null) {
            setIdCuadrosPorPintor(new MultiMecena().obtenerListaMecenasPorPintor(idPintor));
        }
        return listaIdMecenasPorPintor;
    }

    /**
     * @return cantPintores
     */
    public static int getCantPintores() {
        return cantPintores;
    }

    /**
     * @return idEscuela
     */
    public String getIdEscuela() {
        return idEscuela;
    }

    /*Generación de ID*/
    /**
     * @return resul
     */
    private static String generarIdPintor() {

        String resul = prefijo + ++cantPintores;

        return resul;
    }

}
