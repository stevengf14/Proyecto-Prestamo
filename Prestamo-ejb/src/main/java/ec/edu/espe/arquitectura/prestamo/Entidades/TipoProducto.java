/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "TIPO_PRODUCTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoProducto.findAll", query = "SELECT t FROM TipoProducto t")
    , @NamedQuery(name = "TipoProducto.findById", query = "SELECT t FROM TipoProducto t WHERE t.id = :id")
    , @NamedQuery(name = "TipoProducto.findByMontoMaximo", query = "SELECT t FROM TipoProducto t WHERE t.montoMaximo = :montoMaximo")
    , @NamedQuery(name = "TipoProducto.findByMontoMinimo", query = "SELECT t FROM TipoProducto t WHERE t.montoMinimo = :montoMinimo")
    , @NamedQuery(name = "TipoProducto.findByDescripcion", query = "SELECT t FROM TipoProducto t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TipoProducto.findByPlazoMax", query = "SELECT t FROM TipoProducto t WHERE t.plazoMax = :plazoMax")
    , @NamedQuery(name = "TipoProducto.findByPlazoMin", query = "SELECT t FROM TipoProducto t WHERE t.plazoMin = :plazoMin")})
public class TipoProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_MAXIMO")
    private BigDecimal montoMaximo;
    @Column(name = "MONTO_MINIMO")
    private BigDecimal montoMinimo;
    @Size(max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "PLAZO_MAX")
    private BigInteger plazoMax;
    @Column(name = "PLAZO_MIN")
    private BigInteger plazoMin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipId")
    private Collection<Producto> productoCollection;

    public TipoProducto() {
    }

    public TipoProducto(BigDecimal id) {
        this.id = id;
    }

    public TipoProducto(BigDecimal id, BigDecimal montoMaximo) {
        this.id = id;
        this.montoMaximo = montoMaximo;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getMontoMaximo() {
        return montoMaximo;
    }

    public void setMontoMaximo(BigDecimal montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    public BigDecimal getMontoMinimo() {
        return montoMinimo;
    }

    public void setMontoMinimo(BigDecimal montoMinimo) {
        this.montoMinimo = montoMinimo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigInteger getPlazoMax() {
        return plazoMax;
    }

    public void setPlazoMax(BigInteger plazoMax) {
        this.plazoMax = plazoMax;
    }

    public BigInteger getPlazoMin() {
        return plazoMin;
    }

    public void setPlazoMin(BigInteger plazoMin) {
        this.plazoMin = plazoMin;
    }

    @XmlTransient
    public Collection<Producto> getProductoCollection() {
        return productoCollection;
    }

    public void setProductoCollection(Collection<Producto> productoCollection) {
        this.productoCollection = productoCollection;
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
        if (!(object instanceof TipoProducto)) {
            return false;
        }
        TipoProducto other = (TipoProducto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.prestamo.Entidades.TipoProducto[ id=" + id + " ]";
    }
    
}
