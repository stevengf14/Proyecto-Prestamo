/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Modelo;

import ec.edu.espe.arquitectura.prestamo.Entidades.Accrual;
import ec.edu.espe.arquitectura.prestamo.Entidades.Cliente;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
public class Bean_Accrual implements Bean_AccrualLocal {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
    EntityManager em1 = factory.createEntityManager();

    public Cliente verificarCliente(String cedula) {
        Cliente cli = new Cliente();
        try {
            List<BigDecimal> cedulaList = new ArrayList<BigDecimal>();
            Query q = em1.createNativeQuery("SELECT ID FROM CLIENTE WHERE CEDULA='" + cedula + "'");
            cedulaList = q.getResultList();
            int id = cedulaList.get(0).intValue();
            em1.joinTransaction();
            cli = em1.find(Cliente.class, BigDecimal.valueOf(id));
        } catch (Exception e) {
            cli = null;
        }
        return cli;
    }

    public int encontrarPrestamo(int cli) {
        int id = 0;
        List<BigDecimal> idList = new ArrayList<BigDecimal>();

        Query q = em1.createNativeQuery("SELECT ID FROM PRESTAMO WHERE CLI_ID='" + cli + "' AND ESTADO='act' ");
        idList = q.getResultList();
        id = idList.get(0).intValue();

        return id;
    }

    public List<Accrual> cargarTablaAccrual(int cli_id) {
        List<Accrual> lista = new ArrayList<Accrual>();
        List<BigDecimal> idList = new ArrayList<BigDecimal>();
        Query q = em1.createNativeQuery("SELECT AMO_ID FROM ACCRUAL WHERE PRE_ID=" + encontrarPrestamo(cli_id));
        idList = q.getResultList();
        for (int i = 0; i < idList.size(); i++) {
            lista.add(encontrarAccrual(idList.get(i).intValue()));
        }
        return lista;
    }

    public Accrual encontrarAccrual(int id) {
        Accrual com = new Accrual();
        em1.joinTransaction();
        com = em1.find(Accrual.class, BigDecimal.valueOf(id));

        System.out.println("aquiiiiiiiIIIIIIiiiii");
        return com;
    }

    public double verificarCuota(int cli) {
        double cuota = 0;
        List<BigDecimal> idList = new ArrayList<BigDecimal>();

        Query q = em1.createNativeQuery("SELECT VALOR_CUOTA FROM AMORTIZACION WHERE PRE_ID=" + encontrarPrestamo(cli));
        idList = q.getResultList();
        cuota = idList.get(0).doubleValue();

        return cuota;
    }

    public Date retornarFecha(int amo_id) {
        Date fec = new Date();
        List<Timestamp> idList = new ArrayList<Timestamp>();
        Query q = em1.createNativeQuery("SELECT FECHA_AMORTIZACION FROM AMORTIZACION WHERE ID=" + amo_id);
        idList = q.getResultList();
        fec = new Date(idList.get(0).getTime());

        return fec;
    }
}
