package ec.appservicios.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.appservicios.modelo.*;

@Stateless
public class ValoracionDAO {

	@Inject
	private EntityManager em;
	
	public ValoracionDAO(){
		
	}
	
	public Valoracion read(int codigo){
		return em.find(Valoracion.class, codigo);
	}
	
	public void insert(Valoracion valoracion){
		em.persist(valoracion);
	}
	
	public void update(Valoracion valoracion){
		em.merge(valoracion);	
		}
	
	public void remove(int codigo){
		Valoracion valoracion = em.find(Valoracion.class, codigo);
		em.remove(valoracion);
	}
	
	public void guardar (Valoracion valoracion){
		if(em.find(Valoracion.class, valoracion.getCodigo())==null){
			insert(valoracion);
		} else {
			update(valoracion);
		}
	}
	
	public List<Valoracion> getValoraciones(){
		String sql="SELECT v FROM Valoracion v";
		Query query=em.createQuery(sql,Valoracion.class);
		return query.getResultList();
	}
}
