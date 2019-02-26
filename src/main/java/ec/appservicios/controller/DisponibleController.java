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

@ManagedBean(name="disponible")
@SessionScoped
public class DisponibleController {
	
	private Disponible newDisponible;
	
	private List<Disponible> listaDisponible;
	private List<Proveedor> listprove;
		
	private Date fechaDisponible= new Date();
	private Date horaInicio= new Date();
	private Date horaFin= new Date();
	
		
	@Inject
	private DisponibleDAO disdao;
	@Inject
	private ProveedorDAO provdao;
	@Inject
	private Logger disLOG;
	
	
	@PostConstruct
	public void init(){
		
		newDisponible= new Disponible();
		listaDisponible=disdao.getDisponibles();
		listprove=disdao.getProveedoresActivos();
		
	}
	
	public Disponible getNewDisponible() {
		return newDisponible;
	}

	public void setNewDisponible(Disponible newDisponible) {
		this.newDisponible = newDisponible;
	}

	public List<Disponible> getListaDisponible() {
		return listaDisponible;
	}

	public void setListaDisponible(List<Disponible> listaDisponible) {
		this.listaDisponible = listaDisponible;
	}

	public List<Proveedor> getlistprove() {
		return listprove;
	}

	public void setlistprove(List<Proveedor> listprove) {
		this.listprove = listprove;
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

	public DisponibleDAO getDisdao() {
		return disdao;
	}

	public void setDisdao(DisponibleDAO disdao) {
		this.disdao = disdao;
	}

	public Logger getDisLOG() {
		return disLOG;
	}

	public void setDisLOG(Logger disLOG) {
		this.disLOG = disLOG;
	}

	
	public String borraDisponibilidad(int codigo) {
		disdao.remove(codigo);
		init();
		return null;
	}
	
	
	
	public String guardar()
	{
		
		newDisponible.setFecha(new SimpleDateFormat("dd-MM-yyyy").format(fechaDisponible));
		newDisponible.setHoraInicio(horaInicio.getHours()+":"+horaInicio.getMinutes());
		newDisponible.setHoraFin(horaFin.getHours()+":"+horaFin.getMinutes());
		newDisponible.setnombreProve(provdao.getProve().getNombre());
		newDisponible.setnewProveedor(provdao.getProve());
		disdao.guardar(newDisponible);
		List<Disponible> aux_lista_dis = disdao.getDisponibles();
		for (Disponible disp: aux_lista_dis) {
			disLOG.log(Level.INFO, disp.toString());
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
		}
		
		for(Mascota mas:listMascota){
			
			if(mas.getNombre().equals(user)){
				nombreUsuario1=mas.getUsuario().getNombre();
				break;
			}
		}
		
		*/
	}
	
	
	
	
}
