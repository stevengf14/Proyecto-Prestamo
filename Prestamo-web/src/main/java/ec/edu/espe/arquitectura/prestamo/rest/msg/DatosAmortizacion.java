/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.rest.msg;

import ec.edu.espe.arquitectura.prestamo.Entidades.Cargo;
import ec.edu.espe.arquitectura.prestamo.Entidades.PagoPrestamo;
import ec.edu.espe.arquitectura.prestamo.Entidades.Prestamo;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Alejandro Torres
 */
public class DatosAmortizacion {
    private BigDecimal id;
    private BigDecimal capital;
    private BigDecimal interes;
    private BigDecimal valorCuota;
    private Date fechaAmortizacion;
    private String estado;
    private BigInteger numero;
    private List<Cargo> cargoList;
    private List<PagoPrestamo> pagoPrestamoList;
    private Prestamo preId;
    private BigDecimal saldo;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigInteger getNumero() {
        return numero;
    }

    public void setNumero(BigInteger numero) {
        this.numero = numero;
    }

    public List<Cargo> getCargoList() {
        return cargoList;
    }

    public void setCargoList(List<Cargo> cargoList) {
        this.cargoList = cargoList;
    }

    public List<PagoPrestamo> getPagoPrestamoList() {
        return pagoPrestamoList;
    }

    public void setPagoPrestamoList(List<PagoPrestamo> pagoPrestamoList) {
        this.pagoPrestamoList = pagoPrestamoList;
    }

    public Prestamo getPreId() {
        return preId;
    }

    public void setPreId(Prestamo preId) {
        this.preId = preId;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "DatosAmortizacion{" + "id=" + id + ", capital=" + capital + ", interes=" + interes + ", valorCuota=" + valorCuota + ", fechaAmortizacion=" + fechaAmortizacion + ", estado=" + estado + ", numero=" + numero + ", cargoList=" + cargoList + ", pagoPrestamoList=" + pagoPrestamoList + ", preId=" + preId + ", saldo=" + saldo + '}';
    }  
}
