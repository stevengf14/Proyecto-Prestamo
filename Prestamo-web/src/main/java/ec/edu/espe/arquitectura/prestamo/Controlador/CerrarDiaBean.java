/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Controlador;

import ec.edu.espe.arquitectura.prestamo.Modelo.BeanCerrarDiaLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Steven
 */
@ManagedBean
@Dependent
public class CerrarDiaBean {
    
    @EJB
    BeanCerrarDiaLocal beanCerrarDia;
    public CerrarDiaBean() {
    }
    
    public void cerrarDia()
    {
        beanCerrarDia.CerrarDia();
    }
}
