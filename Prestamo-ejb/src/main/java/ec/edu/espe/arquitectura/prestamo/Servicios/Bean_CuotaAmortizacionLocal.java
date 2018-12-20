/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Servicios;

import ec.edu.espe.arquitectura.prestamo.Entidades.Amortizacion;
import javax.ejb.Local;

/**
 *
 * @author solan
 */
@Local
public interface Bean_CuotaAmortizacionLocal {
    public Amortizacion encontrarCuota(int id);
}
