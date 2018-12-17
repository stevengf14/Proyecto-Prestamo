/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.api;

import ec.edu.espe.arquitectura.prestamo.Entidades.TipoProducto;
import ec.edu.espe.arquitectura.prestamo.Servicios.Bean_TipoProductoLocal;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Alejandro Torres
 */
@Path("TipoProducto")
@RequestScoped
public class TipoProductoResource {
    
    @EJB
    Bean_TipoProductoLocal beanTP;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TipoProductoResource
     */
    public TipoProductoResource() {
    }

    /**
     * Retrieves representation of an instance of ec.edu.espe.arquitectura.prestamo.Controlador.TipoProductoResource
     * @return an instance of java.lang.String
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postJson(TipoProducto content) {
        TipoProducto tp = new TipoProducto();
        tp = beanTP.crearProducto(content.getId(), content.getMontoMaximo(), content.getMontoMinimo(), content.getDescripcion(), content.getPlazoMax(), content.getPlazoMin());
        System.out.println("Los datos Ingresados son ["+tp.getId()+","+tp.getMontoMaximo()+tp.getMontoMinimo()+","+tp.getDescripcion()+","+tp.getPlazoMax()+tp.getPlazoMin()+"]");
        return Response.noContent().build();
    }
}
