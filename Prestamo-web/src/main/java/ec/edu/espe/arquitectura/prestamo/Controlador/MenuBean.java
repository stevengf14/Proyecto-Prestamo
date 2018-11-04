/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Controlador;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Steven
 */
@ManagedBean
@ViewScoped
public class MenuBean implements Serializable{

    /**
     * Creates a new instance of MenuBean
     */
    public MenuBean() {
        
    }
    public String NuevoPrestamo()
    {
        return "NuevoPrestamo";
    }
    public String Salir()
    {
        return "Usuario_Login";
    }
    
}
