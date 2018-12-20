/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Servicios;

import ec.edu.espe.arquitectura.prestamo.Entidades.Amortizacion;
import java.math.BigDecimal;
import javax.ejb.Local;

/**
 *
 * @author Samsung-PC
 */
@Local
public interface Bean_PagoPrestamoLocal {
     public Amortizacion editarAmortizacion(BigDecimal id_amortiza, BigDecimal valor_pago);
}
