/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Controlador;

import java.awt.event.ActionEvent;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Steven
 */
@ManagedBean
@SessionScoped
public class DetalleBean implements Serializable {

    
    public DetalleBean() {
    }
    
    public String aceptar(ActionEvent e)
    {
        return "Inicio";
    }
}
