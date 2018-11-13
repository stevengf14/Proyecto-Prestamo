/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Controlador;

import ec.edu.espe.arquitectura.prestamo.Entidades.Amortizacion;
import ec.edu.espe.arquitectura.prestamo.Entidades.Cliente;
import ec.edu.espe.arquitectura.prestamo.Entidades.Prestamo;
import ec.edu.espe.arquitectura.prestamo.Entidades.Tabla_Amortizacion;
import ec.edu.espe.arquitectura.prestamo.Entidades.Total;
import ec.edu.espe.arquitectura.prestamo.Modelo.Bean_NuevoPrestamoLocal;

import ec.edu.espe.arquitectura.prestamo.Modelo.PagoPrestamoFacadeLocal;
import ec.edu.espe.arquitectura.prestamo.util.FacesUtil;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author Samsung-PC
 */
@ManagedBean
@SessionScoped
public class PagosBean implements Serializable {

    
    @EJB
    PagoPrestamoFacadeLocal bean_pagos;
    @EJB
    Bean_NuevoPrestamoLocal bean_nuevoPrestamo;
    Cliente cli = new Cliente();
    List<Amortizacion> amortbusqueda = new ArrayList<Amortizacion>();
    List<Amortizacion> listaAmort = new ArrayList<Amortizacion>();
    private double monto;
    private int plazo;
    private ArrayList<Total> lista_total = new ArrayList<>();
    private String nombre="";
    private double valorCuota;
    private double xCobrar;
    private double valorRecibido;
    private double cambio;
    private String fechaPago;
    private static double cargo;
    private static double total;
    private static int idAmortizacion;
    private int idAmort;
    Amortizacion amorti = new Amortizacion();
    private ArrayList<Tabla_Amortizacion> amortizacion = new ArrayList<Tabla_Amortizacion>();
    private ArrayList<Amortizacion> listAmortiza = new ArrayList<Amortizacion>();
    public List<Amortizacion> getListaAmort() {
        return listaAmort;
    }

    public int getIdAmort() {
        return idAmort;
    }

