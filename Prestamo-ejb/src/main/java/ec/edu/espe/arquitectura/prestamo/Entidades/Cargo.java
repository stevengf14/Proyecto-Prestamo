/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Steven
 */
@Entity
@Table(name = "CARGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c")
    , @NamedQuery(name = "Cargo.findById", query = "SELECT c FROM Cargo c WHERE c.id = :id")
    , @NamedQuery(name = "Cargo.findByValor", query = "SELECT c FROM Cargo c WHERE c.valor = :valor")
    , @NamedQuery(name = "Cargo.findByEstado", query = "SELECT c FROM Cargo c WHERE c.estado = :estado")})
public class Cargo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ESTADO")
    private String estado;
    @JoinColumn(name = "AMO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Amortizacion amoId;
    @JoinColumn(name = "PRE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Prestamo preId;
    @JoinColumn(name = "TCO_ID", referencedColumnName = "ID")
    @ManyToOne
    private TipoCargo tcoId;

    public Cargo() {
    }

    public Cargo(BigDecimal id) {
        this.id = id;
    }

    public Cargo(BigDecimal id, BigDecimal valor, String estado) {
        this.id = id;
        this.valor = valor;
        this.estado = estado;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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

    public Amortizacion getAmoId() {
        return amoId;
    }

    public void setAmoId(Amortizacion amoId) {
        this.amoId = amoId;
    }

    public Prestamo getPreId() {
        return preId;
    }

    public void setPreId(Prestamo preId) {
        this.preId = preId;
    }

    public TipoCargo getTcoId() {
        return tcoId;
    }

    public void setTcoId(TipoCargo tcoId) {
        this.tcoId = tcoId;
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
        if (!(object instanceof Cargo)) {
            return false;
        }
        Cargo other = (Cargo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.prestamo.Entidades.Cargo[ id=" + id + " ]";
    }
    
}
