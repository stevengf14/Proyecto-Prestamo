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
    
    public boolean verificarPrestamoPorCliente(String cedula);
    
    public boolean verificarTipoPrestamoCliente(String cedula, String TipoPrestamo);

    public int EncontrarIdPrestamo(String TipoPrestamo);

    public boolean validarMonto(String TipoPrestamo, double monto);

    public String mensajeMonto(String TipoPrestamo);

    public boolean validarPlazo(String TipoPrestamo, int plazo);

    public String mensajePlazo(String TipoPrestamo);

    public double Convertir(double num);

    public List<String> GenerarFechas(int plazoPrestamo);

    public void insertarPrestamo(String id, String cli, String tiPre, String fecCre, String fecCon, String fecDese, String monPres, String pla, String inte, String valComi, String monFin, String estado);

    public int ExtraerNumPrestamo();

    public int EncontrarClienteId(String cedula);

    public int ExtraerNumAmortizacion();

    public boolean InsertarAmortizacion(int pre_id, double capital, double interes, double valor_cuota, String fecha, String estado, int numero, double saldo);
    
    public List<String> cargarListaPrestamos();
    
    public double encontrarInteres(String tiPre);
}
