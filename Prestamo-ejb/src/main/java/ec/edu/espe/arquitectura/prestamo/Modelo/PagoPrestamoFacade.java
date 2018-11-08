/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Modelo;


import ec.edu.espe.arquitectura.prestamo.Entidades.Prestamo;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.persistence.Query;

/**
 *
 * @author Samsung-PC
 */
@Stateless
public class PagoPrestamoFacade  implements PagoPrestamoFacadeLocal {

 @Override
 public Prestamo busquedaPrestamo(String cedula) {
        
        Prestamo pre = new Prestamo();
        List<BigInteger> plazoList = new ArrayList<BigInteger>();
        List<BigDecimal> plazoList1 = new ArrayList<BigDecimal>();
        List<BigDecimal> monto = new ArrayList<BigDecimal>();
         List<String> cedulaList = new ArrayList<String>();
       
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
        EntityManager em1 = factory.createEntityManager();
        try {
             Query q3 = em1.createNativeQuery("SELECT CEDULA FROM CLIENTE WHERE CEDULA='" + cedula + "'");
            Query q = em1.createNativeQuery("SELECT plazo FROM PRESTAMO  INNER JOIN CLIENTE ON PRESTAMO.CLI_ID = CLIENTE.ID WHERE CLIENTE.CEDULA ='" + cedula + "'");
            Query q1 = em1.createNativeQuery("SELECT PRESTAMO.ID FROM PRESTAMO  INNER JOIN CLIENTE ON PRESTAMO.CLI_ID = CLIENTE.ID WHERE CLIENTE.CEDULA ='" + cedula + "'");
            Query q2 = em1.createNativeQuery("SELECT monto FROM PRESTAMO  INNER JOIN CLIENTE ON PRESTAMO.CLI_ID = CLIENTE.ID WHERE CLIENTE.CEDULA ='" + cedula + "'");
            plazoList = q.getResultList();
            cedulaList=q3.getResultList();
            plazoList1 = q1.getResultList();
            monto = q2.getResultList();
            System.out.println("h------"+plazoList.get(0));
         
           if (cedula.equals(cedulaList.get(0))) {
                
                pre.setPlazo(plazoList.get(0));
                pre.setId(plazoList1.get(0));
                pre.setMonto(monto.get(0));
            } else {
                pre = null;
            }
        } catch (Exception ex) {
            pre = null;
        }
        em1.close();
        factory.close();
        return pre;
    }
 
 public double busquedaMonto(String cedula) {
        
        
        List<BigDecimal> monto = new ArrayList<BigDecimal>();
         List<String> cedulaList = new ArrayList<String>();
       double monto1;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
        EntityManager em1 = factory.createEntityManager();
        try {
             Query q3 = em1.createNativeQuery("SELECT CEDULA FROM CLIENTE WHERE CEDULA='" + cedula + "'");
            Query q2 = em1.createNativeQuery("SELECT monto FROM PRESTAMO  INNER JOIN CLIENTE ON PRESTAMO.CLI_ID = CLIENTE.ID WHERE CLIENTE.CEDULA ='" + cedula + "'");
            cedulaList=q3.getResultList();
            monto = q2.getResultList();
           
           if (cedula.equals(cedulaList.get(0))) {
            BigDecimal bd=monto.get(0);
             monto1 = bd.doubleValue();
               
            } else {
                monto1 = 0;
            }
        } catch (Exception ex) {
            monto1 = 0;
        }
        em1.close();
        factory.close();
        return monto1;
    }
 
