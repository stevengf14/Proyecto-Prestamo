/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Servicios;

import ec.edu.espe.arquitectura.prestamo.Entidades.Amortizacion;
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
public class Bean_AmortizacionPrestamo implements Bean_AmortizacionPrestamoLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
    EntityManager em1 = factory.createEntityManager();
    
    public Amortizacion encontrarAmortizacion(int amoId) {
        Amortizacion amort = new Amortizacion();
        em1.joinTransaction();
        amort = em1.find(Amortizacion.class, BigDecimal.valueOf(amoId));
        //pre = em1.find(Prestamo.class, BigDecimal.valueOf(cliId));
        //return pre;
        return amort;
    }
}
