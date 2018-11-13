/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Modelo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Steven
 */
@Local
public interface BeanCerrarDiaLocal {
    public List<BigDecimal> recorrerPrestamosActivos();
    public List<BigDecimal> recorrerAmortizacionActiva(int idPrestamo);
    public void CerrarDia();
    public boolean CompararFechas(Timestamp fecha);
    
}
