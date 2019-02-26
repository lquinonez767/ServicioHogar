package ec.appservicios.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.appservicios.modelo.*;

@Stateless
public class DisponibleDAO {
	
	@Inject
	private EntityManager em;
	
	public DisponibleDAO(){
		
	}
	
	public Disponible read(int codigo){
		return em.find(Disponible.class, codigo);
	}
	
	public void insert(Disponible disponible){
		em.persist(disponible);
	}
	
	public void update(Disponible disponible){
		em.merge(disponible);	
		}
	
	public void remove(int codigo){
		Disponible disponible = em.find(Disponible.class, codigo);
		em.remove(disponible);
	}
	
	public void guardar (Disponible disponible){
		if(em.find(Disponible.class, disponible.getCodigo())==null){
			insert(disponible);
		} else {
			update(disponible);
		}
	}
	
	public List<Disponible> getDisponibles(){
		String sql="SELECT c FROM Disponible c";
		Query query=em.createQuery(sql,Disponible.class);
		return query.getResultList();
	}
	
	public List<Cita> getCitas(int codigo){
		String sql="SELECT c FROM Cita c where ='"+codigo+"'";
		Query query=em.createQuery(sql,Cita.class);
		return query.getResultList();
	}
	
	public List<ProveedorCliente> getProveedoresClientes(){
		String sql="SELECT p FROM ProveedorCliente p";
		Query query=em.createQuery(sql,ProveedorCliente.class);
		return query.getResultList();
	}
	
	public List<Proveedor> getProveedoresActivos() {
		String sql = "SELECT p FROM Proveedor p WHERE p.estado = 'ACTIVO'";
		Query query = em.createQuery(sql, Proveedor.class);
		return query.getResultList();
	}
	

	public List<ProveedorCliente> getProveClientes() {
		String sql = "SELECT p FROM ProveedorCliente p";
		Query query = em.createQuery(sql, ProveedorCliente.class);
		return query.getResultList();
	}
	
	public List<ProveedorUsuario> getProveUsuarios() {
		String sql = "SELECT p FROM ProveedorUsuario p";
		Query query = em.createQuery(sql, ProveedorUsuario.class);
		return query.getResultList();
	}
	
	public List<Cliente> getClientes() {
		String sql = "SELECT c FROM Cliente c";
		Query query = em.createQuery(sql, Cliente.class);
		return query.getResultList();
	}

}
