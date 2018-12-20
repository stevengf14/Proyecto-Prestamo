/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Modelo;


import ec.edu.espe.arquitectura.prestamo.Entidades.Amortizacion;
import ec.edu.espe.arquitectura.prestamo.Entidades.Prestamo;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Samsung-PC
 */
@Local
public interface PagoPrestamoFacadeLocal {

 public Prestamo busquedaPrestamo(String cedula);
 public double busquedaMonto(String cedula);
 public int busquedaPlazo(String cedula);
 public String busquedaNombre(String cedula);
 public Date busquedaFecha(String cedula);
 public List<String> GenerarFechas(int plazoPrestamo, Date actual);
 public String CompararFechas(String fecha); 
 public List<Amortizacion> busquedaAmortizacion(String cedula);
  public boolean insertarPago(String id, String amo_id, String fechaPago, String valorCargos, String valortToal, String valorPagado);
public int ExtraerNumPagoPrestamo();
 
 public void updateTablaPrestamo(String id);
  public void updateTabla(String id, double saldo);
    
}
