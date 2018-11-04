/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Controlador;

import ec.edu.espe.arquitectura.prestamo.Entidades.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Steven
 */
@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    private Usuario usuario;
    
    public String getNombre() {
        return this.usuario.getNombre();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
