package ec.appservicios.negocio;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.appservicios.modelo.NuevoProductoServicio;


@Stateless
public class NuevoProductoServicioDAO implements Serializable {

	@Inject
	private EntityManager em;
	public NuevoProductoServicioDAO() {
		
	}
	
	public NuevoProductoServicio read(int codigo){
		return em.find(NuevoProductoServicio.class, codigo);
	}
	
	public void insert(NuevoProductoServicio medicamento){
		em.persist(medicamento);
	}
	
	public void update(NuevoProductoServicio medicamento){
		em.merge(medicamento);	
		}
	
	public void remove(int codigo){
		
		NuevoProductoServicio medicamento = em.find(NuevoProductoServicio.class, codigo);
		em.remove(medicamento);
	}
	
	public void guardar(NuevoProductoServicio medicamento) {
		if (em.find(NuevoProductoServicio.class, medicamento.getCodigo()) == null) {
			insert(medicamento);
		} else {
			update(medicamento);
		}
	}
	
	public List<NuevoProductoServicio> getProductos() {
		String sql = "SELECT m FROM NuevoProductoServicio m";
		Query query = em.createQuery(sql, NuevoProductoServicio.class);
		return query.getResultList();
	}

}
