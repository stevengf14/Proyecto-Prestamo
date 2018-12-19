/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Modelo;

import ec.edu.espe.arquitectura.prestamo.Entidades.Cliente;
import ec.edu.espe.arquitectura.prestamo.Entidades.Usuario;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
public class Bean_NuevoPrestamo implements Bean_NuevoPrestamoLocal {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("ec.edu.espe.arquitectura_Prestamo-ejb_ejb_1PU");
    EntityManager em1 = factory.createEntityManager();

    public Cliente verificarCliente(String cedula) {
        boolean val = false;
        Cliente cli = new Cliente();
        List<String> cedulaList = new ArrayList<String>();
        List<String> nombre = new ArrayList<String>();
        try {
            Query q = em1.createNativeQuery("SELECT CEDULA FROM CLIENTE WHERE CEDULA='" + cedula + "'");
            Query q2 = em1.createNativeQuery("SELECT NOMBRE FROM CLIENTE WHERE CEDULA='" + cedula + "'");
            cedulaList = q.getResultList();
            nombre = q2.getResultList();
            if (cedula.equals(cedulaList.get(0))) {
                cli.setCedula(cedulaList.get(0));
                cli.setNombre(nombre.get(0));
            } else {
                cli = null;
            }
        } catch (Exception ex) {
            cli = null;
        }
        return cli;
    }

    public boolean verificarPrestamoPorCliente(String cedula) {
        boolean val = false;
        List<String> cedulaList = new ArrayList<String>();
        List<BigDecimal> idList = new ArrayList<BigDecimal>();
        try {
            Query q = em1.createNativeQuery("SELECT ID FROM CLIENTE WHERE CEDULA='" + cedula + "'");
            idList = q.getResultList();
            Query q1 = em1.createNativeQuery("SELECT CLI_ID FROM PRESTAMO WHERE CLI_ID='" + idList.get(0) + "' AND ESTADO='act'");
            cedulaList = q1.getResultList();
            if (cedulaList.size() >= 1) {
                val = false;
            } else {
                val = true;
            }
        } catch (Exception ex) {
            val = false;
        }
        return val;
    }

    public boolean verificarTipoPrestamoCliente(String cedula, String TipoPrestamo) {
        int id = EncontrarIdPrestamo(TipoPrestamo);
        boolean val = false;
        List<String> tipo_cliente = new ArrayList<String>();
        List<String> tipo_prestamo = new ArrayList<String>();
        try {
            Query q = em1.createNativeQuery("SELECT PRO_TIPO_CLIENTE FROM PRODUCTO WHERE PRO_DESCRIPCION='" + TipoPrestamo + "'");
            tipo_prestamo = q.getResultList();
            Query q2 = em1.createNativeQuery("SELECT TIPO FROM CLIENTE WHERE CEDULA='" + cedula + "'");
            tipo_cliente = q2.getResultList();

            if (tipo_cliente.get(0).equals("Juridico") && tipo_prestamo.get(0).equals("Natural")) {
                val = false;
            } else if (tipo_cliente.get(0).equals("Natural") && tipo_prestamo.get(0).equals("Juridico")) {
                val = false;
            } else {
                val = true;
            }

        } catch (Exception ex) {
            val = false;
        }
        return val;
    }

    public int EncontrarIdPrestamo(String TipoPrestamo) {
        int id = 0;
        List<BigDecimal> idList = new ArrayList<BigDecimal>();
        try {
            Query q = em1.createNativeQuery("SELECT PRO_ID FROM PRODUCTO WHERE PRO_DESCRIPCION='" + TipoPrestamo + "'");
            idList = q.getResultList();
            id = idList.get(0).intValue();
        } catch (Exception ex) {
            //num = 1;
        }
        /*if (TipoPrestamo.equals("Quirografario")) {
            id = 1;
        } else if (TipoPrestamo.equals("Hipotecario")) {
            id = 2;
        } else if (TipoPrestamo.equals("Vehicular")) {
            id = 3;
        } else if (TipoPrestamo.equals("Comercial")) {
            id = 4;
        }*/
        return id;
    }

