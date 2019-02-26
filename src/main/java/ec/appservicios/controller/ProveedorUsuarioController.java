package ec.appservicios.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import ec.appservicios.modelo.*;
import ec.appservicios.negocio.*;


@ManagedBean(name="proveedorUsuario")
@SessionScoped
public class ProveedorUsuarioController {
	
	private ProveedorUsuario newProveUsuario;
	private List<ProveedorUsuario> listaproveUser;
	private List<ProveedorUsuario> listaProveUsuarioProveedor;
	private List<ProveedorUsuario> listaProveUsuarioUsuario;


		
	@Inject
	private ProveedorUsuarioDAO provuserdao;
	@Inject
	private Logger provuserLog;
	
	@PostConstruct
	public void init(){
		provuserLog.log(Level.INFO, "INICIANDO proveedor user...");
		newProveUsuario=new ProveedorUsuario();
		listaproveUser=provuserdao.getProveUsuarios();
		//listaVeteMascota=vetmasdao.getVeteMascotas();

		
		
	}
	
	public ProveedorUsuario getnewProveUsuario() {
		return newProveUsuario;
	}

	public void setnewProveUsuario(ProveedorUsuario newProveUsuario) {
		this.newProveUsuario = newProveUsuario;
	}

	public List<ProveedorUsuario> getListaProveUsuarioProveedor() {
		return listaProveUsuarioProveedor;
	}

	public void setlistaProveUsuarioProveedor(List<ProveedorUsuario> listaProveUsuarioProveedor) {
		this.listaProveUsuarioProveedor = listaProveUsuarioProveedor;
	}

	public List<ProveedorUsuario> getlistaProveUsuarioUsuario() {
		return listaProveUsuarioUsuario;
	}

	public void setlistaProveUsuarioUsuario(List<ProveedorUsuario> listaProveUsuarioUsuario) {
		this.listaProveUsuarioUsuario = listaProveUsuarioUsuario;
	}

	public ProveedorUsuarioDAO getprovuserdao() {
		return provuserdao;
	}

	public void setprovuserdao(ProveedorUsuarioDAO provuserdao) {
		this.provuserdao = provuserdao;
	}

	public Logger getprovuserLog() {
		return provuserLog;
	}

	public void setprovuserLog(Logger provuserLog) {
		this.provuserLog = provuserLog;
	}

	
	
	public void filtrarVeterinarias(int codigo){
		
		listaProveUsuarioProveedor=provuserdao.getProvedoresFiltrados(codigo);
		
		
	}
	
	public void filtrarUsuarios(int codigo){
		
		listaProveUsuarioUsuario=provuserdao.getUsuariosFiltradas(codigo);
		
		
	}

	public List<ProveedorUsuario> getlistaproveUser() {
		return listaproveUser;
	}

	public void setlistaproveUser(List<ProveedorUsuario> listaproveUser) {
		this.listaproveUser = listaproveUser;
	}
	
}
