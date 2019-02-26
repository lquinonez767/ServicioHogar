package ec.appservicios.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.TransactionManagement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.Converter;
import javax.servlet.http.Part;


import org.hibernate.validator.HibernateValidatorConfiguration;

import ec.appservicios.converter.UsuarioConveter;

import ec.appservicios.negocio.*;
import ec.appservicios.modelo.*;


@ManagedBean(name="proveedor")
@SessionScoped
public class ProveedorController{ 
	
	private Proveedor newproveedor;
	private Proveedor auxProveedor;
	private Comentario newComentario;
	
	private List<Proveedor> listaProveedor;
	private List<Telefono> listaTelefonos;
	private List<Proveedor> lisProvActivos;
	private List<Comentario> listaComentarios;
	private List<Valoracion> listaValoraciones;
	private List<Usuario> listaUsuarios;
	private List<ProveedorCliente> listaClientesFiltrados;
		
	@Inject
	private ProveedorDAO provdao;	
	@Inject
	private UsuarioDAO userdao;
	@Inject
	private Logger provLog;

	
	private Date fecha=new Date();
	private String fechacomentario;
	
	private Part fotoPerfil;
	private String directorioPerfil = "C:\\Users\\Lenin\\eclipse-workspace\\ServiciosHogar\\src\\main\\webapp\\imagenes\\"; 
	private String nombreArchivoPerfil;
	
	private Part fotoPortada;
	private String directorioPortada = "C:\\Users\\Lenin\\eclipse-workspace\\ServiciosHogar\\src\\main\\webapp\\imagenes\\"; 
	private String nombreArchivoPortada;
	
	
	
	/* Datos para la ubicacion
	 */
	private String latitud="-1";
	private String longitud="-1"; 
	private String descripcion;
	private String elegimos;
	private String latituddes;
	private String longituddes;

	
	@PostConstruct
	public void init(){
		provLog.log(Level.INFO, "INICIANDO...");
		newproveedor=new Proveedor();
		newComentario=new Comentario();
		fotoPerfil=null;
		fotoPortada=null;
		fechacomentario=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(fecha);
		newproveedor.setEstado("ACTIVO");
		listaProveedor=provdao.getProveedores();
		lisProvActivos=provdao.getProveedoresActivos();
		listaComentarios=provdao.getComentarios();
		listaValoraciones=provdao.getValoraciones();
		listaUsuarios=provdao.getUsuariosActivos();		
	}
	
	
	public Proveedor getNewproveedor() {
		return newproveedor;
	}
	public void setNewproveedor(Proveedor newproveedor) {
		this.newproveedor = newproveedor;
	}
	public ProveedorDAO getProvdao() {
		return provdao;
	}

	public void setProvdao(ProveedorDAO vetdao) {
		this.provdao = vetdao;
	}

		
	public Logger getProvLog() {
		return provLog;
	}


	public void setProvLog(Logger vetLog) {
		this.provLog = vetLog;
	}
	

	public UsuarioDAO getUserdao() {
		return userdao;
	}

	public void setUserdao(UsuarioDAO userdao) {
		this.userdao = userdao;
	}

