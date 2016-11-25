package CapaLogica;


import java.sql.SQLException;
import java.time.LocalDate;

public class Cuadro {
	private String idCuadro;
	private String nombreCuadro;
	private String dimensionesCuadro;
	private LocalDate fechaCreacion;
	private String tecnicaDePintura;
	private String condicionDeLlegada;
	private String condicionActual;
	private double costo;
	private String idPinac;
	private String idPintor;
	private static int cantCuadros =0;
	private static String prefijo = "CUAD-";
	private Pinacoteca miPinacoteca;
	private Pintor miPintor;
	
	/**
     * Constructor con los datos de un cuadro con el id enviado
     *
     * @param pNombreCuadro to set
     * @param pDimensionesCuadro to set
     * @param pfechaCreacion to set
     * @param pTecnicaDePintura to set
     * @param pCondicionDeLlegada to set
     * @param pCondicionActual to set
     * @param pCosto to set
     * @param pidCuadro to set
     * 
     */
	public Cuadro(String pNombreCuadro,String pDimensionesCuadro ,LocalDate pfechaCreacion ,String pTecnicaDePintura, String pCondicionDeLlegada, String pCondicionActual,double pCosto,String pidCuadro){
		this.setNombreCuadro(pNombreCuadro);
		this.setDimensionesCuadro(pDimensionesCuadro);
		this.setFechaCreacion(pfechaCreacion);
		this.setTecnicaDePintura(pTecnicaDePintura);
		this.setCondicionDeLlegada(pCondicionDeLlegada);
		this.setCondicionActual(pCondicionActual);
		this.setCosto(pCosto);
		this.setIdCuadro(pidCuadro);
		miPinacoteca = null;
		miPintor = null;
		
	}
	/**
     * Constructor con los datos de un cuadro con el id generado
     *
     * @param pNombreCuadro to set
     * @param pDimensionesCuasdro to set
     * @param pfechaCreacion to set
     * @param pTecnicaPintura to set
     * @param pCondicionDeLlegada to set
     * @param pCondicionActual to set
     * @param pCosto to set
     * @param pidCuadro to set
     * @param pmiMaestro to set
     */
	public Cuadro(String pNombreCuadro,String pDimensionesCuadro ,LocalDate pfechaCreacion ,String pTecnicaDePintura, String pCondicionDeLlegada, String pCondicionActual,double pCosto){
		this(pNombreCuadro,pDimensionesCuadro,pfechaCreacion,pTecnicaDePintura,pCondicionDeLlegada,pCondicionActual,pCosto,generarId());
	}
	/**
     * @return miPintor
	 * @throws Exception 
	 * @throws SQLException 
     */
	public Pintor getMiPintor() throws SQLException, Exception {
		if(miPintor==null){
			this.setMiPintor((new MultiPintor()).buscar(this.getIdPintor()));	
		}
		return miPintor;
	}	
	/**
     * @param miPintor the miPintor to set
     */
	public void setMiPintor(Pintor miPintor) {
		this.miPintor = miPintor;
	}
	/**
     * @return idPinacoteca
     */
	public Pinacoteca getMiPinacoteca() throws SQLException, Exception  {
		if(miPinacoteca==null){
                    Pinacoteca unaPinacoteca = (new MultiPinacoteca()).buscar(this.getIdCuadro());
		}
		return miPinacoteca;
	}
	/**
     * @param miPinacoteca the miPinacoteca to set
     */
	public void setMiPinacoteca(Pinacoteca miPinacoteca) {
		this.miPinacoteca = miPinacoteca;
	}
	/**
     * @return cantCuadros
     */
	public static int getCantCuadros() {
		return cantCuadros;
	}
	/**
     * @param cantCuadros the cantCuadros to set
     */
	public static void setCantCuadros(int cantCuadros) {
		Cuadro.cantCuadros = cantCuadros;
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
		Cuadro.prefijo = prefijo;
	}
	/**
     * @return idCuadros
     */
	public String getIdCuadro() {
		return idCuadro;
	}
	/**
     * @param  idCuadro the idCuadro to set
     */
	public void setIdCuadro(String idCuadro) {
		this.idCuadro = idCuadro;
	}
	/**
     * @return nombreCuadro
     */
	public String getNombreCuadro() {
		return nombreCuadro;
	}
	/**
     * @param nombreCuadro the nombreCuadro to set
     */
	public void setNombreCuadro(String nombreCuadro) {
		this.nombreCuadro = nombreCuadro;
	}
	/**
     * @return dimensionesCuadro
     */
	public String getDimensionesCuadro() {
		return dimensionesCuadro;
	}
	/**
     * @param dimensionesCuadro the dimensionesCuadro to set
     */
	public void setDimensionesCuadro(String dimensionesCuadro) {
		this.dimensionesCuadro = dimensionesCuadro;
	}
	/**
     * @return fechaCreacion
     */
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	/**
     * @param fechaCracion the fechaDeCreacion to set
     */
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
     * @return tecnicaDePintura
     */
	public String getTecnicaDePintura() {
		return tecnicaDePintura;
	}
	/**
     * @param tecnicaDePintura the tecnicaDePintura to set
     */
	public void setTecnicaDePintura(String tecnicaDePintura) {
		this.tecnicaDePintura = tecnicaDePintura;
	}
	/**
     * @return condicionDeLlegada
     */
	public String getCondicionDeLlegada() {
		return condicionDeLlegada;
	}
	/**
     * @param condicionDeLlegada the condicionDeLlegada to set
     */
	public void setCondicionDeLlegada(String condicionDeLlegada) {
		this.condicionDeLlegada = condicionDeLlegada;
	}
	/**
     * @return condicionActual
     */
	public String getCondicionActual() {
		return condicionActual;
	}
	/**
     * @param condicionActual the condicionActual to set
     */
	public void setCondicionActual(String condicionActual) {
		this.condicionActual = condicionActual;
	}
	/**
     * @return costo
     */
	public double getCosto() {
		return costo;
	}
	/**
     * @param costo the costo to set
     */
	public void setCosto(double costo) {
		this.costo = costo;
	}
	/**
     * @return idPinac
     */
	public String getIdPinac() {
		return idPinac;
	}
	/**
     * @param idPinac the idPinac to set
     */
	public void setIdPinac(String idPinac) {
		this.idPinac = idPinac;
	}
	/**
     * @return idPintor
     */
	public String getIdPintor() {
		return idPintor;
	}
	/**
     * @param idPintor the idPintor to set
     */
	public void setIdPintor(String idPintor) {
		this.idPintor = idPintor;
	}
	/*Generara ID*/
    /**
     * @return result
     */
	public static String generarId(){
		String result = getPrefijo()+ ++cantCuadros;
		return result;
		
	}
}	
