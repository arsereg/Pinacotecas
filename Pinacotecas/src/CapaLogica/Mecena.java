/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Gino
 */
public class Mecena {

    /**
     * @return the cantMecenas
     */
    public static int getCantMecenas() {
        return cantMecenas;
    }

    /**
     * @param aCantMecenas the cantMecenas to set
     */
    public static void setCantMecenas(int aCantMecenas) {
        cantMecenas = aCantMecenas;
    }

    /**
     * @return the prefijo
     */
    public static String getPrefijo() {
        return prefijo;
    }

    /**
     * @param aPrefijo the prefijo to set
     */
    public static void setPrefijo(String aPrefijo) {
        prefijo = aPrefijo;
    }
    private String idMecena;
    private String nombreMecena;
    private String paisDeOrigen;
    private String ciudadDeOrigen;
    private LocalDate fechaDeFallecimiento;
    private static int cantMecenas = 0;
    private static String prefijo = "MEC-";
    private ArrayList<String> idPintores;
    private ArrayList<Pintor> pintores;

    public Mecena(String pnombreMecena, String ppaisDeOrigen, String pciudadDeOrigen, LocalDate pfechaFallecimiento, String pid){
        this.setNombreMecena(pnombreMecena);
        this.setPaisDeOrigen(ppaisDeOrigen);
        this.setCiudadDeOrigen(pciudadDeOrigen);
        this.setFechaDeFallecimiento(pfechaFallecimiento);
        this.setIdMecena(pid);
        //idPintores = (new MultiPintores().buscarListaPintores(pid));
        pintores = null;
    }
    
    public Mecena(String pnombreMecena, String ppaisDeOrigen, String pciudadDeOrigen, LocalDate pfechaFallecimiento){
        this(pnombreMecena, ppaisDeOrigen, pciudadDeOrigen, pfechaFallecimiento, generarId());
    }
    
    /**
     * @return the idMecena
     */
    public String getIdMecena() {
        return idMecena;
    }

    /**
     * @param idMecena the idMecena to set
     */
    private void setIdMecena(String idMecena) {
        this.idMecena = idMecena;
    }

    /**
     * @return the nombreMecena
     */
    public String getNombreMecena() {
        return nombreMecena;
    }

    /**
     * @param nombreMecena the nombreMecena to set
     */
    public void setNombreMecena(String nombreMecena) {
        this.nombreMecena = nombreMecena;
    }

    /**
     * @return the paisDeOrigen
     */
    public String getPaisDeOrigen() {
        return paisDeOrigen;
    }

    /**
     * @param paisDeOrigen the paisDeOrigen to set
     */
    public void setPaisDeOrigen(String paisDeOrigen) {
        this.paisDeOrigen = paisDeOrigen;
    }

    /**
     * @return the ciudadDeOrigen
     */
    public String getCiudadDeOrigen() {
        return ciudadDeOrigen;
    }

    /**
     * @param ciudadDeOrigen the ciudadDeOrigen to set
     */
    public void setCiudadDeOrigen(String ciudadDeOrigen) {
        this.ciudadDeOrigen = ciudadDeOrigen;
    }

    /**
     * @return the fechaDeFallecimiento
     */
    public LocalDate getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    /**
     * @param fechaDeFallecimiento the fechaDeFallecimiento to set
     */
    public void setFechaDeFallecimiento(LocalDate fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }
    
    //Aca se genera el ID
    
    private static String generarId(){
        String resul = getPrefijo() + ++cantMecenas;
        return resul;
    }

    /**
     * @return the idPintores
     */
    public ArrayList<String> getIdPintores() {
        ArrayList<String> laLista;
        laLista = (new MultiPintor()).getListaPintores(idMecena);
        return laLista;
    }

    /**
     * @param idPintores the idPintores to set
     */
    public void setIdPintores(ArrayList<String> idPintores) {
        this.idPintores = idPintores;
    }

    /**
     * @return the pintores
     */
    public ArrayList<Pintor> getPintores() {
        if(pintores == null){
            this.setPintores((new MultiPintores.buscarPorMecena(this.getIdMecena())));
        }
        return pintores;
    }

    /**
     * @param pintores the pintores to set
     */
    public void setPintores(ArrayList<Pintor> pintores) {
        this.pintores = pintores;
    }
    

    
}
