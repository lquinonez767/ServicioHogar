package ec.appservicios.negocio;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.appservicios.modelo.*;

public class ProveedorUsuarioDAO implements Serializable{
	
	@Inject
	private EntityManager em;
	public ProveedorUsuarioDAO() {
		
	}
	
	public ProveedorUsuario read(int codigo){
		return em.find(ProveedorUsuario.class, codigo);
	}
	
	public void insert(ProveedorUsuario proveusuario){
		em.persist(proveusuario);
	}
	
	public void update(ProveedorUsuario proveusuario){
		em.merge(proveusuario);	
		}
	
	public void remove(int codigo){
		
		ProveedorUsuario proveusuario = em.find(ProveedorUsuario.class, codigo);
		em.remove(proveusuario);
	}
	
	public void guardar(ProveedorUsuario proveusuario) {
		if (em.find(ProveedorUsuario.class, proveusuario.getCodigo()) == null) {
			insert(proveusuario);
		} else {
			update(proveusuario);
		}
	}
		

	public List<ProveedorUsuario> getProveUsuarios() {
		String sql = "SELECT p FROM ProveedorUsuario p";
		Query query = em.createQuery(sql, ProveedorUsuario.class);
		return query.getResultList();
	}
	
	
	public List<ProveedorUsuario> getProvedoresFiltrados(int codigo) {
		String sql = "SELECT pf FROM ProveedorUsuario pf where usuario='" + codigo + "'";
		Query query = em.createQuery(sql, ProveedorUsuario.class);
		return query.getResultList();
	}
	
	public List<ProveedorUsuario> getUsuariosFiltradas(int codigo) {
		String sql = "SELECT vu FROM VeterinariaUsuario vu where veterinaria='" + codigo + "'";
		Query query = em.createQuery(sql, ProveedorUsuario.class);
		return query.getResultList();
	}

}
