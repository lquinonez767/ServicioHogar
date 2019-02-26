/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.appservicios.servicios;


import ec.appservicios.modelo.Cliente;
import ec.appservicios.modelo.Usuario;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import ec.appservicios.negocio.ClienteDAO;
import ec.appservicios.negocio.UsuarioDAO;



@Path("/login")

public class Login {

    Usuario Ousuario;

    @Inject
    UsuarioDAO daousuario;
    
    @Inject
    ClienteDAO clienteDAO;

    public Login() {
		Ousuario=new Usuario();
    }

    @GET
    @Path("/consultar")
    @Produces("application/json")
    public Usuario login(@QueryParam("usuario") String usuario, @QueryParam("password") String password) {

    	
    	
    	System.out.println("user "+usuario +" pass: "+password);
    	
       Ousuario =daousuario.buscarUsuario(usuario, password);


       
       
       
       
        return Ousuario;

    }

    

//	@POST
//	@Path("/guardar")
//	@Produces("application/json")
//	@Consumes("application/x-www-form-urlencoded")
//	//public Respuesta guardar(MultivaluedMap<String, String> param){
//	public boolean guardar(){	
//		Respuesta r=new Respuesta();
//		Usuario u=new Usuario();
//		System.out.println("ws");
////		
////		
////		System.out.println(param.toString());
////		
////		
////		
////		u.setNombre(param.getFirst("nombreu"));
////		u.setUsername(param.getFirst("username"));
////		u.setCorreo(param.getFirst("correo"));
////		u.setPassword(daousuario.MD5(param.getFirst("password")));
//		
//		//System.out.println(u.toString());
//		
//		
//		try {
//			
//			
//			u.setEstado("ACTIVO");
//			u.setUrlFotoPerfil("imagenes/usuario.png");
//		    //daousuario.insert(u);
//			r.setCodigo(0);
//			r.setMensaje("OK");
//			return true;
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			r.setCodigo(99);
//			
//			if(e.getMessage().equalsIgnoreCase("org.hibernate.exception.ConstraintViolationException: could not execute statement"))
//			{
//				r.setMensaje("Error ya Existe el Nombre de Usuario");
//				
//			}else
//				{
//				r.setMensaje(e.getMessage());
//				}
//				
//				return false;
//		}
//	}

    
    @GET
	@Path("/guardar")
	@Produces("application/json")
	public Respuesta guardar(@QueryParam("nombre") String nombre,@QueryParam("username") String username,
			@QueryParam("correo") String correo,@QueryParam("password") String password,
			@QueryParam("direccion") String direccion, @QueryParam("fechaN") String fechaN){
		Respuesta r=new Respuesta();
		Usuario u=new Usuario();
		System.out.println("password "+password);
		u.setNombre(nombre);
		u.setUsername(username);
		u.setCorreo(correo);
		u.setPassword(daousuario.MD5(password));
		
		System.out.println(u.toString());
		
		
		try {
			
			
			u.setEstado("ACTIVO");
			u.setUrlFotoPerfil("imagenes/usuario.png");
		    u=daousuario.insert(u);
		    
		    Cliente cliente = new Cliente();
		    cliente.setUsuario(u);
		    cliente.setEdad("0");
		    cliente.setEstado("activo");
		    cliente.setFechaNacimiento(fechaN);
		    cliente.setNombre(nombre);
		    cliente.setUrlFotoCliente("imagenes/usuario.png");
		    cliente.setDireccion(direccion);
		    clienteDAO.insert(cliente);
			r.setCodigo(0);
			r.setMensaje("OK");
			return r;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			r.setCodigo(99);
			
			if(e.getMessage().equalsIgnoreCase("org.hibernate.exception.ConstraintViolationException: could not execute statement"))
			{
				r.setMensaje("Error ya Existe el Nombre de Usuario");
				
			}else
				{
				r.setMensaje(e.getMessage());
				}
				
				return r;
		}
	}
    
    
}
