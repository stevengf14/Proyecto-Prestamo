/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Entidades;

/**
 *
 * @author Steven
 */
public class Tabla_Amortizacion {
    int numero;
    double capital;
    double interes;
    double valor_cuota;
    double saldo;
    String fecha_amortizacion;
    String estado;

    public Tabla_Amortizacion(int numero, double capital, double interes, double valor_cuota, double saldo, String fecha_amortizacion, String estado) {
        this.numero = numero;
        this.capital = capital;
        this.interes = interes;
        this.valor_cuota = valor_cuota;
        this.saldo = saldo;
        this.fecha_amortizacion = fecha_amortizacion;
        this.estado = estado;
    }

    public Tabla_Amortizacion() {
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public double getValor_cuota() {
        return valor_cuota;
    }

    public void setValor_cuota(double valor_cuota) {
        this.valor_cuota = valor_cuota;
    }

    public String getFecha_amortizacion() {
        return fecha_amortizacion;
    }

    public void setFecha_amortizacion(String fecha_amortizacion) {
        this.fecha_amortizacion = fecha_amortizacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
