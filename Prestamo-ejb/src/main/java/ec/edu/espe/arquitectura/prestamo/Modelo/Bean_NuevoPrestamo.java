/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Modelo;

import ec.edu.espe.arquitectura.prestamo.Entidades.Cliente;
import ec.edu.espe.arquitectura.prestamo.Entidades.Usuario;
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
public class Bean_NuevoPrestamo implements Bean_NuevoPrestamoLocal {

    public Cliente verificarCliente(String cedula) {
        boolean val = false;
        Cliente cli = new Cliente();
        List<String> cedulaList = new ArrayList<String>();
        List<String> nombre = new ArrayList<String>();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
        EntityManager em1 = factory.createEntityManager();
        try {
            Query q = em1.createNativeQuery("SELECT CEDULA FROM CLIENTE WHERE CEDULA='" + cedula + "'");
            Query q2 = em1.createNativeQuery("SELECT NOMBRE FROM CLIENTE WHERE CEDULA='" + cedula + "'");
            cedulaList = q.getResultList();
            nombre = q2.getResultList();
            if (cedula.equals(cedulaList.get(0))) {
                cli.setCedula(cedulaList.get(0));
                cli.setNombre(nombre.get(0));
            } else {
                cli = null;
            }
        } catch (Exception ex) {
            cli = null;
        }
        em1.close();
        factory.close();
        return cli;
    }

    public boolean verificarTipoPrestamoCliente(String cedula, String TipoPrestamo) {
        int id = EncontrarIdPrestamo(TipoPrestamo);
        boolean val = false;
        List<String> tipo_cliente = new ArrayList<String>();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
        EntityManager em1 = factory.createEntityManager();
        try {
            Query q2 = em1.createNativeQuery("SELECT TIPO FROM CLIENTE WHERE CEDULA='" + cedula + "'");
            tipo_cliente = q2.getResultList();

            if (tipo_cliente.get(0).equals("Juridico") && id != 4) {
                val = false;
            } else if (tipo_cliente.get(0).equals("Natural") && id == 4) {
                val = false;
            } else {
                val = true;
            }

        } catch (Exception ex) {
            val = false;
        }
        em1.close();
        factory.close();
        return val;
    }

    public int EncontrarIdPrestamo(String TipoPrestamo) {
        int id = 0;
        if (TipoPrestamo.equals("Quirografario")) {
            id = 1;
        } else if (TipoPrestamo.equals("Hipotecario")) {
            id = 2;
        } else if (TipoPrestamo.equals("Vehicular")) {
            id = 3;
        } else if (TipoPrestamo.equals("Comercial")) {
            id = 4;
        }
        return id;
    }

    public boolean validarMonto(String TipoPrestamo, double monto) {
        int id = EncontrarIdPrestamo(TipoPrestamo);
        boolean val = false;
        List<BigDecimal> montoMin = new ArrayList<BigDecimal>();
        List<BigDecimal> montoMax = new ArrayList<BigDecimal>();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
        EntityManager em1 = factory.createEntityManager();
        try {
            Query q = em1.createNativeQuery("SELECT MONTO_MINIMO FROM TIPO_PRODUCTO WHERE ID='" + id + "'");
            Query q2 = em1.createNativeQuery("SELECT MONTO_MAXIMO FROM TIPO_PRODUCTO WHERE ID='" + id + "'");
            montoMin = q.getResultList();
            montoMax = q2.getResultList();
            
            if (monto >= montoMin.get(0).doubleValue() && monto <= montoMax.get(0).doubleValue()) {
                val = true;
            } else {
                val = false;
            }
        } catch (Exception ex) {
            val = true;
        }
        em1.close();
        factory.close();
        return val;
    }

    public String mensajeMonto(String TipoPrestamo) {
        int id = EncontrarIdPrestamo(TipoPrestamo);
        String mensaje = "";
        List<Double> montoMin = new ArrayList<Double>();
        List<Double> montoMax = new ArrayList<Double>();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
        EntityManager em1 = factory.createEntityManager();
        try {
            Query q = em1.createNativeQuery("SELECT MONTO_MINIMO FROM TIPO_PRODUCTO WHERE ID='" + id + "'");
            Query q2 = em1.createNativeQuery("SELECT MONTO_MAXIMO FROM TIPO_PRODUCTO WHERE ID='" + id + "'");
            montoMin = q.getResultList();
            montoMax = q2.getResultList();
            mensaje = "El monto debe ser entre " + montoMin.get(0) + " y " + montoMax.get(0);
        } catch (Exception ex) {
            mensaje = "";
        }
        em1.close();
        factory.close();
        return mensaje;
    }
    
    public boolean validarPlazo(String TipoPrestamo, int plazo)
    {
        int id = EncontrarIdPrestamo(TipoPrestamo);
        boolean val = false;
        List<BigDecimal> plazoMin = new ArrayList<BigDecimal>();
        List<BigDecimal> plazoMax = new ArrayList<BigDecimal>();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
        EntityManager em1 = factory.createEntityManager();
        try {
            Query q = em1.createNativeQuery("SELECT PLAZO_MIN FROM TIPO_PRODUCTO WHERE ID='" + id + "'");
            Query q2 = em1.createNativeQuery("SELECT PLAZO_MAX FROM TIPO_PRODUCTO WHERE ID='" + id + "'");
            plazoMin = q.getResultList();
            plazoMax = q2.getResultList();
            
            if (plazo >= plazoMin.get(0).doubleValue() && plazo <= plazoMax.get(0).doubleValue()) {
                val = true;
            } else {
                val = false;
            }
        } catch (Exception ex) {
            val = true;
        }
        em1.close();
        factory.close();
        return val;
    }
    public String mensajePlazo(String TipoPrestamo) {
        int id = EncontrarIdPrestamo(TipoPrestamo);
        String mensaje = "";
        List<BigDecimal> plazoMin = new ArrayList<BigDecimal>();
        List<BigDecimal> plazoMax = new ArrayList<BigDecimal>();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
        EntityManager em1 = factory.createEntityManager();
        try {
            Query q = em1.createNativeQuery("SELECT PLAZO_MIN FROM TIPO_PRODUCTO WHERE ID='" + id + "'");
            Query q2 = em1.createNativeQuery("SELECT PLAZO_MAX FROM TIPO_PRODUCTO WHERE ID='" + id + "'");
            plazoMin = q.getResultList();
            plazoMax = q2.getResultList();
            mensaje = "El monto debe ser entre " + plazoMin.get(0) + " y " + plazoMax.get(0);
        } catch (Exception ex) {
            mensaje = "";
        }
        em1.close();
        factory.close();
        return mensaje;
    }
}
