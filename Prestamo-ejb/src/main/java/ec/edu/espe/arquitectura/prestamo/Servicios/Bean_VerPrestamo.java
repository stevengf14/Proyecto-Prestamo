/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Servicios;

import ec.edu.espe.arquitectura.prestamo.Entidades.Prestamo;
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
public class Bean_VerPrestamo implements Bean_VerPrestamoLocal {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
    EntityManager em1 = factory.createEntityManager();

    public Prestamo encontrarPrestamo(int cliId) {
        Prestamo pre = new Prestamo();
        em1.joinTransaction();
        pre = em1.find(Prestamo.class, BigDecimal.valueOf(cliId));
        return pre;
    }
}
