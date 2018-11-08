/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Modelo;

import ec.edu.espe.arquitectura.prestamo.Entidades.Cliente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Steven
 */
@Local
public interface Bean_NuevoPrestamoLocal {
    public Cliente verificarCliente(String cedula);
    public boolean verificarTipoPrestamoCliente(String cedula, String TipoPrestamo);
    public int EncontrarIdPrestamo(String TipoPrestamo);
    public boolean validarMonto(String TipoPrestamo, double monto);
    public String mensajeMonto(String TipoPrestamo);
    public boolean validarPlazo(String TipoPrestamo, int plazo);
    public String mensajePlazo(String TipoPrestamo);
    public double Convertir(double num);
    public List<String> GenerarFechas(int plazoPrestamo);
}
