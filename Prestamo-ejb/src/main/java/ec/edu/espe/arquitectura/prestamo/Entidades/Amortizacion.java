/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Steven
 */
@Entity
@Table(name = "AMORTIZACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Amortizacion.findAll", query = "SELECT a FROM Amortizacion a")
    , @NamedQuery(name = "Amortizacion.findById", query = "SELECT a FROM Amortizacion a WHERE a.id = :id")
    , @NamedQuery(name = "Amortizacion.findByCapital", query = "SELECT a FROM Amortizacion a WHERE a.capital = :capital")
    , @NamedQuery(name = "Amortizacion.findByInteres", query = "SELECT a FROM Amortizacion a WHERE a.interes = :interes")
    , @NamedQuery(name = "Amortizacion.findByValorCuota", query = "SELECT a FROM Amortizacion a WHERE a.valorCuota = :valorCuota")
    , @NamedQuery(name = "Amortizacion.findByFechaAmortizacion", query = "SELECT a FROM Amortizacion a WHERE a.fechaAmortizacion = :fechaAmortizacion")
    , @NamedQuery(name = "Amortizacion.findByEstado", query = "SELECT a FROM Amortizacion a WHERE a.estado = :estado")
    , @NamedQuery(name = "Amortizacion.findByNumero", query = "SELECT a FROM Amortizacion a WHERE a.numero = :numero")
    , @NamedQuery(name = "Amortizacion.findBySaldo", query = "SELECT a FROM Amortizacion a WHERE a.saldo = :saldo")})
public class Amortizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAPITAL")
    private BigDecimal capital;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTERES")
    private BigDecimal interes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_CUOTA")
    private BigDecimal valorCuota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_AMORTIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAmortizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "NUMERO")
    private BigInteger numero;
    @Column(name = "SALDO")
    private BigDecimal saldo;
    @JoinColumn(name = "PRE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Prestamo preId;

    public Amortizacion() {
    }

    public Amortizacion(BigDecimal id) {
        this.id = id;
    }

    public Amortizacion(BigDecimal id, BigDecimal capital, BigDecimal interes, BigDecimal valorCuota, Date fechaAmortizacion, String estado) {
        this.id = id;
        this.capital = capital;
        this.interes = interes;
        this.valorCuota = valorCuota;
        this.fechaAmortizacion = fechaAmortizacion;
        this.estado = estado;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public BigDecimal getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(BigDecimal valorCuota) {
        this.valorCuota = valorCuota;
    }

    public Date getFechaAmortizacion() {
        return fechaAmortizacion;
    }

    public void setFechaAmortizacion(Date fechaAmortizacion) {
        this.fechaAmortizacion = fechaAmortizacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigInteger getNumero() {
        return numero;
    }

    public void setNumero(BigInteger numero) {
        this.numero = numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Prestamo getPreId() {
        return preId;
    }

    public void setPreId(Prestamo preId) {
        this.preId = preId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Amortizacion)) {
            return false;
        }
        Amortizacion other = (Amortizacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.prestamo.Entidades.Amortizacion[ id=" + id + " ]";
    }
    
}
