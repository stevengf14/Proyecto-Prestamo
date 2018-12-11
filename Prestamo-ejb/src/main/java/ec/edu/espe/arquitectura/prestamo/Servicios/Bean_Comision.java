/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Servicios;

import ec.edu.espe.arquitectura.prestamo.Entidades.Comision;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Steven
 */
@Stateless
public class Bean_Comision implements Bean_ComisionLocal {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
    EntityManager em1 = factory.createEntityManager();

    public Comision encontrarComision(int id) {
        Comision com = new Comision();
        em1.joinTransaction();
        com = em1.find(Comision.class, BigDecimal.valueOf(id));
        return com;
    }

    public Comision editarComision(BigDecimal id, String descripcion, String estado) {
        Comision com = new Comision();
        em1.joinTransaction();
        com = em1.find(Comision.class, id);
        com.setDescripcion(descripcion);
        com.setEstado(estado);
        em1.persist(com);
        return com;
    }
    
    public Comision crearComision(BigDecimal id, String descripcion, String estado) {
        Comision com = new Comision(id);
        em1.joinTransaction();
        com.setDescripcion(descripcion);
        com.setEstado(estado);
        em1.persist(com);
        return com;
    }
    
    public void eliminarComision(int id) {
        Comision com = new Comision();
        em1.joinTransaction();
        com = em1.find(Comision.class, BigDecimal.valueOf(id));
        em1.remove(com);
    }
}
