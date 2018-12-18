/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Controlador;

import ec.edu.espe.arquitectura.prestamo.Entidades.Producto;
import ec.edu.espe.arquitectura.prestamo.Entidades.TipoProducto;
import ec.edu.espe.arquitectura.prestamo.Modelo.Bean_nuevoProductoLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Alejandro Torres
 */
@ManagedBean
@SessionScoped
public class NuevoProductoBean implements Serializable {

    private String tipo;
    private double montoMin;
    private double montoMax;
    private int plazoMax;
    private int plazoMin;
    private String tipoCliente;
    //Atributos para el tipo de Producto
    private String descripcion;
    private String estado;
    private double interes;

    static TipoProducto tipoProducto = new TipoProducto();
    static Producto prod = new Producto();

    private String tipo_m;
    private double montoMin_m;
    private double montoMax_m;
    private int plazoMax_m;
    private int plazoMin_m;
    private String tipoCliente_m;
    //Atributos para el tipo de Producto
    private String descripcion_m;
    private String estado_m;
    private double interes_m;

    private List<String> listaTipoPrestamo;

    @EJB
    Bean_nuevoProductoLocal bean_nuevoProducto;

    public String getTipo_m() {
        return tipo_m;
    }

    public void setTipo_m(String tipo_m) {
        this.tipo_m = tipo_m;
    }

    public double getMontoMin_m() {
        return montoMin_m;
    }

    public void setMontoMin_m(double montoMin_m) {
        this.montoMin_m = montoMin_m;
    }

    public double getMontoMax_m() {
        return montoMax_m;
    }

    public void setMontoMax_m(double montoMax_m) {
        this.montoMax_m = montoMax_m;
    }

    public int getPlazoMax_m() {
        return plazoMax_m;
    }

    public void setPlazoMax_m(int plazoMax_m) {
        this.plazoMax_m = plazoMax_m;
    }

    public int getPlazoMin_m() {
        return plazoMin_m;
    }

    public void setPlazoMin_m(int plazoMin_m) {
        this.plazoMin_m = plazoMin_m;
    }

    public String getTipoCliente_m() {
        return tipoCliente_m;
    }

    public void setTipoCliente_m(String tipoCliente_m) {
        this.tipoCliente_m = tipoCliente_m;
    }

    public String getDescripcion_m() {
        return descripcion_m;
    }

    public void setDescripcion_m(String descripcion_m) {
        this.descripcion_m = descripcion_m;
    }

    public String getEstado_m() {
        return estado_m;
    }

    public void setEstado_m(String estado_m) {
        this.estado_m = estado_m;
    }

    public double getInteres_m() {
        return interes_m;
    }

    public void setInteres_m(double interes_m) {
        this.interes_m = interes_m;
    }

    public List<String> getListaTipoPrestamo() {
        return listaTipoPrestamo;
    }

    public void setListaTipoPrestamo(List<String> listaTipoPrestamo) {
        this.listaTipoPrestamo = listaTipoPrestamo;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMontoMin() {
        return montoMin;
    }

    public void setMontoMin(double montoMin) {
        this.montoMin = montoMin;
    }

    public double getMontoMax() {
        return montoMax;
    }

    public void setMontoMax(double montoMax) {
        this.montoMax = montoMax;
    }

    public int getPlazoMax() {
        return plazoMax;
    }

    public void setPlazoMax(int plazoMax) {
        this.plazoMax = plazoMax;
    }

    public int getPlazoMin() {
        return plazoMin;
    }

    public void setPlazoMin(int plazoMin) {
        this.plazoMin = plazoMin;
    }

    //Tipo producto
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    @PostConstruct
    public void init() {
        cargarTipoPrestamo();
        //extraerCampos();
    }

    public void cargarTipoPrestamo() {
        listaTipoPrestamo = bean_nuevoProducto.cargarTipoPrestamo();
    }

    public String guardarProducto() throws InterruptedException {
        int codigo = bean_nuevoProducto.insertarTipoProducto(this.descripcion, this.montoMax, this.montoMin, this.plazoMax, this.plazoMin);
        bean_nuevoProducto.insertarProducto(codigo, this.tipo, this.estado, this.interes, this.tipoCliente);
        return "Inicio";
    }

    public void extraerCampos() {
        prod = bean_nuevoProducto.extraerProducto(tipo_m);
        tipoProducto = bean_nuevoProducto.extraerTipoProducto(prod.getProId());

        descripcion_m = tipoProducto.getDescripcion();
        estado_m = prod.getProEstado();
        montoMin_m = tipoProducto.getMontoMinimo().doubleValue();
        montoMax_m = tipoProducto.getMontoMaximo().doubleValue();
        tipoCliente_m = prod.getProTipoCliente();
        interes_m = prod.getProInteres().doubleValue();
        plazoMin_m = tipoProducto.getPlazoMin().intValue();
        plazoMax_m = tipoProducto.getPlazoMax().intValue();
        System.out.println("hasta aqui 1");
    }

    public String actualizarProductos() {
        tipoProducto.setDescripcion(descripcion_m);
        tipoProducto.setMontoMinimo(BigDecimal.valueOf(montoMin_m));
        tipoProducto.setMontoMaximo(BigDecimal.valueOf(montoMax_m));
        tipoProducto.setPlazoMin(BigInteger.valueOf(plazoMin_m));
        tipoProducto.setPlazoMax(BigInteger.valueOf(plazoMax_m));

        prod.setProEstado(estado_m);
        prod.setProTipoCliente(tipoCliente_m);
        prod.setProInteres(BigDecimal.valueOf(interes_m));

        if (bean_nuevoProducto.actualizarProducto(prod) == true) {
            System.out.println("prod actualizados");
        } else {
            System.out.println("Error1");
        }
        if (bean_nuevoProducto.actualizarTipoProducto(tipoProducto) == true) {
            System.out.println("tipoprodd actualizados");
        } else {
            System.out.println("Error1 tp");
        }
        return "Inicio";
    }

}
