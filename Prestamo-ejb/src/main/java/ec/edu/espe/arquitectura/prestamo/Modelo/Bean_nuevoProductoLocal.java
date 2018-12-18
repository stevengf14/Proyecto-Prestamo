/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Modelo;

import ec.edu.espe.arquitectura.prestamo.Entidades.Producto;
import ec.edu.espe.arquitectura.prestamo.Entidades.TipoProducto;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alejandro Torres
 */
@Local
public interface Bean_nuevoProductoLocal {

    public int extraerNumProducto();

    public int insertarTipoProducto(String descripcion, double montoMax, double montoMin, int plazoMax, int plazoMin);

    public void insertarProducto(int id, String descripcion, String estado, double interes, String tipoCliente);

    public List<String> cargarTipoPrestamo();
    
    public Producto extraerProducto(String tipo);
    
    public TipoProducto extraerTipoProducto(BigDecimal tipo);
    
    public boolean actualizarProducto(Producto prod);
    
    public boolean actualizarTipoProducto(TipoProducto tipoProducto);
}
