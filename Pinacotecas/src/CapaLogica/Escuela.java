package CapaLogica;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Escuela {
	

	private String idEscuela;
	private String nombre;	
	private String paisSede;
	private LocalDate fechaDeOrigen;
	private String descripcionEscuela;
	private ArrayList<Pintor> pintores;
	private ArrayList<String>idPintores;
	private static int cantEscuelas = 0;
	private static String prefijo ="ESC";
	
	/**
     * Constructor con los datos de un escuela con el id enviado
     *
     * @param pnombre to set
     * @param pPaisSede to set
     * @param pFechaOrigen to set
     * @param pDescripcionEscuela to set
     * @param pidEscuela to set
     * 
     */
		
	public Escuela(String pnombre,String pPaisSede,LocalDate pFechaDeOrigen, String pDescripcionEscuela, String pIdEscuela){
		this.setNombre(pnombre);
		this.setPaisSede(pPaisSede);
		this.setFechaDeOrigen(pFechaDeOrigen);
		this.setDescripcionEscuela(pDescripcionEscuela);
		this.setIdEscuela(pIdEscuela);
		pintores = null;
	}
	/**
     * Constructor con los datos de un escuela con el id generado
     *
     * @param pnombre to set
     * @param pPaisSede to set
     * @param pFechaOrigen to set
     * @param pDescripcionEscuela to set
     * 
     */
		
	public Escuela(String pnombre,String pPaisSede,LocalDate pFechaDeOrigen, String pDescripcionEscuela){
		this(pnombre, pPaisSede, pFechaDeOrigen, pDescripcionEscuela, generarId());
	}
	/**
	 * @return Pintor
	 **/
	
	public void setPintores(ArrayList<Pintor> pintores) {
		this.pintores = pintores;
	}
	
	public ArrayList<Pintor> getPintores() throws SQLException, Exception{
		if(pintores == null){
			this.setPintores((new MultiPintor()).obtenerTotalPintores(this.getIdEscuela()));
		}
		return pintores;
	}
	/**
	 * * @return idPintor
	 **/
	public ArrayList<String> getIdPintores()throws SQLException, Exception  {
		if(idPintores==null){
			this.setIdPintores((new MultiPintor()).obtenerListaPintores());
		}
		return idPintores;
		
	}
	
	/**
	 ** @param pintor the pintor to set
	 **/
	public void setIdPintores(ArrayList<String> pidPintores) {
		this.idPintores = pidPintores;
	}
	/**
	 * @return nombre
	 **/
	public String getNombre() {
		return nombre;
	}
	/**
	 * * @param nombre the nombre to set
	     */
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		/**
	     * @return cantEscuelas
	     */
		public static int getCantEscuelas() {
			return cantEscuelas;
		}
		/**
	     * @param cantEscuelas the cantEscuelas to set
	     */
		public static void setCantEscuelas(int cantEscuelas) {
			Escuela.cantEscuelas = cantEscuelas;
		}
		/**
	     * @return prefijo
	     */
		public static String getPrefijo() {
			return prefijo;
		}
		/**
	     * @param prefijo the prefijo to set
	     */
		public static void setPrefijo(String prefijo) {
			Escuela.prefijo = prefijo;
		}
		/**
	     * @return idEscuela
	     */
		public String getIdEscuela() {
			return idEscuela;
		}
		/**
	     * @param idEscuela the idEscuela to set
	     */
		private void setIdEscuela(String idEscuela) {
			this.idEscuela = idEscuela;
		}
		/**
	     * @return paisSede
	     */
		public String getPaisSede() {
			return paisSede;
		}
		/**
	     * @param paisSede the paisSede to set
	     */
		public void setPaisSede(String paisSede) {
			this.paisSede = paisSede;
		}
		/**
	     * @return fechaDeOrigen
	     */
		public LocalDate getFechaDeOrigen() {
			return fechaDeOrigen;
		}
		/**
	     * @param fechaDeOrigen the fechaDeOrigen to set
	     */
		public void setFechaDeOrigen(LocalDate fechaDeOrigen) {
			this.fechaDeOrigen = fechaDeOrigen;
		}
		/**
	     * @return descripcionEscuela
	     */
		public String getDescripcionEscuela() {
			return descripcionEscuela;
		}
		/**
	     * @param descripcionEscuela the descipcionEscuela to set
	     */
		public void setDescripcionEscuela(String descripcionEscuela) {
			this.descripcionEscuela = descripcionEscuela;
		}

		/*Generar ID*/
	    /**
	     * @return resulT
	     */
		public static String generarId(){
			String result = getPrefijo()+ ++cantEscuelas;
			return result;
		}
}
