package CapaLogica;


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
public class Pinacoteca {

    private String idPinac;
    private double metros;
    private LocalDate fechaInag;
    private String nombrePinac;
    private static int cantPinacotecas = 0;
    private static String prefijo = "PINAC- ";
    private ArrayList<String> listaIdCuadros;
    private ArrayList<Cuadro> listaDeCuadros;

    /**
     * Constructor con los datos de una pinacoteca con el id enviado
     *
     * @param pMetros to set
     * @param pFechaInag to set
     * @param pNombre to set
     * @param pid to set
     */
    public Pinacoteca(double pMetros, LocalDate pFechaInag, String pNombre, String pid) throws Exception {
        this.setNombrePinac(pNombre);
        this.setMetros(pMetros);
        this.setFechaInag(pFechaInag);
        this.setIdPinac(pid);
        listaIdCuadros = (new MultiCuadro().obtenerListaCuadros(pid));
        listaDeCuadros = null;
    }

    /**
     * Constructor con los datos de una pinacoteca con el id generado
     *
     * @param pMetros to set
     * @param pFechaInag to set
     * @param pNombre to set
     */
    public Pinacoteca(double pMetros, LocalDate pFechaInag, String pNombre) throws Exception {
        this(pMetros, pFechaInag, pNombre, generarIdPinacoteca());
    }

    /*SETS*/
    /**
     * @param pidPinac the pidPinac to set
     */
    private void setIdPinac(String pidPinac) {
        this.idPinac = pidPinac;
    }

    /**
     * @param metros the metros to set
     */
    public void setMetros(double metros) {
        this.metros = metros;
    }

    /**
     * @param fechaInag the fechaInag to set
     */
    public void setFechaInag(LocalDate fechaInag) {
        this.fechaInag = fechaInag;
    }

    /**
     * @param nombrePinac the nombrePinac to set
     */
    public void setNombrePinac(String nombrePinac) {
        this.nombrePinac = nombrePinac;
    }

    /**
     * @param listaDeCuadros the listaDeCuadros to set
     */
    public void setListaDeCuadros(ArrayList<Cuadro> listaDeCuadros) {
        this.listaDeCuadros = listaDeCuadros;
    }

    /**
     * @param idCuadros the idCuadros to set
     */
    public void setIdCuadros(ArrayList<String> listaIdCuadros) {
        this.listaIdCuadros = listaIdCuadros;
    }

    /**
     * @param cantPinacotecas the cantPinacotecas to set
     */
    public static void setCantPinacotecas(int cantPinacotecas) {
        Pinacoteca.cantPinacotecas = cantPinacotecas;
    }

    /*GETS*/
    /**
     * @return idPinac
     */
    public String getIdPinac() {
        return idPinac;
    }

    /**
     * @return metros
     */
    public double getMetros() {
        return metros;
    }

    /**
     * @return fechaInag
     */
    public LocalDate getFechaInag() {
        return fechaInag;
    }

    /**
     * @return nombrePinac
     */
    public String getNombrePinac() {
        return nombrePinac;
    }

    /**
     * @return listaDeCuadros
     */
    public ArrayList<Cuadro> getListaDeCuadros() throws Exception {

        if (listaDeCuadros == null) {
            setListaDeCuadros(new MultiCuadro().obtenerListaCuadrosPorPinacoteca(this.getIdPinac()));
        }
        return listaDeCuadros;
    }

    /**
     * @return idCuadros
     */
    public ArrayList<String> getIdCuadros() throws Exception {

        if (listaIdCuadros == null) {
            setListaDeCuadros(new MultiCuadro().obtenerListaCuadrosPorPinacoteca(idPinac));
        }
        return listaIdCuadros;
    }

    /**
     * @return cantPinacotecas
     */
    public static int getCantPinacotecas() {
        return cantPinacotecas;
    }

    /*Generación de ID*/
    /**
     * @return resul
     */
    private static String generarIdPinacoteca() {

        String resul = prefijo + ++cantPinacotecas;

        return resul;
    }

}
