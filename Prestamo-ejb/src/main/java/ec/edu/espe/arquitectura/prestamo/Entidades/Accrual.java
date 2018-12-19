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
@Table(name = "ACCRUAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accrual.findAll", query = "SELECT a FROM Accrual a")
    , @NamedQuery(name = "Accrual.findById", query = "SELECT a FROM Accrual a WHERE a.id = :id")
    , @NamedQuery(name = "Accrual.findByAmoId", query = "SELECT a FROM Accrual a WHERE a.amoId = :amoId")
    , @NamedQuery(name = "Accrual.findByFecha", query = "SELECT a FROM Accrual a WHERE a.fecha = :fecha")
    , @NamedQuery(name = "Accrual.findByValor", query = "SELECT a FROM Accrual a WHERE a.valor = :valor")
    , @NamedQuery(name = "Accrual.findByEstado", query = "SELECT a FROM Accrual a WHERE a.estado = :estado")})
public class Accrual implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMO_ID")
    private BigInteger amoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ESTADO")
    private String estado;
    @JoinColumn(name = "PRE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Prestamo preId;

    public Accrual() {
    }

    public Accrual(BigDecimal id) {
        this.id = id;
    }

    public Accrual(BigDecimal id, BigInteger amoId, Date fecha, BigDecimal valor, String estado) {
        this.id = id;
        this.amoId = amoId;
        this.fecha = fecha;
        this.valor = valor;
        this.estado = estado;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getAmoId() {
        return amoId;
    }

    public void setAmoId(BigInteger amoId) {
        this.amoId = amoId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
        if (!(object instanceof Accrual)) {
            return false;
        }
        Accrual other = (Accrual) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.prestamo.Entidades.Accrual[ id=" + id + " ]";
    }
    
}
