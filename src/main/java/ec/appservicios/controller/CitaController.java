package ec.appservicios.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import ec.appservicios.modelo.*;
import ec.appservicios.negocio.*;


@ManagedBean(name="cita")
@SessionScoped
public class CitaController {
	
	private Cita newCita;
	
	private List<Cita> listaCita;
	private List<Cliente> listCliente;
	private List<Proveedor> listprove;
	private List<ProveedorCliente> listproveCliente;
	private List<ProveedorUsuario> listproveUser;
	
	private int codprove;
	private Date fecha= new Date();
	private Date hora= new Date();
	
	private Date fechaDisponible= new Date();
	private Date horaInicio= new Date();
	private Date horaFin= new Date();
	
	private String nombreUsuario1;
	
	@Inject
	private CitaDAO citadao;
	@Inject
	private Logger citaLOG;
	
	
	@PostConstruct
	public void init(){
		
		newCita= new Cita();
		listaCita=citadao.getCitas();
		listprove=citadao.getProveedoresActivos();
		listCliente=citadao.getClientes();
		listproveCliente=citadao.getProveClientes();
		listproveUser=citadao.getProveUsuarios();
	}
	public Cita getNewCita() {
		return newCita;
	}
	public void setNewCita(Cita newCita) {
		this.newCita = newCita;
	}
	public List<Cita> getListaCita() {
		return listaCita;
	}
	public void setListaCita(List<Cita> listaCita) {
		this.listaCita = listaCita;
	}
	public CitaDAO getCitadao() {
		return citadao;
	}
	public void setCitadao(CitaDAO citadao) {
		this.citadao = citadao;
	}
	public Logger getCitaLOG() {
		return citaLOG;
	}
	public void setCitaLOG(Logger citaLOG) {
		this.citaLOG = citaLOG;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
	}
	public int getcodprove() {
		return codprove;
	}
	public void setcodprove(int codprove) {
		this.codprove = codprove;
	}
	public List<Proveedor> getlistprove() {
		return listprove;
	}
	public void setlistprove(List<Proveedor> listprove) {
		this.listprove = listprove;
	}	
	public String getNombreUsuario1() {
		return nombreUsuario1;
	}
	public void setNombreUsuario1(String nombreUsuario1) {
		this.nombreUsuario1 = nombreUsuario1;
	}
	public Date getFechaDisponible() {
		return fechaDisponible;
	}
	public void setFechaDisponible(Date fechaDisponible) {
		this.fechaDisponible = fechaDisponible;
	}
	public Date getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Date getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}
	
	
	
	
	public String guardar()
	{
		
		newCita.setFecha(new SimpleDateFormat("dd-MM-yyyy").format(fecha));
		newCita.setHora(hora.getHours()+":"+hora.getMinutes());
		citadao.guardar(newCita);
		List<Cita> aux_lista_cita = citadao.getCitas();
		for (Cita cita: aux_lista_cita) {
			citaLOG.log(Level.INFO, cita.toString());
		}
		init();
		
		return null;
	}
	
	public void nombreUser(String user){
		
		/*for(ProveedorUsuario vetuser:listproveUser){
			
			if(vetuser.getNombreVete().equals(user)){
				nombreUsuario1=vetuser.getNombreUsuario();
				System.out.println("usssssss "+nombreUsuario1);
				System.out.println(user);
				break;
			}
		}*/
		
		for(Cliente mas:listCliente){
			
			if(mas.getNombre().equals(user)){
				nombreUsuario1=mas.getUsuario().getNombre();
				break;
			}
		}
		
		
	}
	
	
	
	
}
