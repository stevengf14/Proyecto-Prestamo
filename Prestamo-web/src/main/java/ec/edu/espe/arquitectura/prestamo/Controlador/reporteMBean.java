/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Controlador;

import ec.edu.espe.arquitectura.prestamo.Entidades.Amortizacion;
import ec.edu.espe.arquitectura.prestamo.Entidades.reporteD;
import ec.edu.espe.arquitectura.prestamo.Entidades.reporteDeudor;
import ec.edu.espe.arquitectura.prestamo.Modelo.Bean_ReporteLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Alejandro Torres
 */
@Named(value = "reporteMBean")
@SessionScoped
public class reporteMBean implements Serializable {

    private String tipoDeudor;
    private String cedula;
    private String nombreApellido;
    private double valorDeuda;
    private String estadoDeuda;
    private ArrayList<reporteDeudor> reporte = new ArrayList<reporteDeudor>();

    @EJB
    Bean_ReporteLocal beanReporte;

    public reporteMBean() {
    }

    public ArrayList<reporteDeudor> getReporte() {
        return reporte;
    }

    public void setReporte(ArrayList<reporteDeudor> reporte) {
        this.reporte = reporte;
    }

    public String getEstadoDeuda() {
        return estadoDeuda;
    }

    public void setEstadoDeuda(String estadoDeuda) {
        this.estadoDeuda = estadoDeuda;
    }

    public String getTipoDeudor() {
        return tipoDeudor;
    }

    public void setTipoDeudor(String tipoDeudor) {
        this.tipoDeudor = tipoDeudor;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public double getValorDeuda() {
        return valorDeuda;
    }

    public void setValorDeuda(double valorDeuda) {
        this.valorDeuda = valorDeuda;
    }

    public void llenarTabla() {
        String estado = "Expirado";
        System.out.println("Me hizo clic");

        reporteDeudor r;
        r = new reporteDeudor(beanReporte.reporteDeudores().get(0).toString(), beanReporte.reporteDeudores().get(1).toString(), beanReporte.reporteDeudores().get(2).toString(), Double.parseDouble(beanReporte.reporteDeudores().get(3).toString()), beanReporte.reporteDeudores().get(4).toString());
        reporte.add(r);
    }
}