	public Part getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(Part fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public String getDirectorioPerfil() {
		return directorioPerfil;
	}

	public void setDirectorioPerfil(String directorioPerfil) {
		this.directorioPerfil = directorioPerfil;
	}

	public String getNombreArchivoPerfil() {
		return nombreArchivoPerfil;
	}

	public void setNombreArchivoPerfil(String nombreArchivoPerfil) {
		this.nombreArchivoPerfil = nombreArchivoPerfil;
	}

	public Part getFotoPortada() {
		return fotoPortada;
	}

	public void setFotoPortada(Part fotoPortada) {
		this.fotoPortada = fotoPortada;
	}

	public String getDirectorioPortada() {
		return directorioPortada;
	}

	public void setDirectorioPortada(String directorioPortada) {
		this.directorioPortada = directorioPortada;
	}

	public String getNombreArchivoPortada() {
		return nombreArchivoPortada;
	}

	public void setNombreArchivoPortada(String nombreArchivoPortada) {
		this.nombreArchivoPortada = nombreArchivoPortada;
	}

	public List<Proveedor> getListaProveedor() {
		return listaProveedor;
	}

	public void setListaVeterinaria(List<Proveedor> listaProveedor) {
		this.listaProveedor = listaProveedor;
	}

	public List<Telefono> getListaTelefonos() {
		return listaTelefonos;
	}

	public void setListaTelefonos(List<Telefono> listaTelefonos) {
		this.listaTelefonos = listaTelefonos;
	}

	public List<Proveedor> getLisProvActivos() {
		return lisProvActivos;
	}

	public void setLisProvActivos(List<Proveedor> lisProvActivos) {
		this.lisProvActivos = lisProvActivos;
	}
	
	public List<Comentario> getListaComentarios() {
		return listaComentarios;
	}

	public void setListaComentarios(List<Comentario> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}
	public List<Valoracion> getListaValoraciones() {
		return listaValoraciones;
	}

	public void setListaValoraciones(List<Valoracion> listaValoraciones) {
		this.listaValoraciones = listaValoraciones;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getFechacomentario() {
		return fechacomentario;
	}

	public void setFechacomentario(String fechacomentario) {
		this.fechacomentario = fechacomentario;
	}
	
	public Comentario getNewComentario() {
		return newComentario;
	}


	public void setNewComentario(Comentario newComentario) {
		this.newComentario = newComentario;
	}


	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}


	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
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
	
	public Proveedor getAuxProveedor() {
		return auxProveedor;
	}


	public void setAuxProveedor(Proveedor auxProveedor) {
		this.auxProveedor = auxProveedor;
	}

	
	public String MD5(String md5) {
		   try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
		}
	

	public String guardar(){
		
		if(fotoPerfil!=null){
			savefotoperfil();
			newproveedor.setUrlFotoPerfil("imagenes/"+nombreArchivoPerfil);
		}else{
			
			newproveedor.setUrlFotoPerfil("imagenes/hogarlogo.jpg");
			
			
		}
		
		if(fotoPortada!=null){
			savefotoportada();
			newproveedor.setUrlFotoPortada("imagenes/"+nombreArchivoPortada);
		}else{
			
			newproveedor.setUrlFotoPortada("imagenes/portada.jpg");
			
			
		}
		
		newproveedor.setPassword(MD5(newproveedor.getPassword()));
		provdao.guardar(newproveedor);
		List<Proveedor> aux_lista_proveedor = provdao.getProveedores();
		for (Proveedor prov: aux_lista_proveedor) {
			provLog.log(Level.INFO, prov.toString());
		}
		init();
		return null;
	}
	
	public void respComentarios(){
		
	}
		
	public String agregarTelefono() {							//Metodo Agregar Contacto
		System.out.println(newproveedor.getListaTelefonos().size());
		newproveedor.getListaTelefonos().add(new Telefono());
		return null;
	}
	
	
	
	
	public String borraVistaTelefono(Telefono telefono) {
		newproveedor.getListaTelefonos().remove(telefono);
		return null;
	}
	
	public String editar(int codigo) {
		
		
		Proveedor prov = provdao.read(codigo);
		newproveedor = prov;

		listaTelefonos = provdao.getProveedoresTelefonos(newproveedor.getCodigo());
		newproveedor.getListaTelefonos().clear();
		newproveedor.setListaTelefonos(listaTelefonos);


		return "prov_proveedor_editar";
	}
		
	public String ver(int codigo) {
		Proveedor prov = provdao.read(codigo);
		newproveedor = prov;

		listaTelefonos = provdao.getProveedoresTelefonos(newproveedor.getCodigo());
		newproveedor.getListaTelefonos().clear();
		newproveedor.setListaTelefonos(listaTelefonos);

		return "proveedor_ver";
	}
	
	public String comentario(int codigo) {		
		
		for (Proveedor proveedor:lisProvActivos)
		{		
			if(proveedor.getCodigo()==codigo)
			{
				newproveedor=proveedor;
				
			}
		}
		
		listaComentarios=provdao.getProveedoresComentarios(newproveedor.getCodigo());
		newproveedor.getListaComentarios().clear();
		newproveedor.setListaComentarios(listaComentarios);
			

		return "user_comentario";
	}
	

	
	public String eliminar(int codigo){					
		provdao.updateEstado(codigo);
		init();
		return null;
	}
	
	

	public String agregarComentario(String img, String nombreuser) {							//Metodo Agregar Comentario
		System.out.println(newproveedor.getListaComentarios().size());
		
		Comentario comen =newComentario;
		comen.setFecha(fechacomentario);
		comen.setNombreusuario(nombreuser);
		comen.setImagen(img);
		newproveedor.getListaComentarios().add(comen);
		newComentario.setNewusuario(userdao.getUser());
		provdao.guardar(newproveedor);
		List<Proveedor> aux_lista_proveedor = provdao.getProveedores();
		for (Proveedor prov: aux_lista_proveedor) {
			provLog.log(Level.INFO, prov.toString());
		}
		
		
		return null;
	}
	
	

	public void limpiarTodo() {
		init();
	}
	
	
	public String savefotoperfil() {
		try (InputStream input = fotoPerfil.getInputStream()) {
	
			nombreArchivoPerfil = fotoPerfil.getContentType(); 
			
			 Files.copy(input, new File(directorioPerfil, nombreArchivoPerfil).toPath()); System.out.println(
			 "Uploaded file perfil successfully saved in " + fotoPerfil);

		} catch (IOException e) {
			// Show faces message?
		}
		return "";
	}
	
	public String savefotoportada() {
		try (InputStream input = fotoPortada.getInputStream()) {
	
			nombreArchivoPortada = fotoPortada.getContentType(); 
			//fotoPortada.getSubmittedFileName();
			 Files.copy(input, new File(directorioPortada, nombreArchivoPortada).toPath()); System.out.println(
			 "Uploaded file portada successfully saved in " + fotoPortada);

		} catch (IOException e) {
			// Show faces message?
		}
		return "";
	}
	
	public void cargarProveedor(){
		newproveedor=provdao.getProve();
	}
	
	public void cargarClientes(){
		listaClientesFiltrados=provdao.getClientesFiltrados(provdao.getProve().getCodigo());
	}
	
	public String cargarMapa(Proveedor p ){
		auxProveedor = p;
		return("mapa_proveedores");
	}


}

