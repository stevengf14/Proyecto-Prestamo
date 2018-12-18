/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
    , @NamedQuery(name = "Accrual.findByPreId", query = "SELECT a FROM Accrual a WHERE a.preId = :preId")
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
    @Column(name = "PRE_ID")
    private BigInteger preId;
    @Column(name = "AMO_ID")
    private BigInteger amoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FECHA")
    private String fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "VALOR")
    private String valor;
    @Size(max = 30)
    @Column(name = "ESTADO")
    private String estado;
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Prestamo prestamo;

    public Accrual() {
    }

    public Accrual(BigDecimal id) {
        this.id = id;
    }

    public Accrual(BigDecimal id, BigInteger preId, String fecha, String valor) {
        this.id = id;
        this.preId = preId;
        this.fecha = fecha;
        this.valor = valor;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getPreId() {
        return preId;
    }

    public void setPreId(BigInteger preId) {
        this.preId = preId;
    }

    public BigInteger getAmoId() {
        return amoId;
    }

    public void setAmoId(BigInteger amoId) {
        this.amoId = amoId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
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
