/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Alejandro Torres
 */
@Named(value = "tipoProductoBean")
@SessionScoped
public class tipoProductoBean implements Serializable {

    /**
     * Creates a new instance of tipoProductoBean
     */
    private ArrayList<SelectItem> cmb1 = null;    
    
    public tipoProductoBean() {
    }

    public ArrayList<SelectItem> getCmb1() {
        if(cmb1 == null)
            cmb1 = new ArrayList<>();
        return cmb1;
    }

    public void setCmb1(ArrayList<SelectItem> cmb1) {
        this.cmb1 = cmb1;
    }
    
    
}
