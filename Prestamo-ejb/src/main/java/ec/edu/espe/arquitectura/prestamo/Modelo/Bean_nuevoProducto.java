/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Modelo;

import ec.edu.espe.arquitectura.prestamo.Entidades.Producto;
import ec.edu.espe.arquitectura.prestamo.Entidades.TipoProducto;
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

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
    EntityManager em1 = factory.createEntityManager();

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

    public int insertarTipoProducto(String descripcion, double montoMax, double montoMin, int plazoMax, int plazoMin) {
        int id = extraerNumProducto();
        try {
            em1.joinTransaction();
            Query q = em1.createNativeQuery("INSERT INTO TIPO_PRODUCTO (ID,MONTO_MAXIMO,MONTO_MINIMO,DESCRIPCION,PLAZO_MAX,PLAZO_MIN) VALUES (" + id + "," + montoMax + "," + montoMin + ", '" + descripcion + "', " + plazoMax + ", " + plazoMin + ")");
            q.executeUpdate();
        } catch (Exception ex) {
            System.out.println("El error esta aqui");
        }
        return id;
    }

    public void insertarProducto(int id, String descripcion, String estado, double interes, String tipoCliente) {
        try {
            em1.joinTransaction();
            Query q = em1.createNativeQuery("INSERT INTO PRODUCTO VALUES (" + id + "," + id + ", '" + descripcion + "', '" + estado + "', " + interes + ", '" + tipoCliente + "')");
            q.executeUpdate();
        } catch (Exception ex) {
            System.out.println("El error esta aqui");
        }
    }

    public List<String> cargarTipoPrestamo() {
        List<String> lista = new ArrayList<String>();
        Query q = em1.createNativeQuery("SELECT PRO_DESCRIPCION FROM PRODUCTO");
        lista = q.getResultList();
        return lista;
    }

    public Producto extraerProducto(String tipo) {
        Producto prod = new Producto();
        List<BigDecimal> lista = new ArrayList<BigDecimal>();
        Query q = em1.createNativeQuery("SELECT PRO_ID FROM PRODUCTO WHERE PRO_DESCRIPCION='" + tipo + "'");
        lista = q.getResultList();
        em1.joinTransaction();
        prod = em1.find(Producto.class, lista.get(0));
        return prod;
    }

    public TipoProducto extraerTipoProducto(BigDecimal tipo) {
        TipoProducto tp = new TipoProducto();
        em1.joinTransaction();
        tp = em1.find(TipoProducto.class, tipo);
        return tp;
    }

    public boolean actualizarProducto(Producto prod) {
        EntityManagerFactory factory2 = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
        EntityManager em2 = factory2.createEntityManager();
        try {
            em2.joinTransaction();

            //Producto
            Producto p = new Producto();
            p = em2.find(Producto.class, prod.getProId());
            p.setProEstado(prod.getProEstado());
            p.setProTipoCliente(prod.getProTipoCliente());
            p.setProInteres(prod.getProInteres());
            em2.persist(p);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean actualizarTipoProducto(TipoProducto tipoProducto) {

        EntityManagerFactory factory2 = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
        EntityManager em2 = factory2.createEntityManager();
        boolean val = false;
        try {
            em2.joinTransaction();
            //TipoPrestamo
            TipoProducto tp = new TipoProducto();
            tp = em2.find(TipoProducto.class, tipoProducto.getId());
            tp.setDescripcion(tipoProducto.getDescripcion());
            tp.setMontoMinimo(tipoProducto.getMontoMinimo());
            tp.setMontoMaximo(tipoProducto.getMontoMaximo());
            tp.setPlazoMin(tipoProducto.getPlazoMin());
            tp.setPlazoMax(tipoProducto.getPlazoMax());
            em2.persist(tp);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
