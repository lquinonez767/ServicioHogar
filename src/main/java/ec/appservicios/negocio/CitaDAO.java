package ec.appservicios.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.appservicios.modelo.*;

@Stateless
public class CitaDAO {
	
	@Inject
	private EntityManager em;
	
	public CitaDAO(){
		
	}
	
	public Cita read(int codigo){
		return em.find(Cita.class, codigo);
	}
	
	public void insert(Cita cita){
		em.persist(cita);
	}
	
	public void update(Cita cita){
		em.merge(cita);	
		}
	
	public void remove(int codigo){
		Cita cita = em.find(Cita.class, codigo);
		em.remove(cita);
	}
	
	public void guardar (Cita cita){
		if(em.find(Cita.class, cita.getCodigo())==null){
			insert(cita);
		} else {
			update(cita);
		}
	}
	
	public List<Cita> getCitas(){
		String sql="SELECT c FROM Cita c";
		Query query=em.createQuery(sql,Cita.class);
		return query.getResultList();
	}
	
	public List<Cita> getCitas(int codigo){
		String sql="SELECT c FROM Cita c where proveedorcliente='"+codigo+"'";
		Query query=em.createQuery(sql,Cita.class);
		return query.getResultList();
	}
	
	public List<ProveedorCliente> getProveedorClientes(){
		String sql="SELECT p FROM Proveedor p";
		Query query=em.createQuery(sql,ProveedorCliente.class);
		return query.getResultList();
	}
	
	public List<Proveedor> getProveedoresActivos() {
		String sql = "SELECT p FROM Proveedor p WHERE p.estado = 'ACTIVO'";
		Query query = em.createQuery(sql, Proveedor.class);
		return query.getResultList();
	}
	

	public List<ProveedorCliente> getProveClientes() {
		String sql = "SELECT pc FROM ProveedorCliente pc";
		Query query = em.createQuery(sql, ProveedorCliente.class);
		return query.getResultList();
	}
	
	public List<ProveedorUsuario> getProveUsuarios() {
		String sql = "SELECT pu FROM ProveedorUsuario pu";
		Query query = em.createQuery(sql, ProveedorUsuario.class);
		return query.getResultList();
	}
	
	public List<Cliente> getClientes() {
		String sql = "SELECT c FROM Cliente c";
		Query query = em.createQuery(sql, Cliente.class);
		return query.getResultList();
	}

}
