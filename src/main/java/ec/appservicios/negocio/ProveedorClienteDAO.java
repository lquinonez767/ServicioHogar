package ec.appservicios.negocio;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ec.appservicios.modelo.*;

@Stateless
public class ProveedorClienteDAO implements Serializable{
	
	@Inject
	private EntityManager em;
	public ProveedorClienteDAO() {
		
	}
	
	public ProveedorCliente read(int codigo){
		return em.find(ProveedorCliente.class, codigo);
	}
	
	public ProveedorCliente insert(ProveedorCliente provcliente){
		try {
			em.persist(provcliente);
			em.flush();
			return provcliente;
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}
	
	public void update(ProveedorCliente provcliente){
		em.merge(provcliente);	
		}
	
	public void remove(int codigo){
		
		ProveedorCliente provcliente = em.find(ProveedorCliente.class, codigo);
		em.remove(provcliente);
	}
	
	public void guardar(ProveedorCliente provcliente) {
		if (em.find(ProveedorCliente.class, provcliente.getCodigo()) == null) {
			insert(provcliente);
		} else {
			update(provcliente);
		}
	}
		

	public List<ProveedorCliente> getVeteMascotas() {
		String sql = "SELECT v FROM ProveedorCliente v";
		Query query = em.createQuery(sql, ProveedorCliente.class);
		return query.getResultList();
	}
	
	public List<Proveedor> getProveedorActivos() {
		String sql = "SELECT v FROM Proveedor v WHERE v.estado = 'ACTIVO'";
		Query query = em.createQuery(sql, Proveedor.class);
		return query.getResultList();
	}
	
	public List<Usuario> getUsuariosActivos() {
		String sql = "SELECT u FROM Usuario u WHERE u.estado = 'ACTIVO'";
		Query query = em.createQuery(sql, Usuario.class);
		return query.getResultList();
	}

	
	public List<ProveedorCliente> getProveedoresFiltrados(int codigo) {
		String sql = "SELECT pc FROM ProveedorCliente pc where cliente='" + codigo + "'";
		Query query = em.createQuery(sql, ProveedorCliente.class);
		return query.getResultList();
	}
	
	public List<ProveedorCliente> getClientesFiltrados(int codigo) {
		String sql = "SELECT pc FROM ProveedorCliente pc where proveedor='" + codigo + "'";
		Query query = em.createQuery(sql, ProveedorCliente.class);
		return query.getResultList();
	}

	public ProveedorCliente findByProveedorCliente(int idProveedor, int idCliente) {
		String sql = "SELECT pc FROM ProveedorCliente pc where pc.newProveedor.codigo= :idProveedor "
				+ "and pc.cliente.usuario.codigo = :idCliente";
		TypedQuery<ProveedorCliente> query = em.createQuery(sql, ProveedorCliente.class);
		query.setParameter("idProveedor", idProveedor);
		query.setParameter("idCliente", idCliente);
		try {
			ProveedorCliente proveedorCliente = query.getSingleResult();
			return proveedorCliente;
		}catch (Exception e) {
			System.out.println("error findByProveedorCliente "+this.getClass().getName());
			
			return null;
			// TODO: handle exception
		}
	}
}
