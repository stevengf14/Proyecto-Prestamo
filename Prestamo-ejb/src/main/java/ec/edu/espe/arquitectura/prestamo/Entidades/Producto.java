/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Samsung-PC
 */
@Entity
@Table(name = "PRODUCTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findByProId", query = "SELECT p FROM Producto p WHERE p.proId = :proId")
    , @NamedQuery(name = "Producto.findByProDescripcion", query = "SELECT p FROM Producto p WHERE p.proDescripcion = :proDescripcion")
    , @NamedQuery(name = "Producto.findByProEstado", query = "SELECT p FROM Producto p WHERE p.proEstado = :proEstado")
    , @NamedQuery(name = "Producto.findByProInteres", query = "SELECT p FROM Producto p WHERE p.proInteres = :proInteres")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRO_ID")
    private BigDecimal proId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "PRO_DESCRIPCION")
    private String proDescripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PRO_ESTADO")
    private String proEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRO_INTERES")
    private BigDecimal proInteres;
    @JoinColumn(name = "TIP_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TipoProducto tipId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proId")
    private Collection<Prestamo> prestamoCollection;

    public Producto() {
    }

    public Producto(BigDecimal proId) {
        this.proId = proId;
    }

    public Producto(BigDecimal proId, String proDescripcion, String proEstado, BigDecimal proInteres) {
        this.proId = proId;
        this.proDescripcion = proDescripcion;
        this.proEstado = proEstado;
        this.proInteres = proInteres;
    }

    public BigDecimal getProId() {
        return proId;
    }

    public void setProId(BigDecimal proId) {
        this.proId = proId;
    }

    public String getProDescripcion() {
        return proDescripcion;
    }

    public void setProDescripcion(String proDescripcion) {
        this.proDescripcion = proDescripcion;
    }

    public String getProEstado() {
        return proEstado;
    }

    public void setProEstado(String proEstado) {
        this.proEstado = proEstado;
    }

    public BigDecimal getProInteres() {
        return proInteres;
    }

    public void setProInteres(BigDecimal proInteres) {
        this.proInteres = proInteres;
    }

    public TipoProducto getTipId() {
        return tipId;
    }

    public void setTipId(TipoProducto tipId) {
        this.tipId = tipId;
    }

    @XmlTransient
    public Collection<Prestamo> getPrestamoCollection() {
        return prestamoCollection;
    }

    public void setPrestamoCollection(Collection<Prestamo> prestamoCollection) {
        this.prestamoCollection = prestamoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proId != null ? proId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.proId == null && other.proId != null) || (this.proId != null && !this.proId.equals(other.proId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.prestamo.Entidades.Producto[ proId=" + proId + " ]";
    }
    
}
