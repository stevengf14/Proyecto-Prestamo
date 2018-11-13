/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Entidades;

/**
 *
 * @author Alejandro Torres
 */
public class reporteDeudor {
    private String tipoDeudor;
    private String cedula;
    private String nombreApellido;
    private double valorDeduda;
    private String estadoDeuda;

    public reporteDeudor(String tipoDeudor, String cedula, String nombreApellido, double valorDeuda, String estadoDeuda) {
        this.tipoDeudor = tipoDeudor;
        this.cedula = cedula;
        this.nombreApellido = nombreApellido;
        this.valorDeduda = valorDeuda;
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

    public double getValorDeduda() {
        return valorDeduda;
    }

    public void setValorDeduda(double valorDeduda) {
        this.valorDeduda = valorDeduda;
    }

    public String getEstadoDeuda() {
        return estadoDeuda;
    }

    public void setEstadoDeuda(String estadoDeuda) {
        this.estadoDeuda = estadoDeuda;
    }
}
