/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Servicios;

import ec.edu.espe.arquitectura.prestamo.Entidades.Amortizacion;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author solan
 */
@Stateless
public class Bean_CuotaAmortizacion implements Bean_CuotaAmortizacionLocal {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
    EntityManager em1 = factory.createEntityManager();

    public Amortizacion encontrarCuota(int id) {
        Amortizacion pre = new Amortizacion();
        em1.joinTransaction();
        pre = em1.find(Amortizacion.class, BigDecimal.valueOf(id));
        return pre;
    }

}