    public boolean validarMonto(String TipoPrestamo, double monto) {
        int id = EncontrarIdPrestamo(TipoPrestamo);
        boolean val = false;
        List<BigDecimal> montoMin = new ArrayList<BigDecimal>();
        List<BigDecimal> montoMax = new ArrayList<BigDecimal>();
        try {
            Query q = em1.createNativeQuery("SELECT MONTO_MINIMO FROM TIPO_PRODUCTO WHERE ID='" + id + "'");
            Query q2 = em1.createNativeQuery("SELECT MONTO_MAXIMO FROM TIPO_PRODUCTO WHERE ID='" + id + "'");
            montoMin = q.getResultList();
            montoMax = q2.getResultList();

            if (monto >= montoMin.get(0).doubleValue() && monto <= montoMax.get(0).doubleValue()) {
                val = true;
            } else {
                val = false;
            }
        } catch (Exception ex) {
            val = true;
        }
        return val;
    }

    public String mensajeMonto(String TipoPrestamo) {
        int id = EncontrarIdPrestamo(TipoPrestamo);
        String mensaje = "";
        List<Double> montoMin = new ArrayList<Double>();
        List<Double> montoMax = new ArrayList<Double>();
        try {
            Query q = em1.createNativeQuery("SELECT MONTO_MINIMO FROM TIPO_PRODUCTO WHERE ID='" + id + "'");
            Query q2 = em1.createNativeQuery("SELECT MONTO_MAXIMO FROM TIPO_PRODUCTO WHERE ID='" + id + "'");
            montoMin = q.getResultList();
            montoMax = q2.getResultList();
            mensaje = "El monto debe ser entre " + montoMin.get(0) + " y " + montoMax.get(0);
        } catch (Exception ex) {
            mensaje = "";
        }
        return mensaje;
    }

    public boolean validarPlazo(String TipoPrestamo, int plazo) {
        int id = EncontrarIdPrestamo(TipoPrestamo);
        boolean val = false;
        List<BigDecimal> plazoMin = new ArrayList<BigDecimal>();
        List<BigDecimal> plazoMax = new ArrayList<BigDecimal>();
        try {
            Query q = em1.createNativeQuery("SELECT PLAZO_MIN FROM TIPO_PRODUCTO WHERE ID='" + id + "'");
            Query q2 = em1.createNativeQuery("SELECT PLAZO_MAX FROM TIPO_PRODUCTO WHERE ID='" + id + "'");
            plazoMin = q.getResultList();
            plazoMax = q2.getResultList();

            if (plazo >= plazoMin.get(0).doubleValue() && plazo <= plazoMax.get(0).doubleValue()) {
                val = true;
            } else {
                val = false;
            }
        } catch (Exception ex) {
            val = true;
        }
        return val;
    }

    public String mensajePlazo(String TipoPrestamo) {
        int id = EncontrarIdPrestamo(TipoPrestamo);
        String mensaje = "";
        List<BigDecimal> plazoMin = new ArrayList<BigDecimal>();
        List<BigDecimal> plazoMax = new ArrayList<BigDecimal>();
        try {
            Query q = em1.createNativeQuery("SELECT PLAZO_MIN FROM TIPO_PRODUCTO WHERE ID='" + id + "'");
            Query q2 = em1.createNativeQuery("SELECT PLAZO_MAX FROM TIPO_PRODUCTO WHERE ID='" + id + "'");
            plazoMin = q.getResultList();
            plazoMax = q2.getResultList();
            mensaje = "El monto debe ser entre " + plazoMin.get(0) + " y " + plazoMax.get(0);
        } catch (Exception ex) {
            mensaje = "";
        }
        return mensaje;
    }

    public double Convertir(double num) {
        int places = 2;
        BigDecimal bd = new BigDecimal(num);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }

