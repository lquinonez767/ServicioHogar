package ec.appservicios.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;



@ManagedBean(name="geoproveedor")
@ViewScoped
public class GeoProveedor {

	
	private String latitud="-1";
	private String longitud="-1"; 
	private String descripcion;
	private String elegimos;
	private String latituddes;
	private String longituddes;
	
	
	
	private List<GeoProveedor> ubica = new ArrayList<GeoProveedor>();
	private List<SelectItem> itemgeoposicion = new ArrayList<SelectItem>();
	
	public GeoProveedor(String latitud, String longitud, String descripcion) {
		this.latitud = latitud;
		this.longitud = longitud;
		this.descripcion = descripcion;
		
	}
	public GeoProveedor(){
		
		cargarDatos();
	    cargarCampos();
	}

	

	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getElegimos() {
		return elegimos;
	}
	public void setElegimos(String elegimos) {
		this.elegimos = elegimos;
	}
	public String getLatituddes() {
		return latituddes;
	}
	public void setLatituddes(String latituddes) {
		this.latituddes = latituddes;
	}
	public String getLongituddes() {
		return longituddes;
	}
	public void setLongituddes(String longituddes) {
		this.longituddes = longituddes;
	}
	public List<GeoProveedor> getUbica() {
		return ubica;
	}
	public void setUbica(List<GeoProveedor> ubica) {
		this.ubica = ubica;
	}
	public List<SelectItem> getItemgeoposicion() {
		return itemgeoposicion;
	}
	public void setItemgeoposicion(List<SelectItem> itemgeoposicion) {
		this.itemgeoposicion = itemgeoposicion;
	}
	
	private void cargarDatos(){
		
	ubica.add(new GeoProveedor("-2.8965507", "-79.0122929","VETERINARIA SOLITARIA"));
	ubica.add(new GeoProveedor("-2.9037547", "-78.9920967","VETERINARIA PATAS"));
	ubica.add(new GeoProveedor("-2.9093549", "-78.9979134","VETERINARIA MORA"));
	ubica.add(new GeoProveedor("-2.914104","-79.0248189","VETERINARIA MEDIVET"));
	ubica.add(new GeoProveedor("-2.900447","-79.0198938","VETERINARIA FAUNA"));
	
	
	}
	
	private void cargarCampos(){
		
		for(GeoProveedor geo:ubica){
			
			itemgeoposicion.add(new SelectItem(geo.descripcion, geo.descripcion));
		}
		
	}
	
	public String action(){
		
		setDescripcion("Mi ubicacion");
		return null;
	}
	
	
	public String calcular(String descripcion,String lat,String lon){

		for (GeoProveedor ubicar: ubica)
		{

			if (elegimos.equals(ubicar.descripcion))
			{
				this.latituddes=ubicar.latitud;
				this.longituddes=ubicar.longitud;
				System.out.println("calculando"+" "+ubicar.descripcion+"  latitud  "+this.latituddes+" "+"longitud  "+this.longituddes+"--->"+descripcion+"  latitud"+latitud+" "+"  longitud"+longitud );
			}
		}
		return "null";
	}

}
