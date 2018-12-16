/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Servicios;

import ec.edu.espe.arquitectura.prestamo.Entidades.Comision;
import ec.edu.espe.arquitectura.prestamo.Entidades.TipoProducto;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Alejandro Torres
 */
@Stateless
public class Bean_TipoProducto implements Bean_TipoProductoLocal {
    
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
    EntityManager em1 = factory.createEntityManager();
    
    public TipoProducto crearProducto(BigDecimal id, BigDecimal montoMaximo, BigDecimal montoMinimo, String descripcion, BigInteger plazoMax, BigInteger plazoMin) {
        TipoProducto tp = new TipoProducto(id);
        em1.joinTransaction();
        tp.setMontoMaximo(montoMaximo);
        tp.setMontoMinimo(montoMinimo);
        tp.setDescripcion(descripcion);
        tp.setPlazoMax(plazoMax);
        tp.setPlazoMin(plazoMin);
        em1.persist(tp);
        return tp;
    }
}
