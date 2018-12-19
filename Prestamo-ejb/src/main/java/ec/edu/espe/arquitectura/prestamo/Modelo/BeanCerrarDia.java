/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Steven
 */
@Stateless
public class BeanCerrarDia implements BeanCerrarDiaLocal {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
    EntityManager em1 = factory.createEntityManager();

    public List<BigDecimal> recorrerPrestamosActivos() {
        List<BigDecimal> listaPrestamos = new ArrayList<BigDecimal>();
        try {
            Query q = em1.createNativeQuery("SELECT ID FROM PRESTAMO WHERE ESTADO='act'");
            listaPrestamos = q.getResultList();
        } catch (Exception ex) {
            listaPrestamos = null;
        }
        return listaPrestamos;
    }

    public List<BigDecimal> recorrerAmortizacionActiva(int idPrestamo) {
        List<BigDecimal> listAmortizacion = new ArrayList<BigDecimal>();
        try {
            Query q = em1.createNativeQuery("SELECT ID FROM AMORTIZACION WHERE (ESTADO='Pendiente' OR ESTADO='Mora') AND PRE_ID=" + idPrestamo + "");
            listAmortizacion = q.getResultList();
        } catch (Exception ex) {
            listAmortizacion = null;
        }
        return listAmortizacion;
    }

    public List<BigDecimal> recorrerAmortizacionMora(int idPrestamo) {
        List<BigDecimal> listAmortizacion = new ArrayList<BigDecimal>();
        try {
            Query q = em1.createNativeQuery("SELECT ID FROM AMORTIZACION WHERE (ESTADO='Mora') AND PRE_ID=" + idPrestamo + "");
            listAmortizacion = q.getResultList();
        } catch (Exception ex) {
            listAmortizacion = null;
        }
        return listAmortizacion;
    }

