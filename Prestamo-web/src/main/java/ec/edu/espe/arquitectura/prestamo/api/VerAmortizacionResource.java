/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.api;

import ec.edu.espe.arquitectura.prestamo.Entidades.Amortizacion;
import ec.edu.espe.arquitectura.prestamo.Servicios.Bean_AmortizacionPrestamoLocal;
import ec.edu.espe.arquitectura.prestamo.rest.msg.DatosAmortizacion;
import java.util.Set;
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
 * @author Alejandro Torres
 */
@Path("VerAmortizacion")
@RequestScoped
public class VerAmortizacionResource {
    
    @EJB
    Bean_AmortizacionPrestamoLocal bean_VerAmortizacion;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of VerAmortizacionResource
     */
    public VerAmortizacionResource() {
    }

    /**
     * Retrieves representation of an instance of ec.edu.espe.arquitectura.prestamo.api.VerAmortizacionResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{amoId}")
    public Response getJson(@PathParam("amoId") String amoId) {
        Amortizacion amo;
        amo = bean_VerAmortizacion.encontrarAmortizacion(Integer.valueOf(amoId));
        //pre =bean_VerPrestamo.encontrarPrestamo(Integer.valueOf(amoId));
        //pre.setId(BigDecimal.ONE);
        DatosAmortizacion dA = new DatosAmortizacion();
        dA.setId(amo.getId());
        dA.setPreId(amo.getPreId());
        dA.setCapital(amo.getCapital());
        dA.setInteres(amo.getInteres());
        dA.setValorCuota(amo.getValorCuota());
        dA.setFechaAmortizacion(amo.getFechaAmortizacion());
        dA.setEstado(amo.getEstado());
        dA.setNumero(amo.getNumero());
        dA.setSaldo(amo.getSaldo());
        System.out.println("Los datos son ["+dA.getId()+","+dA.getCapital()+","+dA.getInteres()+"]");
        return Response.ok(dA).build();
        /*DatosPrestamos datos = new DatosPrestamos();
        datos.setId(pre.getId());
        datos.setMonto(pre.getMonto());
        datos.setEstado(pre.getEstado());
        datos.setTipo(pre.getProId().getProDescripcion());
        datos.setNumero(pre.getId());
        datos.setSaldo(pre.getSaldo());
        datos.setFecha(pre.getFechaDesembolso());
        System.out.println("Los datos son ["+pre.getCliId()+","+pre.getFechaConsecion()+","+pre.getEstado()+"]");
        return Response.ok(datos).build();*/
    }
}
