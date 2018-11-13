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
            em1.getTransaction().begin();
            Query q = em1.createNativeQuery("SELECT ID FROM PRESTAMO WHERE ESTADO='act'");
            listaPrestamos = q.getResultList();
            em1.getTransaction().commit();
        } catch (Exception ex) {
            listaPrestamos = null;
        }
        //em1.close();
        //factory.close();
        return listaPrestamos;
    }

    public List<BigDecimal> recorrerAmortizacionActiva(int idPrestamo) {
        List<BigDecimal> listAmortizacion = new ArrayList<BigDecimal>();
        try {
            em1.getTransaction().begin();
            Query q = em1.createNativeQuery("SELECT ID FROM AMORTIZACION WHERE (ESTADO='Pendiente' OR ESTADO='Mora') AND PRE_ID=" + idPrestamo + "");
            listAmortizacion = q.getResultList();
            em1.getTransaction().commit();
        } catch (Exception ex) {
            listAmortizacion = null;
        }
        //em1.close();
        //factory.close();
        return listAmortizacion;
    }

    public List<BigDecimal> recorrerAmortizacionMora(int idPrestamo) {
        List<BigDecimal> listAmortizacion = new ArrayList<BigDecimal>();
        try {
            em1.getTransaction().begin();
            Query q = em1.createNativeQuery("SELECT ID FROM AMORTIZACION WHERE (ESTADO='Mora') AND PRE_ID=" + idPrestamo + "");
            listAmortizacion = q.getResultList();
            em1.getTransaction().commit();
        } catch (Exception ex) {
            listAmortizacion = null;
        }
        for (int i = 0; i < listAmortizacion.size(); i++) {
            System.out.print(listAmortizacion.get(i));
        }
        //em1.close();
        //factory.close();
        return listAmortizacion;
    }

    public void ActualizarAmortizacion() {
        List<BigDecimal> listAmortizacion = new ArrayList<BigDecimal>();
        List<BigDecimal> listaPrestamos = recorrerPrestamosActivos();
        List<Timestamp> listaFechas = new ArrayList<Timestamp>();
        for(int i=0;i<listaPrestamos.size();i++)
        {
            System.out.println(listaPrestamos.get(i).toString());
        }
        for (int i = 0; i < listaPrestamos.size(); i++) {
            listAmortizacion = recorrerAmortizacionActiva(listaPrestamos.get(i).intValue());
            for (int j = 0; j < listAmortizacion.size(); j++) {
                try {
                    em1.getTransaction().begin();
                    //System.out.println(5+" ");
                    Query q1 = em1.createNativeQuery("SELECT FECHA_AMORTIZACION FROM AMORTIZACION WHERE ID=" + listAmortizacion.get(j) + "");
                    listaFechas = q1.getResultList();
                    em1.getTransaction().commit();
                    if (CompararFechas(listaFechas.get(0))) {
                        actualizarAmortizacion(listAmortizacion.get(j).intValue());
                        //insertar(listaPrestamos.get(i).intValue(), listAmortizacion.get(j).intValue(), 550);

                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            listAmortizacion.clear();
        }

        //em1.close();
        //factory.close();
    }

    public void CerrarDia() {
        ActualizarAmortizacion();
        List<BigDecimal> listaPrestamos = recorrerPrestamosActivos();
        List<BigDecimal> listaAmortizacionMora = new ArrayList<BigDecimal>();
        List<BigDecimal> listaCargoInsertar = new ArrayList<BigDecimal>();
        List<BigDecimal> listaCargoActualizar = new ArrayList<BigDecimal>();
        long diasMora = 0;
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
                    listaFechas = extraerFechas(listaAmortizacionMora.get(j).intValue());
                    diasMora = (-1) * DiferenciaFechas(listaFechas.get(0));
                    em1.getTransaction().begin();
                    Query q1 = em1.createNativeQuery("SELECT SALDO FROM AMORTIZACION WHERE ID=" + listaAmortizacionMora.get(j) + "");
                    listaSaldo = q1.getResultList();
                    em1.getTransaction().commit();
                    saldo = listaSaldo.get(0).doubleValue();
                    interes = saldo * (16.06 / 100) / 360 * diasMora;
                    if (diasMora <= 15) {
                        valor_mora = saldo * (5 / 100) / 360 * diasMora;
                    } else if (diasMora > 15 && diasMora <= 30) {
                        valor_mora = saldo * (7 / 100) / 360 * diasMora;
                    } else if (diasMora > 31 && diasMora <= 60) {
                        valor_mora = saldo * (9 / 100) / 360 * diasMora;
                    } else if (diasMora > 61 && diasMora <= 120) {
                        valor_mora = saldo * (10 / 100) / 360 * diasMora;
                    }
                    valor_final = valor_mora + interes;
                    if (ExtraerCargos(listaAmortizacionMora.get(j).intValue())) {
                        actualizar(listaAmortizacionMora.get(j).intValue(), Convertir(valor_final));
                    } else {
                        insertar(listaPrestamos.get(i).intValue(), listaAmortizacionMora.get(j).intValue(), Convertir(valor_final));
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        em1.close();
        factory.close();

    }

    public boolean ExtraerCargos(int num) {
        boolean val = false;
        List<BigDecimal> listaCargo = new ArrayList<BigDecimal>();
        try {
            em1.getTransaction().begin();
            Query q = em1.createNativeQuery("select amo_id from cargo where amo_id=" + num);
            listaCargo = q.getResultList();
            em1.getTransaction().commit();
            if (listaCargo.get(0).intValue() == num) {
                val = true;
            } else {
                val = false;
            }
        } catch (Exception ex) {
            val = false;
        }
        //em1.close();
        //factory.close();
        return val;
    }

    public List<Timestamp> extraerFechas(int num) {
        List<Timestamp> listaFechas = new ArrayList<Timestamp>();
        try {
            em1.getTransaction().begin();
            Query q1 = em1.createNativeQuery("SELECT FECHA_AMORTIZACION FROM AMORTIZACION WHERE ID=" + num + "");
            listaFechas = q1.getResultList();
            em1.getTransaction().commit();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return listaFechas;
    }

    public long DiferenciaFechas(Timestamp fecha) {
        Timestamp FechaProceso;
        Period periodo = null;
        long days=0;
        List<Timestamp> listaFechaProceso = new ArrayList<Timestamp>();
        try {
            em1.getTransaction().begin();
            Query q = em1.createNativeQuery("SELECT FECHA FROM FECHA_PROCESO WHERE CODIGO=1");
            listaFechaProceso = q.getResultList();
            em1.getTransaction().commit();
            FechaProceso = listaFechaProceso.get(0);
            days = ChronoUnit.DAYS.between(FechaProceso.toLocalDateTime().toLocalDate(), fecha.toLocalDateTime().toLocalDate());
//            periodo = Period.between(FechaProceso.toLocalDateTime().toLocalDate(), fecha.toLocalDateTime().toLocalDate());
            System.out.print("Fecha1: "+fecha.toString()+" Fecha2: "+FechaProceso.toString()+" //Diferencia= " + days);
        } catch (Exception ex) {
            return 0;
        }
        //em1.close();
        //factory.close();

        return days;
    }

    public boolean CompararFechas(Timestamp fecha) {
        Timestamp FechaProceso;
        Period periodo = null;
        List<Timestamp> listaFechaProceso = new ArrayList<Timestamp>();
        try {
            em1.getTransaction().begin();
            Query q = em1.createNativeQuery("SELECT FECHA FROM FECHA_PROCESO WHERE CODIGO=1");
            listaFechaProceso = q.getResultList();
            em1.getTransaction().commit();
            FechaProceso = listaFechaProceso.get(0);
            periodo = Period.between(FechaProceso.toLocalDateTime().toLocalDate(), fecha.toLocalDateTime().toLocalDate());
            //System.out.print("Diferencia= " + periodo.getDays());
        } catch (Exception ex) {
            return false;
        }
        //em1.close();
        //factory.close();

        return periodo.isNegative();
    }

    public void insertar(int id_prestamo, int id_amortizacion, double valor) {
        int num = ExtraerNumCargo();
        try {
            
            em1.getTransaction().begin();
            Query q2 = em1.createNativeQuery("INSERT INTO CARGO VALUES(" + num + "," + id_prestamo + ",1," + id_amortizacion + "," + valor + ",'Activo')");
            q2.executeUpdate();
            em1.getTransaction().commit();
        } catch (Exception e) {
            em1.getTransaction().commit();
            System.out.println(e.getMessage());
        }
    }

    public void actualizar(int id_amortizacion, double valor) {
        try {
            em1.getTransaction().begin();
            Query q2 = em1.createNativeQuery("UPDATE CARGO SET VALOR=" + valor + " WHERE AMO_ID=" + id_amortizacion);
            q2.executeUpdate();
            em1.getTransaction().commit();
        } catch (Exception e) {
            em1.getTransaction().commit();
            System.out.println(e.getMessage());
        }
    }

    public void actualizarAmortizacion(int id_amortizacion) {
        try {
            em1.getTransaction().begin();
            Query q2 = em1.createNativeQuery("UPDATE AMORTIZACION SET ESTADO='Mora' WHERE ID=" + id_amortizacion);
            q2.executeUpdate();
            em1.getTransaction().commit();
        } catch (Exception e) {
            em1.getTransaction().commit();
            System.out.println(e.getMessage());
        }
    }

    public int ExtraerNumCargo() {
        int num;
        List<BigDecimal> idList = new ArrayList<BigDecimal>();
        try {
            em1.getTransaction().begin();
            Query q = em1.createNativeQuery("SELECT MAX(ID) FROM CARGO");
            idList = q.getResultList();
            em1.getTransaction().commit();
            num = idList.get(0).intValue() + 1;
        } catch (Exception ex) {
            num = 1;
        }
        //em1.close();
        //factory.close();
        return num;
    }

    public double Convertir(double num) {
        int places = 2;
        BigDecimal bd = new BigDecimal(num);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }
}
