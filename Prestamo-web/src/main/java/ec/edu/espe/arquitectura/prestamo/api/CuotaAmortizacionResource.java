/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.api;

import ec.edu.espe.arquitectura.prestamo.Entidades.Amortizacion;
import ec.edu.espe.arquitectura.prestamo.Servicios.Bean_CuotaAmortizacionLocal;
import ec.edu.espe.arquitectura.prestamo.rest.msg.Cuota;
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

/**
 * REST Web Service
 *
 * @author solan
 */
@Path("cuotaAmortizacion")
@RequestScoped
public class CuotaAmortizacionResource {

    @EJB
    Bean_CuotaAmortizacionLocal bean_CuotaAmortizacionLocal; 
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CuotaResource
     */
    public CuotaAmortizacionResource() {
    }

    /**
     * Retrieves representation of an instance of ec.edu.espe.arquitectura.prestamo.api.CuotaResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getJson(@PathParam("id") String id) {
        Amortizacion amo;
        amo =bean_CuotaAmortizacionLocal.encontrarCuota(Integer.valueOf(id));
        //pre.setId(BigDecimal.ONE);
        Cuota cuota = new Cuota();
        cuota.setId(amo.getId());
        cuota.setValorCuota(amo.getValorCuota());
        cuota.setFechaAmortizacion(amo.getFechaAmortizacion());
        System.out.println("Los datos son ["+amo.getId()+","+amo.getValorCuota()+","+amo.getFechaAmortizacion()+"]");
        return Response.ok(cuota).build();
    }

    /**
     * PUT method for updating or creating an instance of CuotaAmortizacionResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
