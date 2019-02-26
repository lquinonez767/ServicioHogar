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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.Part;

import ec.appservicios.negocio.*;
import ec.appservicios.modelo.*;

@ManagedBean(name="cliente_bean")
@SessionScoped
public class ClienteController {

	private Cliente newCliente;
	private Proveedor newProveedor;
	private ProveedorCliente newProveCliente;
	
	private List<Cliente> listaClientes;
	private List<Diagnostico> listaDiagnosticos;
	private List<Cliente> listaClientesActivo;
	private List<Usuario> listaUsuarios;
	
	private int cod=0;
	
	private Date fechaNacimiento;
	private Date fecha;
	private Date fechaProxi;
	
	@Inject
	private ProveedorDAO provdao;
	
	@Inject
	private UsuarioDAO userdao;
	
	@Inject
	private ClienteDAO clidao;
	@Inject
	private Logger masLOG;
	
	
	private Part fotoCliente;
	private String directorioCliente = "C:\\Users\\Lenin\\eclipse-workspace\\ServiciosHogar\\src\\main\\webapp\\imagenes\\"; 
	private String nombreArchivoCliente;
	
	
	
	
	@PostConstruct
	public void init() {
		masLOG.log(Level.INFO, "INICIANDO...");
		newCliente = new Cliente();
		listaClientes=clidao.getClientes();
		listaClientesActivo=clidao.getClientesActivos();
		newCliente.setEstado("ACTIVO");
		fotoCliente=null;
		listaUsuarios=clidao.getUsuarios();
		newProveCliente=new ProveedorCliente();
		newProveedor=new Proveedor();
		fechaNacimiento=new Date();
		fecha=new Date();
		fechaProxi=new Date();
	}
	

	public Cliente getNewCliente() {
		return newCliente;
	}
	public void setNewCliente(Cliente newCliente) {
		this.newCliente = newCliente;
	}
	public List<Cliente> getListaMascotas() {
		return listaClientes;
	}
	public void setListaMascotas(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	public List<Diagnostico> getListaDiagnosticos() {
		return listaDiagnosticos;
	}
	public void setListaDiagnoticos(List<Diagnostico> listaDiagnosticos) {
		this.listaDiagnosticos = listaDiagnosticos;
	}
	public ClienteDAO getClidao() {
		return clidao;
	}
	public void setClidao(ClienteDAO clidao) {
		this.clidao = clidao;
	}
	public Logger getMasLOG() {
		return masLOG;
	}
	public void setMasLOG(Logger masLOG) {
		this.masLOG = masLOG;
	}
	public List<Cliente> getListaClientesActivo() {
		return listaClientesActivo;
	}
	public void setListaClientesActivo(List<Cliente> listaMascotasActivo) {
		this.listaClientesActivo = listaMascotasActivo;
	}	
	public Proveedor getNewProveedor() {
		return newProveedor;
	}
	public void setNewProveedor(Proveedor newProveedor) {
		this.newProveedor = newProveedor;
	}
	public ProveedorCliente getNewProveCliente() {
		return newProveCliente;
	}
	public void setNewProveCliente(ProveedorCliente newProveCliente) {
		this.newProveCliente = newProveCliente;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	public Part getFotoCliente() {
		return fotoCliente;
	}
	public void setFotoCliente(Part fotoCliente) {
		this.fotoCliente = fotoCliente;
	}
	public String getDirectorioCliente() {
		return directorioCliente;
	}
	public void setDirectorioCliente(String directorioCliente) {
		this.directorioCliente = directorioCliente;
	}
	public String getNombreArchivoCliente() {
		return nombreArchivoCliente;
	}
	public void setNombreArchivoCliente(String nombreArchivoCliente) {
		this.nombreArchivoCliente = nombreArchivoCliente;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getFechaProxi() {
		return fechaProxi;
	}
	public void setFechaProxi(Date fechaProxi) {
		this.fechaProxi = fechaProxi;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public String guardar() {
		
		if(fotoCliente!=null){
			savefotocliente();
			newCliente.setUrlFotoCliente("imagenes/"+nombreArchivoCliente);
		}else{

			newCliente.setUrlFotoCliente("imagenes/mascota.png");

		}
		
		newCliente.setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").format(fechaNacimiento));
		clidao.guardar(newCliente);
		List<Cliente> aux_lista_cliente = clidao.getClientes();
		for (Cliente cli: aux_lista_cliente) {
			masLOG.log(Level.INFO, cli.toString());
		}
		
		init();
		return null;
	}
	
	
	public String agregarDiagnostico() {
		
		Diagnostico diag=new Diagnostico();
		diag.setFecha(new SimpleDateFormat("dd-MM-yyyy").format(fecha));
		diag.setProximaCita(new SimpleDateFormat("dd-MM-yyyy").format(fechaProxi));
		newCliente.getListaDiagnosticos().add(diag);
		return null;
	}
	
	
	
	
    
	public String borraVistaDiagnostico(Diagnostico diagnostico) {
		newCliente.getListaDiagnosticos().remove(diagnostico);
		return null;
	}

	public String editar(int codigo) {
	
	
		Cliente cliente = clidao.read(codigo);
		newCliente = cliente;

		listaDiagnosticos = clidao.getDiagnosticoCliente(newCliente.getCodigo());
		newCliente.getListaDiagnosticos().clear();
		newCliente.setListaDiagnosticos(listaDiagnosticos);


		return null;
	}
	
	public String ver(int codigo) {
		Cliente cliente = clidao.read(codigo);
		newCliente = cliente;

		listaDiagnosticos = clidao.getDiagnosticoCliente(newCliente.getCodigo());
		newCliente.getListaDiagnosticos().clear();
		newCliente.setListaDiagnosticos(listaDiagnosticos);
	
		return null;
	}
	
	public String eliminar(int codigo) {
		clidao.updateEstado(codigo);
		init();
		return null;
	}
	
	public void limpiarTodo() {
		init();
	}
	
	public String agregarClienteUser(){							//Metodo Agregar 
		
		newCliente.setUsuario(userdao.getUser());
		guardar();
		return null;
	}
	
	public String agregarProveedorCliente() {							//Metodo Agregar ProveddorCli y Cli
		
		
		newProveCliente.setNewProveedor(provdao.getProve());
		newProveCliente.setNombreProve(provdao.getProve().getNombre());
		newProveCliente.setNombreCliente(newCliente.getNombre());
		newCliente.getListaProveCliente().add(newProveCliente);
		
		if (cod!=0)
		{
			for (Usuario user:listaUsuarios){			
				if(user.getCodigo()==cod){
					newCliente.setUsuario(user);
				}
				
			}
			
		}
		guardar();
		return null;
	}
	
	public String savefotocliente() {
		try (InputStream input = fotoCliente.getInputStream()) {
			
			nombreArchivoCliente = fotoCliente.getName(); 
			
			 Files.copy(input, new File(directorioCliente, nombreArchivoCliente).toPath()); System.out.println(
			 "Uploaded file successfully saved in " + fotoCliente);
			 

		} catch (IOException e) {
			// Show faces message?
		}
		return "";
	}
	

}