    public void setIdAmort(int idAmort) {
        this.idAmort = idAmort;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getIdAmortizacion() {
        return idAmortizacion;
    }

    public void setIdAmortizacion(int idAmortizacion) {
        this.idAmortizacion = idAmortizacion;
    }
    

    public void setListaAmort(List<Amortizacion> listaAmort) {
        this.listaAmort = listaAmort;
    }

    public Amortizacion getAmorti() {
        return amorti;
    }

    public ArrayList<Amortizacion> getListAmortiza() {
        return listAmortiza;
    }

    public void setListAmortiza(ArrayList<Amortizacion> listAmortiza) {
        this.listAmortiza = listAmortiza;
    }

    
    
    public void setAmorti(Amortizacion amorti) {
        this.amorti = amorti;
    }
    

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    

    public List<Amortizacion> getAmortbusqueda() {
        return amortbusqueda;
    }
    
    
    public void setAmortbusqueda(List<Amortizacion> amortbusqueda) {
        this.amortbusqueda = amortbusqueda;
    }
    public double getCargo() {
        return cargo;
    }

    public void setCargo(double cargo) {
        this.cargo = cargo;
    }
     Tabla_Amortizacion ta;
     private Amortizacion taSelected;

    public Amortizacion getTaSelected() {
        return taSelected;
    }

    public void setTaSelected(Amortizacion taSelected) {
        this.taSelected = taSelected;
    }

     
    public Tabla_Amortizacion getTa() {
        return ta;
    }

    public void setTa(Tabla_Amortizacion ta) {
        this.ta = ta;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public double getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(double valorCuota) {
        this.valorCuota = valorCuota;
    }

    public double getxCobrar() {
        return xCobrar;
    }

    public void setxCobrar(double xCobrar) {
        this.xCobrar = xCobrar;
    }

    public double getValorRecibido() {
        return valorRecibido;
    }

    public void setValorRecibido(double valorRecibido) {
        this.valorRecibido = valorRecibido;
    }

    public double getCambio() {
        return cambio;
    }

    public void setCambio(double cambio) {
        this.cambio = cambio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    private String cedula = "";
    private static String cedula1 = "";

    public static String getCedula1() {
        return cedula1;
    }

    public static void setCedula1(String cedula1) {
        PagosBean.cedula1 = cedula1;
    }
    
    Prestamo pres= new Prestamo();

    public Prestamo getPres() {
        return pres;
    }

    public void setPres(Prestamo pres) {
        this.pres = pres;
    }
 

    public ArrayList<Tabla_Amortizacion> getAmortizacion() {
        return amortizacion;
    }

    public void setAmortizacion(ArrayList<Tabla_Amortizacion> amortizacion) {
        this.amortizacion = amortizacion;
    }

    public Bean_NuevoPrestamoLocal getBean_nuevoPrestamo() {
        return bean_nuevoPrestamo;
    }

    public void setBean_nuevoPrestamo(Bean_NuevoPrestamoLocal bean_nuevoPrestamo) {
        this.bean_nuevoPrestamo = bean_nuevoPrestamo;
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

    public ArrayList<Total> getLista_total() {
        return lista_total;
    }

    public void setLista_total(ArrayList<Total> lista_total) {
        this.lista_total = lista_total;
    }

    public PagoPrestamoFacadeLocal getBean_pagos() {
        return bean_pagos;
    }

    public void setBean_pagos(PagoPrestamoFacadeLocal bean_pagos) {
        this.bean_pagos = bean_pagos;
    }
   
  
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

  
    
   
    /**
     * Creates a new instance of PagosBean
     */
    public PagosBean() {
        
    }
  
   

 
    public void CalculosPago(){
        
        for (Amortizacion amort: this.amortbusqueda) {
            if(amort.getEstado().equals("Pendiente")||amort.getEstado().equals("Mora")){
                this.taSelected=amort;
               // this.idAmortizacion=taSelected.getId().intValue();
                 System.out.println("hi------: " +taSelected.getId().intValue());
                 PagosBean.idAmortizacion=taSelected.getId().intValue();
                if(this.taSelected.getEstado().equals("Mora")){
                    PagosBean.cargo=6.38;
                }else{
                    PagosBean.cargo=0;
                }
                PagosBean.total=PagosBean.cargo+this.taSelected.getValorCuota().doubleValue();
                break;
            }else{
                bean_pagos.updateTablaPrestamo(amort.getPreId()+"");
            }
                     
        }
        
        
    }
   
        public void regisPago() {
        
         boolean inserto;
         Date fecha = new Date();
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        System.out.println("--------id: "+this.idAmortizacion);
        this.fechaPago = format.format(calendar.getTime());
        inserto=bean_pagos.insertarPago(bean_pagos.ExtraerNumPagoPrestamo()+"", idAmortizacion+"", this.fechaPago, this.cargo+"", this.total+"", this.valorRecibido+"");
        if(inserto==true)
         FacesUtil.addMessageInfo("Se registro el pago correctamente");
        
        bean_pagos.updateTabla(this.idAmortizacion+"");
        actualizarLimpiar();
        
    }
    
     public void cargarTabla() {
         cedula1=cedula;
         cli = bean_nuevoPrestamo.verificarCliente(cedula);
         if (cli != null) {
             nombre=bean_pagos.busquedaNombre(cedula);
             amortbusqueda =bean_pagos.busquedaAmortizacion(cedula);
//             System.out.println(amortbusqueda.get(0).getClass().toString());
             CalculosPago();
             
         
        } else {
            FacesUtil.addMessageWarn(null, "El cliente no existe");
            
        }
        
      
    
 }
     
      public void actualizarLimpiar() {
         amortbusqueda.clear();
         cli = bean_nuevoPrestamo.verificarCliente(cedula1);
         if (cli != null) {
             nombre=bean_pagos.busquedaNombre(cedula1);
             amortbusqueda =bean_pagos.busquedaAmortizacion(cedula1);
//             System.out.println(amortbusqueda.get(0).getClass().toString());
             CalculosPago();
             
         
        } else {
            FacesUtil.addMessageWarn(null, "El cliente no existe");
            
        }
        

 }
 
 
    
    
}
