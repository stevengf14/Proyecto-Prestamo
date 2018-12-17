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
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

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
    
    //Atributos para el tipo de Producto
    private String descripcion;
    private String estado;
    private double interes;
    @EJB
    Bean_nuevoProductoLocal bean_nuevoProducto;

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
    

    public String guardarProducto() throws InterruptedException
    {
        int codigo = 0;
        bean_nuevoProducto.insertarProducto(bean_nuevoProducto.extraerNumProducto(), this.montoMax, this.montoMin, this.tipo, this.plazoMax, this.plazoMin);
        //bean_nuevoProducto.insertarProducto(bean_nuevoProducto.extraerNumProducto(), 500.0, 5000.0, "Hola", 3, 12);
        codigo = bean_nuevoProducto.extraerNumProducto();
//        bean_nuevoProducto.insertarTipoProducto(10, codigo, this.descripcion, this.estado, this.interes);
        return "Nuevo Producto";
    }
}
