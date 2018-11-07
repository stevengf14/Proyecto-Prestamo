/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Controlador;

import ec.edu.espe.arquitectura.prestamo.Entidades.Cliente;
import ec.edu.espe.arquitectura.prestamo.Modelo.Bean_NuevoPrestamo;
import ec.edu.espe.arquitectura.prestamo.Modelo.Bean_NuevoPrestamoLocal;
import ec.edu.espe.arquitectura.prestamo.util.FacesUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

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

    @EJB
    Bean_NuevoPrestamoLocal bean_nuevoPrestamo;

    Cliente cli = new Cliente();
    
    public NuevoPrestamoBean() {
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
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



    public String aceptar() {
        cli = bean_nuevoPrestamo.verificarCliente(cedula);
        if (cli != null) {
            if (bean_nuevoPrestamo.verificarTipoPrestamoCliente(cedula, tipo)) {
                if (bean_nuevoPrestamo.validarMonto(tipo, monto)) {
                    if (bean_nuevoPrestamo.validarPlazo(tipo, plazo)) {
                        return "DetallePrestamo";
                    } else {
                        FacesUtil.addMessageWarn(null, bean_nuevoPrestamo.mensajePlazo(tipo));
                        return "";
                    }
                } else {
                    FacesUtil.addMessageWarn(null, bean_nuevoPrestamo.mensajeMonto(tipo));
                    return "";
                }
            } else {
                if (tipo.equals("Comercial")) {
                    FacesUtil.addMessageWarn(null, "Únicamente los clientes jurídicos pueden acceder a prétamos comerciales");
                } else {
                    FacesUtil.addMessageWarn(null, "Los clientes jurídicos pueden acceder únicamente a préstamos comerciales");
                }
                return "";
            }
        } else {
            FacesUtil.addMessageWarn(null, "El cliente no existe");
            return "";
        }

    }

}
