/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Servicios;

import ec.edu.espe.arquitectura.prestamo.Entidades.Comision;
import java.math.BigDecimal;
import javax.ejb.Local;

/**
 *
 * @author Steven
 */
@Local
public interface Bean_ComisionLocal {
    public Comision encontrarComision(int id);
    public Comision editarComision(BigDecimal id, String descripcion, String estado);
    public Comision crearComision(BigDecimal id, String descripcion, String estado);
    public void eliminarComision(int id);
}
