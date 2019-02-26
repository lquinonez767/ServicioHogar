package ec.appservicios.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.Part;

import ec.appservicios.modelo.NuevoProductoServicio;
import ec.appservicios.negocio.NuevoProductoServicioDAO;


@ManagedBean(name="nuevoproductoservicio_bean")
@SessionScoped
public class NuevoProductoServicioController {

	private NuevoProductoServicio newNuevoProductoServicio;
	private List<NuevoProductoServicio> listaProductos;
	
	@Inject
	private NuevoProductoServicioDAO medao;
	@Inject
	private Logger medLOG;
	
	
	private Part fotoProducto;
	private String directorioProducto = "C:\\Users\\Lenin\\eclipse-workspace\\ServiciosHogar\\src\\main\\webapp\\imagenes"; 
	private String nombreArchivoProducto;
	
	@PostConstruct
	public void init() {   
		medLOG.log(Level.INFO, "INICIANDO...");
		newNuevoProductoServicio = new NuevoProductoServicio();
		listaProductos=medao.getProductos();
		fotoProducto=null;
	}

	public NuevoProductoServicio getNewNuevoProductoServicio() {
		return newNuevoProductoServicio;
	}

	public void setNewNuevoProductoServicio(NuevoProductoServicio newNuevoProductoServicio) {
		this.newNuevoProductoServicio = newNuevoProductoServicio;
	}

	public List<NuevoProductoServicio> getlistaProductos() {
		return listaProductos;
	}

	public void setlistaProductos(List<NuevoProductoServicio> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public NuevoProductoServicioDAO getMedao() {
		return medao;
	}

	public void setMedao(NuevoProductoServicioDAO medao) {
		this.medao = medao;
	}

	public Logger getMedLOG() {
		return medLOG;
	}

	public void setMedLOG(Logger medLOG) {
		this.medLOG = medLOG;
	} 
	
	
	
	
	public Part getFotoProducto() {
		return fotoProducto;
	}

	public void setFotoProducto(Part fotoProducto) {
		this.fotoProducto = fotoProducto;
	}

	public String getDirectorioProducto() {
		return directorioProducto;
	}

	public void setDirectorioProducto(String directorioProducto) {
		this.directorioProducto = directorioProducto;
	}

	public String getNombreArchivoProducto() {
		return nombreArchivoProducto;
	}

	public void setNombreArchivoProducto(String nombreArchivoProducto) {
		this.nombreArchivoProducto = nombreArchivoProducto;
	}

	public String guardar() {
		
		if(fotoProducto!=null){
			savefotoProducto();
			newNuevoProductoServicio.setUrlFotoProducto("imagenes/"+nombreArchivoProducto);
		}else{

			newNuevoProductoServicio.setUrlFotoProducto("imagenes/producto.png");

		}
		medao.guardar(newNuevoProductoServicio);
		List<NuevoProductoServicio> aux_lista_producto = medao.getProductos();
		for (NuevoProductoServicio producto: aux_lista_producto) {
			medLOG.log(Level.INFO, producto.toString());
		}
		init();
		return null;
	}
	
	public String editar(int codigo) {
		NuevoProductoServicio producto = medao.read(codigo);
		newNuevoProductoServicio = producto;
		return "registro_producto_editar";
	}
	
	public String ver(int codigo) {
		NuevoProductoServicio producto = medao.read(codigo);
		newNuevoProductoServicio = producto;
	
		return null;
	}
	
	public void limpiarTodo() { 
		init();
	}
	 
	public String eliminar(int codigo) {
		medao.remove(codigo);
		init();
		return null;
	}

	public String savefotoProducto() {
		try (InputStream input = fotoProducto.getInputStream()) {
			
			nombreArchivoProducto = fotoProducto.getName(); 
			
			 Files.copy(input, new File(directorioProducto, nombreArchivoProducto).toPath()); System.out.println(
			 "Uploaded file successfully saved in " + fotoProducto);
			 

		} catch (IOException e) {
			// Show faces message?
		}
		return "";
	}
	
}
