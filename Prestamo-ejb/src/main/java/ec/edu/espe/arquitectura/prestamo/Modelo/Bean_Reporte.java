/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Modelo;

import ec.edu.espe.arquitectura.prestamo.Entidades.Amortizacion;
import ec.edu.espe.arquitectura.prestamo.Entidades.reporteD;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
 * @author Alejandro Torres
 */
@Stateless
public class Bean_Reporte implements Bean_ReporteLocal {
    
    List<reporteD> listaS = new ArrayList<reporteD>();  
    @Override
//    public List<Amortizacion> verificarMora(String estado) {
//        List<Amortizacion> rd = new ArrayList<>();
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
//        EntityManager em1 = factory.createEntityManager();
//        try {
//            Query q = em1.createNativeQuery("select c.cedula, c.tipo, c.nombre, a.valor_cuota, a.estado from amortizacion a, prestamo p, cliente c where a.pre_id=p.id and p.cli_id=c.id and a.estado='" + estado + "'");
//            rd= q.getResultList();
//        } catch (Exception ex) {
//            
//        }
//        return rd;
//    }
//////    public reporteD verificarMora() {
//////        reporteD r = new reporteD();
//////        List<reporteD> deudor = new ArrayList<reporteD>();
//////        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
//////        EntityManager em1 = factory.createEntityManager();
//////        try {
//////            Query q = em1.createNativeQuery("select c.tipo, c.cedula, c.nombre, a.valor_cuota, a.estado from amortizacion a, prestamo p, cliente c where a.pre_id=p.id and p.cli_id=c.id");
//////            deudor = q.getResultList();
//////            r.setTipoDeudor(deudor.get(0).toString());
//////            r.setCedula(deudor.get(1).toString());
//////            r.setNombreApellido(deudor.get(2).toString());
//////            r.setValorDeduda(Double.parseDouble(deudor.get(3).toString()));
//////            r.setEstadoDeuda(deudor.get(4).toString());
//////        } catch (Exception ex) {
//////            System.out.println(ex);
//////        }
//////        return r;
//////    }
        
//    public List<String> verificarMora(String estado) {
//        List<String> rd = new ArrayList<>();
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
//        EntityManager em1 = factory.createEntityManager();
//        try {
//            Query q = em1.createNativeQuery("select c.cedula, c.tipo, c.nombre, a.valor_cuota, a.estado from amortizacion a, prestamo p, cliente c where a.pre_id=p.id and p.cli_id=c.id");
//            rd = q.getResultList();
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//        return rd;
//    }    
    public List<reporteD> reporteDeudores() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
        EntityManager em1 = factory.createEntityManager();
        
        try {
            Query a = em1.createNativeQuery(
                    "SELECT c.tipo, c.cedula, c.nombre, a.valor_cuota, a.estado from amortizacion a, prestamo p, cliente c where a.pre_id=p.id and p.cli_id=c.id");
            List<Object[]> listado = a.getResultList();
            for (Object[] objects : listado) {
                reporteD deudor =new reporteD();
                deudor.setTipoDeudor((String)objects[0]);
                deudor.setCedula((String)objects[1]);
                deudor.setNombreApellido((String)objects[2]);
                deudor.setValorDeduda((Double)objects[3]);
                deudor.setEstadoDeuda((String)objects[3]);
                listaS.add(deudor);
            }
            //listaS.forEach(r -> System.out.print(r.getTipoDeudor() + " "+ r.getCedula() + " "+ r.getNombreApellido() + " "+ r.getValorDeduda() + " "+ r.getEstadoDeuda()));
            return listaS;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }        
    }    
}
