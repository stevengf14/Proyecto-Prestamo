/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Modelo;

import ec.edu.espe.arquitectura.prestamo.Entidades.Amortizacion;
import ec.edu.espe.arquitectura.prestamo.Entidades.reporteD;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alejandro Torres
 */
@Local
public interface Bean_ReporteLocal {
//    public List<Amortizacion> verificarMora(String estado);
    public List<reporteD> reporteDeudores();
//    public List<String> verificarMora(String estado);
}
