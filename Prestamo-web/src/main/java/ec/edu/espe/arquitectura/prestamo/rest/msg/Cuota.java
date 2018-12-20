/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.rest.msg;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author solan
 */
public class Cuota {

    public BigDecimal id;
    public BigDecimal valorCuota;
    public Date fechaAmortizacion;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(BigDecimal valorCuota) {
        this.valorCuota = valorCuota;
    }

    public Date getFechaAmortizacion() {
        return fechaAmortizacion;
    }

    public void setFechaAmortizacion(Date fechaAmortizacion) {
        this.fechaAmortizacion = fechaAmortizacion;
    }
}
