package ec.appservicios.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;

//import org.primefaces.util.*;

import javax.servlet.http.Part;

import ec.appservicios.modelo.*;
import ec.appservicios.negocio.*;

@ManagedBean(name="usuario")
@SessionScoped
public class UsuarioController {

	
	private Usuario newUsuario;
	private ProveedorUsuario newProvUser;
	private Proveedor newProveedor;
	
	private List<Usuario> listaUsuarios;
	private List<Telefono> listaTelefonos;
	private List<Comentario> listaComentarios;
	private List<Valoracion> listaValoraciones;
	private List<Proveedor> listaProveedores;
	private List<ProveedorUsuario> listaProveUsuario;
	private List<Usuario> listUsuariosFiltrados;
	private List<Cliente> listaClientesFiltrados;
	
	@Inject
	private UsuarioDAO userdao;
	@Inject
	private ProveedorDAO provdao;
	@Inject
	private ProveedorUsuarioDAO provuserdao;
	@Inject
	private Logger userLOG;
		
	private Part fotoPerfil;
	private String directorioPerfil = "C:\\Users\\Lenin\\eclipse-workspace\\ServiciosHogar\\src\\main\\webapp\\imagenes"; 
	private String nombreArchivoPerfil;
	
	private String nomProve;

	
	@PostConstruct
	public void init(){
		userLOG.log(Level.INFO, "INICIANDO...");
		newUsuario=new Usuario();
		newUsuario.setEstado("ACTIVO");
		fotoPerfil=null;
		listaUsuarios=userdao.getUsuariosActivos();
		listaComentarios=userdao.getComentarios();
		listaValoraciones=userdao.getValoraciones();
		listaProveedores=provdao.getProveedoresActivos();
		
		newProveedor=new Proveedor();
		newProvUser= new ProveedorUsuario();
	}
		

	public Usuario getNewUsuario() {
		return newUsuario;
	}
	public void setNewUsuario(Usuario newUsuario) {
		this.newUsuario = newUsuario;
	}
	public UsuarioDAO getUserdao() {
		return userdao;
	}
	public void setUserdao(UsuarioDAO userdao) {
		this.userdao = userdao;
	}	
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	public List<Telefono> getListaTelefonos() {
		return listaTelefonos;
	}
	public void setListaTelefonos(List<Telefono> listaTelefonos) {
		this.listaTelefonos = listaTelefonos;
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
	public Logger getUserLOG() {
		return userLOG;
	}
	public void setUserLOG(Logger userLOG) {
		this.userLOG = userLOG;
	}
	public Part getFotoPerfil() {
		return fotoPerfil;
	}
	public void setFotoPerfil(Part fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	public String getNomProve() {
		return nomProve;
	}
	public void setNomProve(String nomProve) {
		this.nomProve = nomProve;
	}
	public List<Proveedor> getListaProveedores() {
		return listaProveedores;
	}
	public void setListaProveedores(List<Proveedor> listaProveedores) {
		this.listaProveedores = listaProveedores;
	}
	public List<ProveedorUsuario> getListaProvUsuario() {
		return listaProveUsuario;
	}
	public void setListaProveUsuario(List<ProveedorUsuario> listaProveUsuario) {
		this.listaProveUsuario = listaProveUsuario;
	}
	public List<Usuario> getListUsuariosFiltrados() {
		return listUsuariosFiltrados;
	}
	public void setListUsuariosFiltrados(List<Usuario> listUsuariosFiltrados) {
		this.listUsuariosFiltrados = listUsuariosFiltrados;
	}
	public List<Cliente> getListaClientesFiltrados() {
		return listaClientesFiltrados;
	}
	public void setListaMascotasFiltradas(List<Cliente> listaClientesFiltrados) {
		this.listaClientesFiltrados = listaClientesFiltrados;
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
	
	
	

	
	public String guardar()
	{
		if(fotoPerfil!=null){
			savefotoperfil();
			newUsuario.setUrlFotoPerfil("imagenes/"+nombreArchivoPerfil);
		}else{			
				newUsuario.setUrlFotoPerfil("imagenes/usuario.png");	
		}
		//System.out.println("get paassssssss: "+newUsuario.getPassword());
		newUsuario.setPassword(MD5(newUsuario.getPassword()));
		userdao.guardar(newUsuario);
		List<Usuario> aux_lista_usuario = userdao.getUsuarios();
		for (Usuario user: aux_lista_usuario) {
			userLOG.log(Level.INFO, user.toString());
		}
		init();
		
		return null;
	}

	
	public String agregarTelefono() {
		newUsuario.getListaTelefonos().add(new Telefono());
		return null;
	}

    
	public String borraVistaTelefono(Telefono telf) {
		newUsuario.getListaTelefonos().remove(telf);
		return null;
	}
	
	
	public String ver(int codigo) {
		Usuario user = userdao.read(codigo);
		newUsuario = user;

		listaTelefonos=userdao.getUsuariosTelefonos(newUsuario.getCodigo()); 
		newUsuario.getListaTelefonos().clear();
		newUsuario.setListaTelefonos(listaTelefonos);

		return null;
	}
	
	
	public String eliminar(int codigo){					
		userdao.updateEstado(codigo);
		init();
		return null;
	}
	
	public String agregarProveedorUsuario() {							//Metodo Agregar 
		
		newProvUser.setNewProveedor(provdao.getProve());
		newProvUser.setNombreProve(nomProve);
		newProvUser.setNombreUsuario(newUsuario.getNombre());
		newUsuario.getListaProveUsuarios().add(newProvUser);
		guardar();
		return null;
	}
	
	public void registroUsuario(){
		
		for (Proveedor prove:listaProveedores)
		{		
			if(prove.getNombre().equals(nomProve))
			{
				provdao.setProve(prove);

				break;		
			}
		}
		
		agregarProveedorUsuario();
		
	}
	
	
	
	public String savefotoperfil() {
		try (InputStream input = fotoPerfil.getInputStream()) {
			
			nombreArchivoPerfil = fotoPerfil.getName(); 
			
			 Files.copy(input, new File(directorioPerfil, nombreArchivoPerfil).toPath()); System.out.println(
			 "Uploaded file successfully saved in " + fotoPerfil);
			 

		} catch (IOException e) {
			// Show faces message?
		}
		return "";
	}
	
	public void cargarUsuario(){
		newUsuario=userdao.getUser();
	}
	
	public void cargarClientes(){
		listaClientesFiltrados=userdao.getFiltrarClientesUsuario(userdao.getUser().getCodigo());
	}
	
	public void cargarClientes1(int codigo){
		listaClientesFiltrados=userdao.getFiltrarClientesUsuario(codigo);
	}
	

	
}
