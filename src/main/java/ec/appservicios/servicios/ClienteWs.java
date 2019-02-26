package ec.appservicios.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.appservicios.negocio.ClienteDAO;
import ec.appservicios.negocio.NuevoProductoServicioDAO;
import ec.appservicios.negocio.UsuarioDAO;
import ec.appservicios.modelo.*;

@Stateless
@Path("/clientes")

public class ClienteWs {

	
	
	@Inject
    UsuarioDAO daousuario;
	@Inject
	NuevoProductoServicioDAO daoproducto;
	
	NuevoProductoServicio produc;

	@GET
    @Path("/consultar")
    @Produces("application/json")
    public List<Cliente> clientes(@QueryParam("id") int idusuario){
		
		
		System.out.println("entro "+ idusuario);
		
		
		return daousuario.getFiltrarClientesUsuario(idusuario);
		
		
		
		
	}
	

	@GET
    @Path("/consultar_produc")
    @Produces("application/json")
    public List<NuevoProductoServicio> productos(){
		
		
		System.out.println("entro WS");
		
		
		return daoproducto.getProductos();
		
		
		
		
	}
}