    public List<String> GenerarFechas(int plazoPrestamo) {
        List<String> lista = new ArrayList<String>();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate ahora = LocalDate.now();
        int mes = ahora.getMonthValue();
        int anio = ahora.getYear();
        int dia = ahora.getDayOfMonth();
        if (dia == 29 || dia == 30 || dia == 31) {
            dia = 1;
        }
        lista.add(dia + "-" + mes + "-" + anio);
        for (int i = 0; i < plazoPrestamo; i++) {
            mes = mes + 1;
            if (mes == 13) {
                mes = 1;
                anio = anio + 1;
            }
            lista.add(dia + "-" + mes + "-" + anio);
        }

        return lista;
    }

    public int ExtraerNumPrestamo() {
        int num;

        List<BigDecimal> idList = new ArrayList<BigDecimal>();
        try {
            Query q = em1.createNativeQuery("SELECT MAX(ID) FROM PRESTAMO");
            idList = q.getResultList();
            num = idList.get(0).intValue() + 1;
        } catch (Exception ex) {
            num = 1;
        }
        return num;
    }

    public double encontrarInteres(String tiPre) {
        double interes = 0;
        List<BigDecimal> lista = new ArrayList<BigDecimal>();
        Query q = em1.createNativeQuery("SELECT PRO_INTERES FROM PRODUCTO WHERE PRO_DESCRIPCION='" + tiPre + "'");
        lista = q.getResultList();
        interes = lista.get(0).doubleValue();
        return interes;
    }

    public void insertarPrestamo(String id, String cli, String tiPre, String fecCre, String fecCon, String fecDese, String monPres, String pla, String inte, String valComi, String monFin, String estado) {
        double interes = encontrarInteres(inte);

        em1.joinTransaction();
        Query q = em1.createNativeQuery("INSERT INTO PRESTAMO VALUES (" + id + "," + cli + "," + tiPre + ", '" + fecCre + "', '" + fecCon + "', '" + fecDese + "', " + monPres + ", " + pla + ", " + String.valueOf(interes) + ", " + valComi + ", " + monFin + ", " + monPres + ", '" + estado + "')");
        q.executeUpdate();
    }

    public int EncontrarClienteId(String cedula) {
        int num;
        List<BigDecimal> idList = new ArrayList<BigDecimal>();
        try {
            Query q = em1.createNativeQuery("SELECT ID FROM CLIENTE WHERE CEDULA='" + cedula + "'");
            idList = q.getResultList();
            num = idList.get(0).intValue();
        } catch (Exception ex) {
            num = 1;
        }
        return num;
    }

    public int ExtraerNumAmortizacion() {
        int num;
        List<BigDecimal> idList = new ArrayList<BigDecimal>();
        try {
            Query q = em1.createNativeQuery("SELECT MAX(ID) FROM AMORTIZACION");
            idList = q.getResultList();
            num = idList.get(0).intValue() + 1;
        } catch (Exception ex) {
            num = 1;
        }
        return num;
    }

    public boolean InsertarAmortizacion(int pre_id, double capital, double interes, double valor_cuota, String fecha, String estado, int numero, double saldo) {
        int id = ExtraerNumAmortizacion();
        boolean val = false;
        try {
            em1.joinTransaction();
            Query q = em1.createNativeQuery("INSERT INTO AMORTIZACION VALUES(" + id + "," + pre_id + "," + capital + "," + interes + "," + valor_cuota + ",'" + fecha + "','" + estado + "'," + numero + "," + valor_cuota + ")");
            int num = q.executeUpdate();
            if (num < 0) {
                val = true;
            } else {
                val = false;
            }
        } catch (Exception ex) {
            val = false;
        }
        return val;
    }

    public List<String> cargarListaPrestamos() {
        List<String> lista = new ArrayList<String>();
        Query q = em1.createNativeQuery("SELECT PRO_DESCRIPCION FROM PRODUCTO WHERE PRO_ESTADO='Activo'");
        lista = q.getResultList();
        return lista;
    }
}
