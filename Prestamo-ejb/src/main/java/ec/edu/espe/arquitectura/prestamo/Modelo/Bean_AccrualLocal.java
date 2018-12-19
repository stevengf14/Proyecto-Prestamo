/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Modelo;

import ec.edu.espe.arquitectura.prestamo.Entidades.Accrual;
import ec.edu.espe.arquitectura.prestamo.Entidades.Cliente;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Steven
 */
@Local
public interface Bean_AccrualLocal {

    public Cliente verificarCliente(String cedula);

    public List<Accrual> cargarTablaAccrual(int cli_id);

    public double verificarCuota(int cli);

    public Date retornarFecha(int amo_id);
}
