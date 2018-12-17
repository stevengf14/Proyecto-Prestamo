/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Modelo;

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
 * @author Alejandro Torres
 */
@Stateless
public class Bean_nuevoProducto implements Bean_nuevoProductoLocal {

    public int extraerNumProducto() {
        int num;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
        EntityManager em1 = factory.createEntityManager();

        List<BigDecimal> idList = new ArrayList<BigDecimal>();
        try {
            Query q = em1.createNativeQuery("SELECT MAX(ID) FROM TIPO_PRODUCTO");
            idList = q.getResultList();
            num = idList.get(0).intValue() + 1;
        } catch (Exception ex) {
            num = 1;
        }
        em1.close();
        factory.close();
        return num;
    }
    
    public void insertarProducto(int id, double montoMax, double montoMin, String descripcion, int plazoMax, int plazoMin){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
        EntityManager em1 = factory.createEntityManager();
        try {
            System.out.println(extraerNumProducto());
//            em1.getTransaction().begin();
            em1.joinTransaction();
            Query q = em1.createNativeQuery("INSERT INTO TIPO_PRODUCTO (ID,MONTO_MAXIMO,MONTO_MINIMO,DESCRIPCION,PLAZO_MAX,PLAZO_MIN) VALUES (" + id + "," + montoMax + "," + montoMin + ", '" + descripcion + "', " + plazoMax + ", " + plazoMin + ")");
            q.executeUpdate();
//            em1.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("El error esta aqui");
//            em1.getTransaction().rollback();
        }
        em1.close();
        factory.close();
    }
    
    public void insertarTipoProducto(int id, int tipoId, String descripcion, String estado, double interes){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
        EntityManager em1 = factory.createEntityManager();
        try {
            System.out.println(extraerNumProducto());
            em1.joinTransaction();
            Query q = em1.createNativeQuery("INSERT INTO PRODUCTO VALUES (" + id + "," + tipoId + ", '" + descripcion + "', '" + estado + "', " + interes + ")");
            q.executeUpdate();
        } catch (Exception ex) {
            System.out.println("El error esta aqui");
        }
        em1.close();
        factory.close();
    }
}
