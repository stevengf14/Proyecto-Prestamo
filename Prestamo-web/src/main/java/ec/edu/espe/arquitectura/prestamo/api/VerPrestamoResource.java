/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.api;

import ec.edu.espe.arquitectura.prestamo.Entidades.Prestamo;
import ec.edu.espe.arquitectura.prestamo.Servicios.Bean_VerPrestamo;
import ec.edu.espe.arquitectura.prestamo.Servicios.Bean_VerPrestamoLocal;
import ec.edu.espe.arquitectura.prestamo.rest.msg.DatosPrestamos;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author solan
 */
@Path("verPrestamo")
@RequestScoped
public class VerPrestamoResource {

    @EJB
    Bean_VerPrestamoLocal bean_VerPrestamo;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of VerPrestamoResource
     */
    public VerPrestamoResource() {
    }

    /**
     * Retrieves representation of an instance of ec.edu.espe.arquitectura.prestamo.api.VerPrestamoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{cliId}")
    public Response getJson(@PathParam("cliId") String cliId) {
        Prestamo pre;
        pre =bean_VerPrestamo.encontrarPrestamo(Integer.valueOf(cliId));
        //pre.setId(BigDecimal.ONE);
        DatosPrestamos datos = new DatosPrestamos();
        datos.setId(pre.getId());
        datos.setMonto(pre.getMonto());
        datos.setEstado(pre.getEstado());
        datos.setTipo(pre.getProId().getProDescripcion());
        datos.setNumero(pre.getId());
        datos.setSaldo(pre.getSaldo());
        datos.setFecha(pre.getFechaDesembolso());
        System.out.println("Los datos son ["+pre.getCliId()+","+pre.getFechaConsecion()+","+pre.getEstado()+"]");
        return Response.ok(datos).build();
    }
    
    /**
     * PUT method for updating or creating an instance of VerPrestamoResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
