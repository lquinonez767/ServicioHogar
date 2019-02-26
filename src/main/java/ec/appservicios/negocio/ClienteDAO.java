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
public class ClienteDAO implements Serializable{
	
	@Inject
	private EntityManager em;
	
	
	public ClienteDAO() {
		
	}
	
	public Cliente read(int codigo){
		return em.find(Cliente.class, codigo);
	}
	
	public void insert(Cliente cliente){
		em.persist(cliente);
	}
	
	public void update(Cliente cliente){
		em.merge(cliente);//es un update	
		}
	
	public void remove(int codigo){
		
		Cliente cliente = em.find(Cliente.class, codigo);
		em.remove(cliente);
	}
	
	public void guardar(Cliente cliente) {
		if (em.find(Cliente.class, cliente.getCodigo()) == null) {
			insert(cliente);
		} else {
			update(cliente);
		}
	}
	
	public List<Cliente> getClientes() {
		String sql = "SELECT c FROM Cliente c";
		Query query = em.createQuery(sql, Cliente.class);
		return query.getResultList();
	}
	
	public List<Cliente> getClientesActivos() {
		String sql = "SELECT c FROM Cliente c WHERE c.estado = 'ACTIVO'";
		Query query = em.createQuery(sql, Cliente.class);
		return query.getResultList();
	}
	
	public void updateEstado(int codigo) {
		String sql = "UPDATE Cliente SET estado = '" + "INACTIVO" + "' WHERE codigo = '" + codigo + "'";
		Query query = em.createQuery(sql);
		query.executeUpdate();
	}
	
	public List<Diagnostico> getDiagnosticos() {
		String sql = "SELECT d FROM Diagnostico d";
		Query query = em.createQuery(sql, Diagnostico.class);
		return query.getResultList();
	}

	public List<Diagnostico> getDiagnosticoCliente(int codigo) {
		String sql = "SELECT d FROM Diagnostico d where cliente='" + codigo + "'";
		Query query = em.createQuery(sql, Diagnostico.class);
		return query.getResultList();
	}
	
	public List<Usuario> getUsuarios(){
		String sql="SELECT u FROM Usuario u";
		Query query=em.createQuery(sql,Usuario.class);
		return query.getResultList();
	}

	
	public List<Cliente> getFiltrarClientesUsuario(int codigo) {
		String sql = "SELECT c FROM Cliente m where propietario='"+codigo+"'";
		Query query = em.createQuery(sql, Cliente.class);
		return query.getResultList();
	}
	
	public Cliente getCliente(int idUsuario) {
		String sql = "SELECT c FROM Cliente c where c.usuario.codigo = :idUsuario";
		TypedQuery<Cliente> query = em.createQuery(sql, Cliente.class);
		query.setParameter("idUsuario", idUsuario);
		try {
			Cliente cliente = query.getSingleResult();
			return cliente;
		}catch (Exception e) {
			System.out.println("error getCliente "+this.getClass().getName());
			
			return null;
			// TODO: handle exception
		}
	}
	

	

}
