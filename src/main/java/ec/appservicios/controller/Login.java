package ec.appservicios.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.appservicios.modelo.*;
import ec.appservicios.negocio.*;

@ManagedBean(name = "login")
@SessionScoped
public class Login {
	
	private Usuario usuario;
	private Proveedor proveedor;
	private List<Usuario> listaUsuarios;
	private List<Proveedor> listaProveedores;
	
	private String username;
	private String password;
	private String redirigir=null;
	

	@Inject
	private UsuarioDAO userdao;
	
	@Inject
	private ProveedorDAO provdao;
	
	@Inject
	private Logger loginLOG;
	
	
	@PostConstruct
	public void init(){
		loginLOG.log(Level.INFO, "Login");
		usuario=new Usuario();
		proveedor=new Proveedor();
		listaUsuarios=userdao.getUsuariosActivos();
		listaProveedores=provdao.getProveedoresActivos();
		
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	public List<Proveedor> getListaProveedores() {
		return listaProveedores;
	}
	public void setListaProveedores(List<Proveedor> listaProveedores) {
		this.listaProveedores = listaProveedores;
	}
	public UsuarioDAO getUserdao() {
		return userdao;
	}
	public void setUserdao(UsuarioDAO userdao) {
		this.userdao = userdao;
	}
	public ProveedorDAO getProvdao() {
		return provdao;
	}
	public void setVetedao(ProveedorDAO provdao) {
		this.provdao = provdao;
	}
	public Logger getLoginLOG() {
		return loginLOG;
	}
	public void setLoginLOG(Logger loginLOG) {
		this.loginLOG = loginLOG;
	}


	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String userLogin(){
		
		int e=0;
		
		for (Usuario user:listaUsuarios)
		{		
			if(user.getUsername().equals(username) && user.getPassword().equals(MD5(password)))
			{
				usuario=user;			
				e=1;
				userdao.setUser(usuario);
				redirigir="user_perfil";
				System.out.println("Usuario correcto: nombre: "+usuario.getNombre());
				//activo=true;
				break;		
			}
		}
		
		if (e==0){
			
			for (Proveedor prov:listaProveedores)
			{		
				if(prov.getUsername().equals(username) && prov.getPassword().equals(MD5(password)))
				{
					proveedor=prov;			
					e=0;
					provdao.setProve(proveedor);
					redirigir="prov_perfil";//vista
					System.out.println("Proveedor correcto: nombre: "+proveedor.getNombre());
					//activo=true;
					break;		
				}
			}			
			
		}else
		{
			//activo=false;
			System.out.println("Usuario no encontrado");
		}
		
		
		
		return redirigir;
	}
	
	public String cerrarSession(){
		
		/*if (activo==true){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		System.out.println("Cerrar");
        return "login.xhtml";// indicas a donde quieres direccionar despu�s de cerrar sesi�n 
		}
		else{
			if (tipo.equals("veterinario")){
				return "vet_usuarios";
			}
			else{
				return "user_usuario_editar";
			}
		}*/
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		System.out.println("Cerrar Sesion log");
        return "login.xhtml";
		
		
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

}
