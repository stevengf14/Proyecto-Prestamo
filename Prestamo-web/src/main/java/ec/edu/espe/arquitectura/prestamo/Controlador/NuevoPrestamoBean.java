/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Controlador;

import ec.edu.espe.arquitectura.prestamo.Entidades.Cliente;
import ec.edu.espe.arquitectura.prestamo.Entidades.Tabla_Amortizacion;
import ec.edu.espe.arquitectura.prestamo.Entidades.Total;
import ec.edu.espe.arquitectura.prestamo.Modelo.Bean_NuevoPrestamoLocal;
import ec.edu.espe.arquitectura.prestamo.Modelo.Bean_Usuarios;
import ec.edu.espe.arquitectura.prestamo.util.FacesUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Steven
 */
@ManagedBean
@ViewScoped
public class NuevoPrestamoBean implements Serializable {

    private String cedula;
    private String tipo;
    private double monto;
    private int plazo;
    private ArrayList<Tabla_Amortizacion> amortizacion = new ArrayList<Tabla_Amortizacion>();
    private ArrayList<Total> lista_total = new ArrayList<Total>();
    private boolean confirmacion;
    private boolean detalle;
    private String fechaCreacion;
    private String fechaConcesion;
    private String fechaDesembolso;
    private double montoFinal;
    private double coutas;
    private double comision;
    private int numPrestamo;
    private boolean prestamo;
    private boolean detallePrestamo;
    @EJB
    Bean_NuevoPrestamoLocal bean_nuevoPrestamo;

    Cliente cli = new Cliente();

    public ArrayList<Total> getLista_total() {
        return lista_total;
    }

    public void setLista_total(ArrayList<Total> lista_total) {
        this.lista_total = lista_total;
    }

