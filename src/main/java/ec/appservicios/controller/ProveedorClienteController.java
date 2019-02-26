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


@ManagedBean(name="proveedorCliente")
@SessionScoped
public class ProveedorClienteController {
	
	private ProveedorCliente newProveCliente;
	private List<ProveedorCliente> listaProveCliente;
	private List<ProveedorCliente> listaProveClienteCliente;
	private List<Proveedor> listaProveedores;
	private List<Usuario> listaUsuarios;

	private Cliente newCliente;
	
	@Inject
	private ProveedorClienteDAO provclidao;
	@Inject
	private ClienteDAO clidao;
	@Inject
	private Logger provclilog;
	
	@PostConstruct
	public void init(){
		provclilog.log(Level.INFO, "INICIANDO provclilog...");
		newProveCliente=new ProveedorCliente();
		listaProveedores=provclidao.getProveedorActivos();
		listaUsuarios=provclidao.getUsuariosActivos();
		newCliente=new Cliente();
		
	}
	
	
	public ProveedorCliente getnewProveCliente() {
		return newProveCliente;
	}
	public void setnewProveCliente(ProveedorCliente newProveCliente) {
		this.newProveCliente = newProveCliente;
	}
	public List<ProveedorCliente> getlistaProveCliente() {
		return listaProveCliente;
	}
	public void setlistaProveCliente(List<ProveedorCliente> listaProveCliente) {
		this.listaProveCliente = listaProveCliente;
	}
	public ProveedorClienteDAO getprovclidao() {
		return provclidao;
	}
	public void setprovclidao(ProveedorClienteDAO provclidao) {
		this.provclidao = provclidao;
	}
	public Logger getprovCliLog() {
		return provclilog;
	}
	public void setProvCliLog(Logger provclilog) {
		this.provclilog = provclilog;
	}
	public List<Proveedor> getlistaProveedores() {
		return listaProveedores;
	}
	public void setlistaProveedores(List<Proveedor> listaProveedores) {
		this.listaProveedores = listaProveedores;
	}
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	
	
	
	


	public List<ProveedorCliente> getlistaProveClienteCliente() {
		return listaProveClienteCliente;
	}


	public void setlistaProveClienteCliente(List<ProveedorCliente> listaProveClienteCliente) {
		this.listaProveClienteCliente = listaProveClienteCliente;
	}




	public Cliente getNewCliente() {
		return newCliente;
	}


	public void setNewcliente(Cliente newCliente) {
		this.newCliente = newCliente;
	}


	public void filtrarProveedores(int codigo){
				
		Cliente cliente = clidao.read(codigo);
		newCliente = cliente;
		listaProveCliente=provclidao.getProveedoresFiltrados(codigo);
		
		
	}
	
	public void filtrarClientes(int codigo){
		
		listaProveCliente=provclidao.getClientesFiltrados(codigo);
		
		
	}
	
}
