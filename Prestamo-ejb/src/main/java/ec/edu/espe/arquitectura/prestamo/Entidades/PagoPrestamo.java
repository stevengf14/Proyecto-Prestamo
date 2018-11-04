/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Samsung-PC
 */
@Entity
@Table(name = "PAGO_PRESTAMO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagoPrestamo.findAll", query = "SELECT p FROM PagoPrestamo p")
    , @NamedQuery(name = "PagoPrestamo.findById", query = "SELECT p FROM PagoPrestamo p WHERE p.id = :id")
    , @NamedQuery(name = "PagoPrestamo.findByFechaPago", query = "SELECT p FROM PagoPrestamo p WHERE p.fechaPago = :fechaPago")
    , @NamedQuery(name = "PagoPrestamo.findByValorCargos", query = "SELECT p FROM PagoPrestamo p WHERE p.valorCargos = :valorCargos")
    , @NamedQuery(name = "PagoPrestamo.findByValorTotal", query = "SELECT p FROM PagoPrestamo p WHERE p.valorTotal = :valorTotal")
    , @NamedQuery(name = "PagoPrestamo.findByValorPagado", query = "SELECT p FROM PagoPrestamo p WHERE p.valorPagado = :valorPagado")})
public class PagoPrestamo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_PAGO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_CARGOS")
    private BigDecimal valorCargos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_PAGADO")
    private BigDecimal valorPagado;
    @JoinColumn(name = "AMO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Amortizacion amoId;

    public PagoPrestamo() {
    }

    public PagoPrestamo(BigDecimal id) {
        this.id = id;
    }

    public PagoPrestamo(BigDecimal id, Date fechaPago, BigDecimal valorCargos, BigDecimal valorTotal, BigDecimal valorPagado) {
        this.id = id;
        this.fechaPago = fechaPago;
        this.valorCargos = valorCargos;
        this.valorTotal = valorTotal;
        this.valorPagado = valorPagado;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public BigDecimal getValorCargos() {
        return valorCargos;
    }

    public void setValorCargos(BigDecimal valorCargos) {
        this.valorCargos = valorCargos;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(BigDecimal valorPagado) {
        this.valorPagado = valorPagado;
    }

    public Amortizacion getAmoId() {
        return amoId;
    }

    public void setAmoId(Amortizacion amoId) {
        this.amoId = amoId;
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
        if (!(object instanceof PagoPrestamo)) {
            return false;
        }
        PagoPrestamo other = (PagoPrestamo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.prestamo.Entidades.PagoPrestamo[ id=" + id + " ]";
    }
    
}
