/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Servicios;

import ec.edu.espe.arquitectura.prestamo.Entidades.Amortizacion;
import ec.edu.espe.arquitectura.prestamo.Entidades.Comision;
import ec.edu.espe.arquitectura.prestamo.Entidades.Prestamo;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author Samsung-PC
 */
@Stateless
public class Bean_PagoPrestamo implements Bean_PagoPrestamoLocal {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
   
    EntityManager em1 = factory.createEntityManager();
    
    public Amortizacion editarAmortizacion(BigDecimal id_amortiza, BigDecimal valor_pago) {
        Amortizacion com = new Amortizacion();
        double saldo=0;
        com = em1.find(Amortizacion.class, id_amortiza);
        saldo=(com.getValorCuota().doubleValue()-valor_pago.doubleValue());
        if(saldo>0){
            com.setEstado("Pendiente");
            com.setSaldo(BigDecimal.valueOf(saldo));
        }else{
            com.setEstado("Pagado");
            com.setSaldo(BigDecimal.valueOf(saldo));
        }
        
        em1.persist(com);
        return com;
    }
}
