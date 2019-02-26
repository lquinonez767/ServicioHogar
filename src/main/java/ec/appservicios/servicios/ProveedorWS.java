package ec.appservicios.servicios;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.appservicios.negocio.CitaDAO;
import ec.appservicios.negocio.ClienteDAO;
import ec.appservicios.negocio.ProveedorClienteDAO;
import ec.appservicios.negocio.ProveedorDAO;
import ec.appservicios.negocio.UsuarioDAO;
import ec.appservicios.modelo.*;

@Stateless
@Path("/proveedores")
public class ProveedorWS {

	
	@Inject
	private ProveedorDAO provdao;
	@Inject
	private Logger vetLog;
	@Inject
	private ProveedorClienteDAO proveedorClienteDAO;
	@Inject
	private UsuarioDAO usuarioDAO;
	@Inject
	private ClienteDAO clienteDAO;
	@Inject
	private CitaDAO citaDAO;
	
	@GET
    @Path("/consultar")
    @Produces("application/json")
    public List<Proveedor> veterinarias(){
		
		return provdao.getProveedores();
		
	}
	
	@GET
    @Path("/ltsComentarios")
    @Produces("application/json")
    public List<Comentario> ltsComentariosByProducto(@QueryParam("idProveedor") int idProveedor){
		Proveedor pro = provdao.read(idProveedor);
		try {
			return pro.getListaComentarios();
		}catch (Exception e) {
			return null;
			// TODO: handle exception
		}
		
	}
	
	@GET
    @Path("/crearPeticion")
    @Produces("application/json")
	public Respuesta crearPeticion(@QueryParam("idProveedor") int idProveedor, @QueryParam("idUsuario") int idUsuario,
			@QueryParam("fecha") String fecha,@QueryParam("hora") String hora, @QueryParam("motivo") String motivo) {
		//veo si hay una relacion en proveedorcliente
		Respuesta r=new Respuesta();
		try {
			
			ProveedorCliente proveedorCliente = proveedorClienteDAO.findByProveedorCliente(idProveedor,idUsuario);
			if(proveedorCliente==null) {
				System.out.println("no existe la relacion");
				//creo la relacion
				Usuario usuario = usuarioDAO.read(idUsuario);
				Proveedor proveedor = provdao.read(idProveedor);
				Cliente cliente = clienteDAO.getCliente(idUsuario);
				proveedorCliente = new ProveedorCliente();
				proveedorCliente.setNombreCliente(usuario.getNombre());
				proveedorCliente.setNombreProve(proveedor.getNombre());
				proveedorCliente.setNewProveedor(proveedor);
				proveedorCliente.setCliente(cliente);
				if(usuario!=null && proveedor!=null && cliente!=null) {
					System.out.println("puedo insertar");
					proveedorCliente= proveedorClienteDAO.insert(proveedorCliente);
				}else {
					System.out.println("no puedo insertar");
				}
			}else {
				System.out.println("existe la relacion");
			}
			Cita cita = new Cita();
			cita.setNewProveCliente(proveedorCliente);
			cita.setFecha(fecha);
			cita.setHora(hora);
			cita.setMotivo(motivo);
			citaDAO.insert(cita);
			r.setCodigo(0);
			r.setMensaje("OK");
			return r;
		} catch (Exception e) {
			r.setCodigo(99);
			r.setMensaje("error");
			return r;
			// TODO: handle exception
		}
	}
}
