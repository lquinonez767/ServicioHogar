package ec.appservicios.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.appservicios.modelo.Cita;
import ec.appservicios.modelo.Comentario;

@Stateless
public class ComentarioDAO {

	@Inject
	private EntityManager em;
	
	public boolean insert(Comentario comentario){
		try {
			em.persist(comentario);
			return true;
		}catch (Exception e) {
			System.out.println("error al insertar cometario");
			return false;
			// TODO: handle exception
		}
	}
	
	public boolean update(Comentario comentario){
		try {
			em.merge(comentario);
			return true;
		}catch (Exception e) {
			System.out.println("error al actualizar cometario");
			return false;
			// TODO: handle exception
		}
	}
	
	public List<Comentario> getLtsComenByPro(int idProveedor){
		String sql="SELECT c FROM Comentario c where c.";
		Query query=em.createQuery(sql,Comentario.class);
		return query.getResultList();
	}
}