 @Override
 public int busquedaPlazo(String cedula) {
                
        List<BigDecimal> plazo = new ArrayList<BigDecimal>();
        int plazo1;
         List<String> cedulaList = new ArrayList<String>();
       double monto1;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
        EntityManager em1 = factory.createEntityManager();
        try {
             Query q3 = em1.createNativeQuery("SELECT CEDULA FROM CLIENTE WHERE CEDULA='" + cedula + "'");
            Query q2 = em1.createNativeQuery("SELECT PLAZO FROM PRESTAMO  INNER JOIN CLIENTE ON PRESTAMO.CLI_ID = CLIENTE.ID WHERE CLIENTE.CEDULA ='" + cedula + "'");
            cedulaList=q3.getResultList();
            plazo = q2.getResultList();
           
           if (cedula.equals(cedulaList.get(0))) {
                 
               BigDecimal bd=plazo.get(0);
               plazo1=bd.intValue();
               
               
            } else {
                plazo1 = 0;
                
            }
        } catch (Exception ex) {
            plazo1 = 0;
            System.out.println(ex);
        }
        em1.close();
        factory.close();
        return plazo1;
    }
  
     public String busquedaNombre(String cedula) {
                
        List<String> nombre = new ArrayList<String>();
        String nombre1;
         List<String> cedulaList = new ArrayList<String>();
       
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
        EntityManager em1 = factory.createEntityManager();
        try {
             Query q3 = em1.createNativeQuery("SELECT CEDULA FROM CLIENTE WHERE CEDULA='" + cedula + "'");
            Query q2 = em1.createNativeQuery("SELECT NOMBRE FROM CLIENTE WHERE CEDULA='" + cedula + "'");
            cedulaList=q3.getResultList();
            nombre = q2.getResultList();
           
           if (cedula.equals(cedulaList.get(0))) {
                 
               nombre1=nombre.get(0);
             
               
               
            } else {
                nombre1 = "";
                
            }
        } catch (Exception ex) {
            nombre1 = "";
            System.out.println(ex);
        }
        em1.close();
        factory.close();
        return nombre1;
    }
     
       public Date busquedaFecha(String cedula) {
                
        List<Date> date = new ArrayList<Date>();
        String nombre1;
         List<String> cedulaList = new ArrayList<String>();
       Date date1= new Date();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
        EntityManager em1 = factory.createEntityManager();
        try {
             Query q3 = em1.createNativeQuery("SELECT CEDULA FROM CLIENTE WHERE CEDULA='" + cedula + "'");
            Query q2 = em1.createNativeQuery("SELECT FECHA_CREACION FROM PRESTAMO  INNER JOIN CLIENTE ON PRESTAMO.CLI_ID = CLIENTE.ID WHERE CLIENTE.CEDULA ='" + cedula + "'");
            cedulaList=q3.getResultList();
            date = q2.getResultList();
           
           if (cedula.equals(cedulaList.get(0))) {
                 
               date1=date.get(0);
               System.out.println(date1);
               
               
            } else {
                date1 = null;
                
            }
        } catch (Exception ex) {
            date1 = null;
            System.out.println(ex);
        }
        em1.close();
        factory.close();
        return date1;
    }
     
     public List<String> GenerarFechas(int plazoPrestamo, Date actual) {
        List<String> lista = new ArrayList<String>();
         System.out.println(actual.getYear());
         SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
    
        String strDate = sm.format(actual);
     try {
         Date dt = sm.parse(strDate);
          LocalDate ahora = LocalDate.of(dt.getYear()+1900, dt.getMonth(), dt.getDay());
        
        int mes = ahora.getMonthValue();
        int anio = ahora.getYear();
        int dia = ahora.getDayOfMonth();
        if (dia == 29 || dia == 30 || dia == 31) {
            dia = 1;
        }
        lista.add(anio + "-" + mes + "-" + dia);
        for (int i = 0; i < plazoPrestamo; i++) {
            mes = mes + 1;
            if (mes == 13) {
                mes = 1;
                anio = anio + 1;
            }
            lista.add(anio + "-" + mes + "-" + dia);
        }

     } catch (ParseException ex) {
         Logger.getLogger(PagoPrestamoFacade.class.getName()).log(Level.SEVERE, null, ex);
     }
      
        return lista;
    }
    
}