    public NuevoPrestamoBean() {
        this.confirmacion = true;
        this.detalle = true;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public ArrayList<Tabla_Amortizacion> getAmortizacion() {
        return amortizacion;
    }

    public void setAmortizacion(ArrayList<Tabla_Amortizacion> amortizacion) {
        this.amortizacion = amortizacion;
    }

    public boolean isConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(boolean confirmacion) {
        this.confirmacion = confirmacion;
    }

    public boolean isDetalle() {
        return detalle;
    }

    public void setDetalle(boolean detalle) {
        this.detalle = detalle;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaConcesion() {
        return fechaConcesion;
    }

    public void setFechaConcesion(String fechaConcesion) {
        this.fechaConcesion = fechaConcesion;
    }

    public String getFechaDesembolso() {
        return fechaDesembolso;
    }

    public void setFechaDesembolso(String fechaDesembolso) {
        this.fechaDesembolso = fechaDesembolso;
    }

    public double getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(double montoFinal) {
        this.montoFinal = montoFinal;
    }

    public double getCoutas() {
        return coutas;
    }

    public void setCoutas(double coutas) {
        this.coutas = coutas;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public int getNumPrestamo() {
        return numPrestamo;
    }

    public void setNumPrestamo(int numPrestamo) {
        this.numPrestamo = numPrestamo;
    }

    public boolean isPrestamo() {
        return prestamo;
    }

    public void setPrestamo(boolean prestamo) {
        this.prestamo = prestamo;
    }

    public boolean isDetallePrestamo() {
        return detallePrestamo;
    }

    public void setDetallePrestamo(boolean detallePrestamo) {
        this.detallePrestamo = detallePrestamo;
    }

    public boolean aceptar() {
        cli = bean_nuevoPrestamo.verificarCliente(cedula);
        amortizacion.clear();
        if (cli != null) {
            if (bean_nuevoPrestamo.verificarTipoPrestamoCliente(cedula, tipo)) {
                if (bean_nuevoPrestamo.validarMonto(tipo, monto)) {
                    if (bean_nuevoPrestamo.validarPlazo(tipo, plazo)) {
                        CargarTabla();
                        return true;
                    } else {
                        FacesUtil.addMessageWarn(null, bean_nuevoPrestamo.mensajePlazo(tipo));
                        return false;
                    }
                } else {
                    FacesUtil.addMessageWarn(null, bean_nuevoPrestamo.mensajeMonto(tipo));
                    return false;
                }
            } else {
                if (tipo.equals("Comercial")) {
                    FacesUtil.addMessageWarn(null, "Únicamente los clientes jurídicos pueden acceder a prétamos comerciales");
                } else {
                    FacesUtil.addMessageWarn(null, "Los clientes jurídicos pueden acceder únicamente a préstamos comerciales");
                }
                return false;
            }
        } else {
            FacesUtil.addMessageWarn(null, "El cliente no existe");
            return false;
        }

    }

    public void CargarTabla() {
        lista_total.clear();
        Tabla_Amortizacion ta;
        double interes_anual = 16.06;
        double interes_mensual = interes_anual / 12 / 100;
        double interes = 0;
        double valor_cuota = monto * (interes_mensual * Math.pow(1 + interes_mensual, plazo)) / (Math.pow(interes_mensual + 1, plazo) - 1);
        double capital = 0;
        double saldo = monto;
        List<String> lista_fecha = bean_nuevoPrestamo.GenerarFechas(plazo);
        for (int i = 0; i <= plazo; i++) {

            if (i == 0) {
                ta = new Tabla_Amortizacion(i, 0, 0, 0, bean_nuevoPrestamo.Convertir(saldo), lista_fecha.get(i), "");
            } else {
                interes = saldo * ((16.06 / 12) / 100);
                capital = valor_cuota - interes;
                saldo = saldo - capital;
                ta = new Tabla_Amortizacion(i, bean_nuevoPrestamo.Convertir(capital), bean_nuevoPrestamo.Convertir(interes), bean_nuevoPrestamo.Convertir(capital + interes), bean_nuevoPrestamo.Convertir(saldo), lista_fecha.get(i), "Pendiente");
            }
            amortizacion.add(ta);
        }
        Total tot = new Total("Total", bean_nuevoPrestamo.Convertir(monto), bean_nuevoPrestamo.Convertir(monto * interes_anual / 100), bean_nuevoPrestamo.Convertir(valor_cuota * plazo), bean_nuevoPrestamo.Convertir(saldo), "", "");
        lista_total.add(tot);
    }

    public String aceptarDetalle() {
        return "ConfirmacionPrestamo";
    }

    public void mostrarConfirmacion() {
        this.confirmacion = true;
        this.detalle = false;

    }

    public void calculosConfirmacion() {
        Date fecha = new Date();
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        this.fechaCreacion = format.format(fecha);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        this.fechaConcesion = format.format(calendar.getTime());

        Integer sizefechas = amortizacion.size() - 1;
        this.fechaDesembolso = amortizacion.get(sizefechas).getFecha_amortizacion();

        double com = (monto * 0.15);
        this.comision = com;
        this.montoFinal = this.monto - com;
        this.coutas = amortizacion.get(sizefechas).getValor_cuota();
        this.numPrestamo = bean_nuevoPrestamo.ExtraerNumPrestamo();
    }

    public String guardarPrestamo() {
        System.out.println("datos: " + numPrestamo + ", " + fechaConcesion + "," + monto);
        bean_nuevoPrestamo.insertarPrestamo(numPrestamo + "", bean_nuevoPrestamo.EncontrarClienteId(cedula) + "", bean_nuevoPrestamo.EncontrarIdPrestamo(tipo) + "", this.fechaCreacion, this.fechaConcesion, this.fechaDesembolso, this.monto + "", this.plazo + "", "16.06", "0.15", this.montoFinal + "", "act");
        for (int i = 1; i < amortizacion.size(); i++) {
            bean_nuevoPrestamo.InsertarAmortizacion(numPrestamo, amortizacion.get(i).getCapital(), amortizacion.get(i).getInteres(), amortizacion.get(i).getValor_cuota(), amortizacion.get(i).getFecha_amortizacion(), amortizacion.get(i).getEstado(), amortizacion.get(i).getNumero(), amortizacion.get(i).getCapital());
        }
        return "NuevoPrestamo";
    }

    public String onFlowProcess(FlowEvent event) {
        if (event.getNewStep().equals("detalle")) {
            if (aceptar()) {
                
                return event.getNewStep();
            } else {
                //FacesUtil.addMessageWarn(null, "Los clientes jurídicos pueden acceder únicamente a préstamos comerciales");
                return event.getOldStep();
            }
        } else if (event.getNewStep().equals("confirmacion")) {
            System.out.println(this.amortizacion.size());
            calculosConfirmacion();
            return event.getNewStep();
        } else {
            return event.getNewStep();
        }
//        if (prestamo) {
////            prestamo = false;   //reset in case user goes back
//            aceptar();
//            return "detalle";
//        } else if (detalle) {
//            System.out.println(this.amortizacion.size());
//            calculosConfirmacion();
//            return  "confirmacion";
//        } else {
//            return event.getNewStep();
//        }
    }
}
