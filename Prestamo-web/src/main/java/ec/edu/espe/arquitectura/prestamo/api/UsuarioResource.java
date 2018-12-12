/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.api;

import ec.edu.espe.arquitectura.prestamo.Entidades.Usuario;

import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ec.edu.espe.arquitectura.prestamo.Servicios.Bean_UsuLocal;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;

/**
 * REST Web Service
 *
 * @author Samsung-PC
 */
@Path("usuario")
@RequestScoped
public class UsuarioResource {

    @Context
    private UriInfo context;
    
    @EJB
    Bean_UsuLocal beanUsu;

    /**
     * Creates a new instance of UsuarioResource
     */
    public UsuarioResource() {
    }

    /**
     * Retrieves representation of an instance of ec.edu.espe.arquitectura.prestamo.api.UsuarioResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
     @Path("/{id}")
    public Response getJson(@PathParam("id") String id) {
        Usuario usu;
        usu=beanUsu.encontrarUsuario(id);
        System.out.println("Los datos son ["+usu.getId()+","+usu.getNombre()+","+usu.getClave()+","+usu.getCorreo()+"]");
        return Response.ok(usu).build();
    }

    /**
     * PUT method for updating or creating an instance of UsuarioResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putJson(Usuario content)  {
        Usuario usu = new Usuario();
        usu = beanUsu.editarUsuario(content.getId(), content.getNombre(), content.getClave(),content.getCorreo());
        System.out.println("Los datos modificados son ["+usu.getId()+","+usu.getNombre()+","+usu.getClave()+","+usu.getCorreo()+"]");
        return Response.noContent().build();
    }
    
      @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postJson(Usuario content) {
        Usuario usu = new Usuario();
        usu = beanUsu.crearUsuario(content.getId(), content.getNombre(), content.getClave(),content.getCorreo());
        System.out.println("Los datos Ingresados son ["+usu.getId()+","+usu.getNombre()+","+usu.getClave()+","+usu.getCorreo()+"]");
        return Response.noContent().build();
    }
    
      @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteJson(@PathParam("id") String id) {
        beanUsu.eliminarUsuario(id);
        System.out.println("Registro eliminado");
        return Response.noContent().build();
    }
}
