/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Modelo;

import javax.ejb.Local;

/**
 *
 * @author Alejandro Torres
 */
@Local
public interface Bean_nuevoProductoLocal {
    public int extraerNumProducto();
    public void insertarProducto(int id, double montoMax, double montoMin, String descripcion, int plazoMax, int plazoMin);
    public void insertarTipoProducto(int id, int tipoId, String descripcion, String estado, double interes);
}
