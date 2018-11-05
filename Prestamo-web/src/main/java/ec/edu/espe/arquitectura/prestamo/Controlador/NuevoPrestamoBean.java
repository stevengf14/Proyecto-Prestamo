/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Controlador;

import ec.edu.espe.arquitectura.prestamo.util.FacesUtil;
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
public class NuevoPrestamoBean implements Serializable {

    private String cedula;
    private String tipo;
    private double monto;
    private int plazo;
    
    public NuevoPrestamoBean() {
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }
    public String aceptar()
    {
        if(monto<10000)
            return "DetallePrestamo";
        else
        {
            FacesUtil.addMessageWarn(null, "El monto debe ser entre bla y bla");
            return "";
        }
    }
}