    public void ActualizarAmortizacion() {
        List<BigDecimal> listAmortizacion = new ArrayList<BigDecimal>();
        List<BigDecimal> listaPrestamos = recorrerPrestamosActivos();
        List<Timestamp> listaFechas = new ArrayList<Timestamp>();
        for (int i = 0; i < listaPrestamos.size(); i++) {
        }
        for (int i = 0; i < listaPrestamos.size(); i++) {
            listAmortizacion = recorrerAmortizacionActiva(listaPrestamos.get(i).intValue());
            for (int j = 0; j < listAmortizacion.size(); j++) {
                try {
                    Query q1 = em1.createNativeQuery("SELECT FECHA_AMORTIZACION FROM AMORTIZACION WHERE ID=" + listAmortizacion.get(j) + "");
                    listaFechas = q1.getResultList();
                    if (CompararFechas(listaFechas.get(0))) {
                        actualizarAmortizacion(listAmortizacion.get(j).intValue());
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            listAmortizacion.clear();
        }
    }

    public double encontrar_interes(int id) {
        double interes = 0;
        List<BigDecimal> lista = new ArrayList<BigDecimal>();
        try {
            Query q = em1.createNativeQuery("SELECT INTERES FROM PRESTAMO WHERE ID='" + id + "'");
            lista = q.getResultList();
            interes = lista.get(0).doubleValue();
        } catch (Exception ex) {

        }
        return interes;
    }

    public void CerrarDia() {
        ActualizarAmortizacion();
        List<BigDecimal> listaPrestamos = recorrerPrestamosActivos();
        List<BigDecimal> listaAmortizacionMora = new ArrayList<BigDecimal>();
        List<BigDecimal> listaCargoInsertar = new ArrayList<BigDecimal>();
        List<BigDecimal> listaCargoActualizar = new ArrayList<BigDecimal>();
        boolean val = false;
        double diasMora = 0;
        double saldo = 0;
        double interes = 0;
        double valor_mora = 0;
        double valor_final = 0;
        List<Timestamp> listaFechas = new ArrayList<Timestamp>();
        List<BigDecimal> listaSaldo = new ArrayList<BigDecimal>();
        for (int i = 0; i < listaPrestamos.size(); i++) {
            listaAmortizacionMora = recorrerAmortizacionMora(listaPrestamos.get(i).intValue());
            for (int j = 0; j < listaAmortizacionMora.size(); j++) {
                try {
                    double prestamo_interes = 0;
                    prestamo_interes = encontrar_interes(listaPrestamos.get(i).intValue());
                    listaFechas = extraerFechas(listaAmortizacionMora.get(j).intValue());
                    diasMora = (-1) * DiferenciaFechas(listaFechas.get(0));
                    Query q1 = em1.createNativeQuery("SELECT SALDO FROM AMORTIZACION WHERE ID=" + listaAmortizacionMora.get(j));
                    listaSaldo = q1.getResultList();
                    saldo = listaSaldo.get(0).doubleValue();
                    interes = saldo * (prestamo_interes / 100) / 360 * diasMora;
                    if (diasMora <= 15) {
                        valor_mora = saldo * 0.005 / 360 * diasMora;
                    } else if (diasMora > 15 && diasMora <= 30) {
                        valor_mora = saldo * 0.007 / 360 * diasMora;
                    } else if (diasMora > 31 && diasMora <= 60) {
                        valor_mora = saldo * 0.009 / 360 * diasMora;
                    } else if (diasMora > 61 && diasMora <= 120) {
                        valor_mora = saldo * 0.1 / 360 * diasMora;
                    } else if (diasMora > 120) {
                        interes = obtenerInteres(listaAmortizacionMora.get(j).intValue());
                        valor_mora = obtenerCargo(listaAmortizacionMora.get(j).intValue())-interes;
                    }
                    System.out.println("saldo: " + saldo + "// dias mora: " + diasMora + " // valor_mora: " + valor_mora + " // interes: " + interes);
                    valor_final = valor_mora + interes;
                    if (ExtraerCargos(listaAmortizacionMora.get(j).intValue())) {
                        actualizar(listaAmortizacionMora.get(j).intValue(), Convertir(valor_final));
                        actualizarAccrual(listaAmortizacionMora.get(j).intValue(), Convertir(interes));
                    } else {
                        insertar(listaPrestamos.get(i).intValue(), listaAmortizacionMora.get(j).intValue(), Convertir(valor_final));
                        insertarAccrual(listaPrestamos.get(i).intValue(), listaAmortizacionMora.get(j).intValue(), Convertir(interes));
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    public double obtenerCargo(int amo_id) {
        List<BigDecimal> lista = new ArrayList<BigDecimal>();
        try {
            Query q1 = em1.createNativeQuery("SELECT VALOR FROM CARGO WHERE AMO_ID=" + amo_id);
            lista = q1.getResultList();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista.get(0).doubleValue();
    }

    public double obtenerInteres(int amo_id) {
        List<BigDecimal> lista = new ArrayList<BigDecimal>();
        try {
            Query q1 = em1.createNativeQuery("SELECT VALOR FROM ACCRUAL WHERE AMO_ID=" + amo_id);
            lista = q1.getResultList();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista.get(0).doubleValue();
    }

    public void insertarAccrual(int id_prestamo, int id_amortizacion, double valor) {
        int num = ExtraerNumAccrual();
        try {

            em1.joinTransaction();
            Query q2 = em1.createNativeQuery("INSERT INTO ACCRUAL VALUES(" + num + "," + id_prestamo + "," + id_amortizacion + ",'" + encontrarFechaProceso() + "'," + valor + ",'Activo')");
            q2.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void actualizarAccrual(int id_amortizacion, double valor) {
        try {
            em1.joinTransaction();
            Query q2 = em1.createNativeQuery("UPDATE ACCRUAL SET VALOR=" + valor + ",FECHA='" + encontrarFechaProceso() + "' WHERE AMO_ID=" + id_amortizacion + "AND ESTADO='Activo'");
            q2.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean ExtraerCargos(int num) {
        boolean val = false;
        List<BigDecimal> listaCargo = new ArrayList<BigDecimal>();
        try {
            Query q = em1.createNativeQuery("select amo_id from cargo where amo_id=" + num);
            listaCargo = q.getResultList();
            if (listaCargo.get(0).intValue() == num) {
                val = true;
            } else {
                val = false;
            }
        } catch (Exception ex) {
            val = false;
        }
        return val;
    }

    public List<Timestamp> extraerFechas(int num) {
        List<Timestamp> listaFechas = new ArrayList<Timestamp>();
        try {
            Query q1 = em1.createNativeQuery("SELECT FECHA_AMORTIZACION FROM AMORTIZACION WHERE ID=" + num + "");
            listaFechas = q1.getResultList();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return listaFechas;
    }

    public String encontrarFechaProceso() {
        Timestamp FechaProceso;
        Period periodo = null;
        long days = 0;
        List<Timestamp> listaFechaProceso = new ArrayList<Timestamp>();
        Query q = em1.createNativeQuery("SELECT FECHA FROM FECHA_PROCESO WHERE CODIGO=1");
        listaFechaProceso = q.getResultList();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(listaFechaProceso.get(0).getTime()));
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day + "-" + month + "-" + year;
    }

    public int DiferenciaFechas(Timestamp fecha) {
        Timestamp FechaProceso;
        Period periodo = null;
        long days = 0;
        List<Timestamp> listaFechaProceso = new ArrayList<Timestamp>();
        try {
            Query q = em1.createNativeQuery("SELECT FECHA FROM FECHA_PROCESO WHERE CODIGO=1");
            listaFechaProceso = q.getResultList();
            FechaProceso = listaFechaProceso.get(0);
            days = ChronoUnit.DAYS.between(FechaProceso.toLocalDateTime().toLocalDate(), fecha.toLocalDateTime().toLocalDate());
            System.out.print("Fecha1: " + fecha.toString() + " Fecha2: " + FechaProceso.toString() + " //Diferencia= " + days);
        } catch (Exception ex) {
            return 0;
        }
        return Math.toIntExact(days);
    }

    public boolean CompararFechas(Timestamp fecha) {
        Timestamp FechaProceso;
        Period periodo = null;
        List<Timestamp> listaFechaProceso = new ArrayList<Timestamp>();
        try {
            Query q = em1.createNativeQuery("SELECT FECHA FROM FECHA_PROCESO WHERE CODIGO=1");
            listaFechaProceso = q.getResultList();
            FechaProceso = listaFechaProceso.get(0);
            periodo = Period.between(FechaProceso.toLocalDateTime().toLocalDate(), fecha.toLocalDateTime().toLocalDate());
        } catch (Exception ex) {
            return false;
        }
        return periodo.isNegative();
    }

    public void insertar(int id_prestamo, int id_amortizacion, double valor) {
        int num = ExtraerNumCargo();
        try {

            em1.joinTransaction();
            Query q2 = em1.createNativeQuery("INSERT INTO CARGO VALUES(" + num + "," + id_prestamo + ",1," + id_amortizacion + "," + valor + ",'Activo')");
            q2.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void actualizar(int id_amortizacion, double valor) {
        try {
            em1.joinTransaction();
            Query q2 = em1.createNativeQuery("UPDATE CARGO SET VALOR=" + valor + " WHERE AMO_ID=" + id_amortizacion + "AND ESTADO='Activo'");
            q2.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void actualizarAmortizacion(int id_amortizacion) {
        try {
            em1.joinTransaction();
            Query q2 = em1.createNativeQuery("UPDATE AMORTIZACION SET ESTADO='Mora' WHERE ID=" + id_amortizacion);
            q2.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int ExtraerNumCargo() {
        int num;
        List<BigDecimal> idList = new ArrayList<BigDecimal>();
        try {
            Query q = em1.createNativeQuery("SELECT MAX(ID) FROM CARGO");
            idList = q.getResultList();
            num = idList.get(0).intValue() + 1;
        } catch (Exception ex) {
            num = 1;
        }
        return num;
    }

    public int ExtraerNumAccrual() {
        int num;
        List<BigDecimal> idList = new ArrayList<BigDecimal>();
        try {
            Query q = em1.createNativeQuery("SELECT MAX(ID) FROM ACCRUAL");
            idList = q.getResultList();
            num = idList.get(0).intValue() + 1;
        } catch (Exception ex) {
            num = 1;
        }
        return num;
    }

    public double Convertir(double num) {
        int places = 2;
        BigDecimal bd = new BigDecimal(num);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }
}
