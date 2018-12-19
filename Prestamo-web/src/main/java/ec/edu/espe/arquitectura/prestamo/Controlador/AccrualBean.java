/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Controlador;

import ec.edu.espe.arquitectura.prestamo.Entidades.Accrual;
import ec.edu.espe.arquitectura.prestamo.Entidades.Cliente;
import ec.edu.espe.arquitectura.prestamo.Entidades.TablaAccrual;
import ec.edu.espe.arquitectura.prestamo.Modelo.Bean_Accrual;
import ec.edu.espe.arquitectura.prestamo.Modelo.Bean_AccrualLocal;
import ec.edu.espe.arquitectura.prestamo.util.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Steven
 */
@ManagedBean
@SessionScoped
public class AccrualBean implements Serializable{

    @EJB
    Bean_AccrualLocal accrualBean;
    Cliente cli = new Cliente();

    private String nombre;
    private String cedula;
    List<Accrual> listaAccrual = new ArrayList<Accrual>();
    private int amo_id;
    private Date fecha;
    private double valor;
    private String estado;
    private double valorCuota;
    private Date fechaPago;
    List<TablaAccrual> lista = new ArrayList<TablaAccrual>();

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public List<Accrual> getListaAccrual() {
        return listaAccrual;
    }

    public void setListaAccrual(List<Accrual> listaAccrual) {
        this.listaAccrual = listaAccrual;
    }

    public int getAmo_id() {
        return amo_id;
    }

    public void setAmo_id(int amo_id) {
        this.amo_id = amo_id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(double valorCuota) {
        this.valorCuota = valorCuota;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public List<TablaAccrual> getLista() {
        return lista;
    }

    public void setLista(List<TablaAccrual> lista) {
        this.lista = lista;
    }

    /**
     * Creates a new instance of PagosBean
     */
    public AccrualBean() {

    }

    public void cargarTabla() {
        listaAccrual.clear();
        cli = accrualBean.verificarCliente(cedula);
        if (cli != null) {
            nombre = cli.getNombre();
            listaAccrual = accrualBean.cargarTablaAccrual(cli.getId().intValue());
            System.out.println("id:    asdad             : "+listaAccrual.size());
            valorCuota = accrualBean.verificarCuota(cli.getId().intValue());
            for (int i = 0; i < listaAccrual.size(); i++) {
                TablaAccrual ta = new TablaAccrual();
                ta.setAmo_id(listaAccrual.get(i).getAmoId().intValue());
                ta.setFecha(listaAccrual.get(i).getFecha());
                ta.setValor(listaAccrual.get(i).getValor().doubleValue());
                ta.setEstado(listaAccrual.get(i).getEstado());
                ta.setValorCuota(valorCuota);
                ta.setFechaPago(accrualBean.retornarFecha(listaAccrual.get(i).getAmoId().intValue()));
                lista.add(ta);
                
            }
        } else {
            FacesUtil.addMessageWarn(null, "El cliente no existe");

        }

    }

}
