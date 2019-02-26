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
public class ProveedorDAO implements Serializable{
	
	@Inject
	private EntityManager em;
	
	private Proveedor prove;
	
	private Proveedor proveedor;
	
	public Proveedor getProve() {
		return prove;
	}

	public void setProve(Proveedor prove) {
		this.prove = prove;
	}
	
	public ProveedorDAO() {
		


	}
	
	public Proveedor read(int codigo){
		try {
			Proveedor pro = em.find(Proveedor.class, codigo);
			if(pro!=null) {
				pro.getListaComentarios().size();
			}
			return pro;
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}
	
	public void insert(Proveedor proveedor){
		em.persist(proveedor);
	}
	
	public void update(Proveedor proveedor){
		em.merge(proveedor);	
		}
	
	public void remove(int codigo){
		
		Proveedor proveedor = em.find(Proveedor.class, codigo);
		em.remove(proveedor);
	}
	
	public void guardar(Proveedor proveedor) {
		if (em.find(Proveedor.class, proveedor.getCodigo()) == null) {
			insert(proveedor);
		} else {
			update(proveedor);
		}
		
	}
	
	public List<Proveedor> getProveedores() {
		String sql = "SELECT p FROM Proveedor p";
		Query query = em.createQuery(sql, Proveedor.class);
		return query.getResultList();
	}
	
	public List<Proveedor> getProveedoresActivos() {
		String sql = "SELECT p FROM Proveedor p WHERE p.estado = 'ACTIVO'";
		Query query = em.createQuery(sql, Proveedor.class);
		return query.getResultList();
	}
	
	
	public void updateEstado(int codigo) {
		String sql = "UPDATE Proveedor SET estado = '" + "INACTIVO" + "' WHERE codigo = '" + codigo + "'";
		Query query = em.createQuery(sql);
		query.executeUpdate();
	}
	
	public List<Telefono> getTelefonos() {
		String sql = "SELECT t FROM Telefonos t";
		Query query = em.createQuery(sql, Telefono.class);
		return query.getResultList();
	}

	public List<Telefono> getProveedoresTelefonos(int codigo) {
		String sql = "SELECT p FROM Telefono p where proveedor='" + codigo + "'";
		Query query =em.createQuery(sql, Telefono.class);
		return query.getResultList();
	}
	
	public List<Comentario> getComentarios() {
		String sql = "SELECT c FROM Comentario c";
		Query query = em.createQuery(sql,Comentario.class);
		return query.getResultList();
	}
	
	public List<Comentario> getProveedoresComentarios(int codigo) {
		String sql = "SELECT c FROM Comentario c where proveedor='" + codigo + "'";
		Query query = em.createQuery(sql, Comentario.class);
		return query.getResultList();
	}
	
	public List<Valoracion> getProveedorValoracion(int codigo) {
		String sql = "SELECT v FROM Valoracion v where proveedor='" + codigo + "'";
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
	
	public List<ProveedorCliente> getClientesFiltrados(int codigo) {
		String sql = "SELECT vm FROM VeterinariaMascota vm where veterinaria='" + codigo + "'";
		Query query = em.createQuery(sql, ProveedorCliente.class);
		return query.getResultList();
	}

	
public Proveedor buscarProveedor(String nombre, String password){
		
		
		try {
		     
			
			System.out.println("Entro");
			
	        TypedQuery<Proveedor> consultaGenProveedores = em.createNamedQuery("Proveedor.BuscarProveedor", Proveedor.class);
	        consultaGenProveedores.setParameter("codProveedor", nombre);
	        consultaGenProveedores.setParameter("codPassword", MD5(password));

	        System.out.println(consultaGenProveedores.toString());
	        
	        proveedor= consultaGenProveedores.getSingleResult();

	         System.out.println(proveedor.getNombre());
	           
	            
	            
	        } catch (Exception e) {
	          
	        	
	            return null;
	        }

	        return proveedor;

	
		
	
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
