/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Servicios;

import ec.edu.espe.arquitectura.prestamo.Entidades.Usuario;
import javax.ejb.Local;

/**
 *
 * @author Samsung-PC
 */
@Local
public interface Bean_UsuLocal {
    public Usuario encontrarUsuario(String id);
    public Usuario editarUsuario(String id, String nombre, String clave, String correo);
    public Usuario crearUsuario(String id,String nombre, String clave, String correo);
     public void eliminarUsuario(String id);
}
