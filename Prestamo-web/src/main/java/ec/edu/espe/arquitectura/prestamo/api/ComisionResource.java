/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.api;

import ec.edu.espe.arquitectura.prestamo.Entidades.Comision;
import ec.edu.espe.arquitectura.prestamo.Servicios.Bean_ComisionLocal;
import ec.edu.espe.arquitectura.prestamo.rest.msg.FindComision;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Steven
 */
@Path("comision")
@RequestScoped
public class ComisionResource {

    @EJB
    Bean_ComisionLocal beanComision;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ComisionResource
     */
    public ComisionResource() {
    }

    /**
     * Retrieves representation of an instance of
     * ec.edu.espe.arquitectura.prestamo.api.ComisionResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getJson(@PathParam("id") String id) {
        Comision com;
        com=beanComision.encontrarComision(Integer.valueOf(id));
        System.out.println("Los datos son ["+com.getId()+","+com.getDescripcion()+","+com.getEstado()+"]");
        return Response.ok(com).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putJson(Comision content) {
        Comision com = new Comision();
        com = beanComision.editarComision(content.getId(), content.getDescripcion(), content.getEstado());
        System.out.println("Los datos modificados son ["+com.getId()+","+com.getDescripcion()+","+com.getEstado()+"]");
        return Response.noContent().build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postJson(Comision content) {
        Comision com = new Comision();
        com = beanComision.crearComision(content.getId(), content.getDescripcion(), content.getEstado());
        System.out.println("Los datos Ingresados son ["+com.getId()+","+com.getDescripcion()+","+com.getEstado()+"]");
        return Response.noContent().build();
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteJson(@PathParam("id") String id) {
        beanComision.eliminarComision(Integer.valueOf(id));
        System.out.println("Registro eliminado");
        return Response.noContent().build();
    }
}
