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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Steven
 */
@Entity
@Table(name = "PRODUCTO_COMISION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoComision.findAll", query = "SELECT p FROM ProductoComision p")
    , @NamedQuery(name = "ProductoComision.findById", query = "SELECT p FROM ProductoComision p WHERE p.id = :id")
    , @NamedQuery(name = "ProductoComision.findByValorFijo", query = "SELECT p FROM ProductoComision p WHERE p.valorFijo = :valorFijo")
    , @NamedQuery(name = "ProductoComision.findByPorcentaje", query = "SELECT p FROM ProductoComision p WHERE p.porcentaje = :porcentaje")})
public class ProductoComision implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_FIJO")
    private BigDecimal valorFijo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PORCENTAJE")
    private BigDecimal porcentaje;
    @JoinColumn(name = "COM_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Comision comId;
    @JoinColumn(name = "PRO_ID", referencedColumnName = "PRO_ID")
    @ManyToOne(optional = false)
    private Producto proId;

    public ProductoComision() {
    }

    public ProductoComision(BigDecimal id) {
        this.id = id;
    }

    public ProductoComision(BigDecimal id, BigDecimal valorFijo, BigDecimal porcentaje) {
        this.id = id;
        this.valorFijo = valorFijo;
        this.porcentaje = porcentaje;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getValorFijo() {
        return valorFijo;
    }

    public void setValorFijo(BigDecimal valorFijo) {
        this.valorFijo = valorFijo;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Comision getComId() {
        return comId;
    }

    public void setComId(Comision comId) {
        this.comId = comId;
    }

    public Producto getProId() {
        return proId;
    }

    public void setProId(Producto proId) {
        this.proId = proId;
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
        if (!(object instanceof ProductoComision)) {
            return false;
        }
        ProductoComision other = (ProductoComision) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.prestamo.Entidades.ProductoComision[ id=" + id + " ]";
    }
    
}
