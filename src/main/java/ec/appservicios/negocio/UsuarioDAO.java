package ec.appservicios.negocio;

import java.util.List;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ec.appservicios.modelo.*;



@Stateless
public class UsuarioDAO {
	
	private Usuario usuario;
	
	@Inject
	private EntityManager em;
	
	@Inject
	private Logger userLOG;
	
	private Usuario user;
	
	
	
	public UsuarioDAO(){
		
	}
	
	public Usuario read(int codigo){
		try {
			return em.find(Usuario.class, codigo);
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}
	
	public Usuario insert(Usuario Usuario){
		em.persist(Usuario);
		em.flush();
		return Usuario;
	}
	
	public void update(Usuario Usuario){
		em.merge(Usuario);	
		}
	
	public void remove(int codigo){
		Usuario usuario = em.find(Usuario.class, codigo);
		em.remove(usuario);
	}
	
	public void guardar (Usuario usuario){
		if(em.find(Usuario.class, usuario.getCodigo())==null){
			insert(usuario);
		} else {
			update(usuario);
		}
	}
	
	
	
	public List<Telefono> getTelefonos(){
		String sql="SELECT t FROM Telefono t";
		Query query=em.createQuery(sql,Telefono.class);
		return query.getResultList();
	}
	
	public List<Usuario> getUsuarios(){
		String sql="SELECT u FROM Usuario u";
		Query query=em.createQuery(sql,Usuario.class);
		return query.getResultList();
	}
	
	public List<Telefono> getUsuariosTelefonos(int codigo){
		String sql="SELECT u FROM Telefono u where usuario='"+codigo+"'";
		Query query=em.createQuery(sql,Telefono.class);
		return query.getResultList();
	}
	
	public void eliminarTelefono(int codigo) {
		System.out.println("Eliminando Telefono...");
		Telefono telefono = em.find(Telefono.class, codigo);
		em.remove(telefono);
	}
	
	public List<Comentario> getComentarios() {
		String sql = "SELECT c FROM Comentario c";
		Query query = em.createQuery(sql,Comentario.class);
		return query.getResultList();
	}
	
	public List<Comentario> getUsuarioComentarios(int codigo) {
		String sql = "SELECT c FROM Comentario c where usuario='" + codigo + "'";
		Query query = em.createQuery(sql, Comentario.class);
		return query.getResultList();
	}
	
	public List<Valoracion> getUsuarioValoracion(int codigo) {
		String sql = "SELECT v FROM Valoracion v where usuario='" + codigo + "'";
		Query query = em.createQuery(sql, Valoracion.class);
		return query.getResultList();
	}
	
	public List<Valoracion> getValoraciones() {
		String sql = "SELECT v FROM Valoracion v";
		Query query = em.createQuery(sql,Valoracion.class);
		return query.getResultList();
	}
	
	public List<Usuario> getUsuariosActivos() {
		String sql = "SELECT u FROM Usuario u WHERE u.estado = 'ACTIVO'";
		Query query = em.createQuery(sql, Usuario.class);
		return query.getResultList();
	}
	
	public void updateEstado(int codigo) {
		String sql = "UPDATE Usuario SET estado = '" + "INACTIVO" + "' WHERE codigo = '" + codigo + "'";
		Query query = em.createQuery(sql);
		query.executeUpdate();
	}

		
	public List<Cliente> getFiltrarClientesUsuario(int codigo) {
		String sql = "SELECT m FROM Cliente m where propietario='"+codigo+"'";
		Query query = em.createQuery(sql, Cliente.class);
		return query.getResultList();
	}
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
	
	public Usuario buscarUsuario(String nombre, String password){
		
		
		try {
		     
			
			System.out.println("Entro");
			
	        TypedQuery<Usuario> consultaGenUsuarios = em.createNamedQuery("Usuario.BuscarUsuario", Usuario.class);
	        consultaGenUsuarios.setParameter("codUsuario", nombre);
	        consultaGenUsuarios.setParameter("codPassword", MD5(password));

	        System.out.println(consultaGenUsuarios.toString());
	        
	        usuario= consultaGenUsuarios.getSingleResult();

	         System.out.println(usuario.getNombre());
	           
	            
	            
	        } catch (Exception e) {
	          e.printStackTrace();
	        	
	            return null;
	        }

	        return usuario;

	
		
	
	}
	
	
	public String MD5(String md5) {
		   try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
		}


}



