package ec.appservicios.controller;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;


import ec.appservicios.modelo.Valoracion;
import ec.appservicios.negocio.ValoracionDAO;

@ManagedBean(name="valoracion")
@SessionScoped
public class ValoracionController {
	
	private Valoracion newValoracion=new Valoracion();
	private List<Valoracion> listaValoracion;
	

	@Inject
	private ValoracionDAO valdao;
	@Inject
	private Logger valLOG;
	
	@PostConstruct
	public void init(){
		valLOG.log(Level.INFO, "INICIANDO valoracion...");
		//newValoracion=new Valoracion();
		listaValoracion=valdao.getValoraciones();
	}
	
	
	public Valoracion getNewValoracion() {
		return newValoracion;
	}
	public void setNewValoracion(Valoracion newValoracion) {
		this.newValoracion = newValoracion;
	}
	public List<Valoracion> getListaValoracion() {
		return listaValoracion;
	}
	public void setListaValoracion(List<Valoracion> listaValoracion) {
		this.listaValoracion = listaValoracion;
	}
	public ValoracionDAO getValdao() {
		return valdao;
	}
	public void setValdao(ValoracionDAO valdao) {
		this.valdao = valdao;
	}
	public Logger getValLOG() {
		return valLOG;
	}
	public void setValLOG(Logger valLOG) {
		this.valLOG = valLOG;
	}
			 	
	
	public String guardar()
	{
		valdao.guardar(newValoracion);
		List<Valoracion> aux_lista_valoracion = valdao.getValoraciones();
		for (Valoracion val: aux_lista_valoracion) {
			valLOG.log(Level.INFO, val.toString());
		}
		init();
		
		return null;
	}
	

}
