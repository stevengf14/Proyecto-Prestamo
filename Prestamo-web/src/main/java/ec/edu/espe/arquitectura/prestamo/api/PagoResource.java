/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.api;

import ec.edu.espe.arquitectura.prestamo.Entidades.Amortizacion;
import ec.edu.espe.arquitectura.prestamo.Entidades.Comision;
import ec.edu.espe.arquitectura.prestamo.Servicios.Bean_ComisionLocal;
import ec.edu.espe.arquitectura.prestamo.Servicios.Bean_PagoPrestamoLocal;
import java.math.BigDecimal;
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
 * @author Samsung-PC
 */
@Path("pago")
@RequestScoped
public class PagoResource {

    @Context
    private UriInfo context;

     @EJB
    Bean_PagoPrestamoLocal beanPago;
    /**
     * Creates a new instance of PagoResource
     */
    public PagoResource() {
    }

    @PUT
    @Path("/{pago}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putJson(Amortizacion content,@PathParam("pago") BigDecimal valor_pago) {
        Amortizacion com = new Amortizacion();
        com = beanPago.editarAmortizacion(content.getId(),valor_pago);
        //System.out.println("Los datos modificados son ["+com.getId()+","+com.getDescripcion()+","+com.getEstado()+"]");
        return Response.noContent().build();
    }
}
