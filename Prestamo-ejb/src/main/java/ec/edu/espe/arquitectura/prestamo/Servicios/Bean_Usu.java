/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Servicios;

import ec.edu.espe.arquitectura.prestamo.Entidades.Comision;
import ec.edu.espe.arquitectura.prestamo.Entidades.Usuario;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Samsung-PC
 */
@Stateless
public class Bean_Usu implements Bean_UsuLocal {

   EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
    EntityManager em1 = factory.createEntityManager();
    
     public Usuario encontrarUsuario(String id) {
        Usuario usu = new Usuario();
        em1.joinTransaction();
        usu = em1.find(Usuario.class, id);
        return usu;
    }
     
     public Usuario editarUsuario(String id, String nombre, String clave, String correo) {
        Usuario usu = new Usuario();
        em1.joinTransaction();
        usu = em1.find(Usuario.class, id);
        usu.setClave(clave);
        usu.setCorreo(correo);
        usu.setNombre(nombre);
        em1.persist(usu);
        return usu;
    }
     
       public Usuario crearUsuario(String id,String nombre, String clave, String correo) {
        Usuario usu = new Usuario(id);
        em1.joinTransaction();
        usu.setClave(clave);
        usu.setCorreo(correo);
        usu.setNombre(nombre);
        em1.persist(usu);
        return usu;
    }
       
    public void eliminarUsuario(String id) {
        Usuario usu = new Usuario();
        em1.joinTransaction();
        usu = em1.find(Usuario.class, id);
        em1.remove(usu);
    }
}
