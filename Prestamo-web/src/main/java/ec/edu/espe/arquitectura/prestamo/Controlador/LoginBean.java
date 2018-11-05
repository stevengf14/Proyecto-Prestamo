/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Controlador;

import ec.edu.espe.arquitectura.prestamo.Entidades.Usuario;
import ec.edu.espe.arquitectura.prestamo.Modelo.Bean_UsuariosLocal;
import ec.edu.espe.arquitectura.prestamo.util.FacesUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Steven
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    @EJB
    Bean_UsuariosLocal bean_usuarios;

    private String usuario = "";
    private String contrasenia = "";
    private String mensaje = "";

    UsuarioBean ub = new UsuarioBean();
    Usuario us = new Usuario();

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String ingresar() {
        String retorno = "";
        try {

            us = bean_usuarios.VerificarUsuario(usuario, contrasenia);
            if (us != null && us.getClave().equals(contrasenia)) {
                mensaje = "Usuario Encontrado";
                retorno = "Inicio";
            } else {
                us = null;
                FacesUtil.addMessageError(null, "Los datos ingresados son incorrectos");
            }

        } catch (Exception e) {
            FacesUtil.addMessageError(null, "Los datos ingresados son incorrectos");
        }
        return retorno;
    }

    public void logout() {
        us = null;
    }
}
