/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Modelo;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Steven
 */
@Stateful
public class Bean_Usuarios implements Bean_UsuariosLocal {

    public boolean VerificarUsuario(String usu, String contra) {
        boolean val = false;
        List<String> usuario = new ArrayList<String>();
        List<String> contrasenia = new ArrayList<String>();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
        EntityManager em1 = factory.createEntityManager();
        try {
            Query q = em1.createNativeQuery("SELECT ID FROM USUARIO WHERE ID='" + usu + "'");
            Query q2 = em1.createNativeQuery("SELECT CLAVE FROM USUARIO WHERE ID='" + usu + "'");
            usuario = q.getResultList();
            contrasenia = q2.getResultList();
            if (usu.equals(usuario.get(0)) && contra.equals(contrasenia.get(0))) {
                val = true;
            } else {
                val = false;
            }
            //ls_mensaje=String.valueOf(lista.size());
        } catch (Exception ex) {
            //ls_mensaje = ex.getMessage();
            val = false;
        }
        em1.close();
        factory.close();
        return val;
    }
    
    

}
