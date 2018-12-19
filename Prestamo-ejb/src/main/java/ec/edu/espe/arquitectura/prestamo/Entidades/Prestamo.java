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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Steven
 */
@Entity
@Table(name = "PRESTAMO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestamo.findAll", query = "SELECT p FROM Prestamo p")
    , @NamedQuery(name = "Prestamo.findById", query = "SELECT p FROM Prestamo p WHERE p.id = :id")
    , @NamedQuery(name = "Prestamo.findByFechaCreacion", query = "SELECT p FROM Prestamo p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Prestamo.findByFechaConsecion", query = "SELECT p FROM Prestamo p WHERE p.fechaConsecion = :fechaConsecion")
    , @NamedQuery(name = "Prestamo.findByFechaDesembolso", query = "SELECT p FROM Prestamo p WHERE p.fechaDesembolso = :fechaDesembolso")
    , @NamedQuery(name = "Prestamo.findByMonto", query = "SELECT p FROM Prestamo p WHERE p.monto = :monto")
    , @NamedQuery(name = "Prestamo.findByPlazo", query = "SELECT p FROM Prestamo p WHERE p.plazo = :plazo")
    , @NamedQuery(name = "Prestamo.findByInteres", query = "SELECT p FROM Prestamo p WHERE p.interes = :interes")
    , @NamedQuery(name = "Prestamo.findByValorComision", query = "SELECT p FROM Prestamo p WHERE p.valorComision = :valorComision")
    , @NamedQuery(name = "Prestamo.findByValorFinal", query = "SELECT p FROM Prestamo p WHERE p.valorFinal = :valorFinal")
    , @NamedQuery(name = "Prestamo.findBySaldo", query = "SELECT p FROM Prestamo p WHERE p.saldo = :saldo")
    , @NamedQuery(name = "Prestamo.findByEstado", query = "SELECT p FROM Prestamo p WHERE p.estado = :estado")})
public class Prestamo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FECHA_CONSECION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaConsecion;
    @Column(name = "FECHA_DESEMBOLSO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDesembolso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO")
    private BigDecimal monto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PLAZO")
    private BigInteger plazo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTERES")
    private BigDecimal interes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_COMISION")
    private BigDecimal valorComision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_FINAL")
    private BigDecimal valorFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO")
    private BigDecimal saldo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "ESTADO")
    private String estado;
    @JoinColumn(name = "CLI_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Cliente cliId;
    @JoinColumn(name = "PRO_ID", referencedColumnName = "PRO_ID")
    @ManyToOne(optional = false)
    private Producto proId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preId")
    private List<Accrual> accrualList;
    @OneToMany(mappedBy = "preId")
    private List<Cargo> cargoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preId")
    private List<Amortizacion> amortizacionList;

    public Prestamo() {
    }

    public Prestamo(BigDecimal id) {
        this.id = id;
    }

    public Prestamo(BigDecimal id, Date fechaCreacion, BigDecimal monto, BigInteger plazo, BigDecimal interes, BigDecimal valorComision, BigDecimal valorFinal, BigDecimal saldo, String estado) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.monto = monto;
        this.plazo = plazo;
        this.interes = interes;
        this.valorComision = valorComision;
        this.valorFinal = valorFinal;
        this.saldo = saldo;
        this.estado = estado;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaConsecion() {
        return fechaConsecion;
    }

    public void setFechaConsecion(Date fechaConsecion) {
        this.fechaConsecion = fechaConsecion;
    }

    public Date getFechaDesembolso() {
        return fechaDesembolso;
    }

    public void setFechaDesembolso(Date fechaDesembolso) {
        this.fechaDesembolso = fechaDesembolso;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigInteger getPlazo() {
        return plazo;
    }

    public void setPlazo(BigInteger plazo) {
        this.plazo = plazo;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public BigDecimal getValorComision() {
        return valorComision;
    }

    public void setValorComision(BigDecimal valorComision) {
        this.valorComision = valorComision;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliId() {
        return cliId;
    }

    public void setCliId(Cliente cliId) {
        this.cliId = cliId;
    }

    public Producto getProId() {
        return proId;
    }

    public void setProId(Producto proId) {
        this.proId = proId;
    }

    @XmlTransient
    public List<Accrual> getAccrualList() {
        return accrualList;
    }

    public void setAccrualList(List<Accrual> accrualList) {
        this.accrualList = accrualList;
    }

    @XmlTransient
    public List<Cargo> getCargoList() {
        return cargoList;
    }

    public void setCargoList(List<Cargo> cargoList) {
        this.cargoList = cargoList;
    }

    @XmlTransient
    public List<Amortizacion> getAmortizacionList() {
        return amortizacionList;
    }

    public void setAmortizacionList(List<Amortizacion> amortizacionList) {
        this.amortizacionList = amortizacionList;
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
        if (!(object instanceof Prestamo)) {
            return false;
        }
        Prestamo other = (Prestamo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.prestamo.Entidades.Prestamo[ id=" + id + " ]";
    }
    
}
