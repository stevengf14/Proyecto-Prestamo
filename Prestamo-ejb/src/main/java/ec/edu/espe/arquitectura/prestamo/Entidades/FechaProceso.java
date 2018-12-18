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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Steven
 */
@Entity
@Table(name = "FECHA_PROCESO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FechaProceso.findAll", query = "SELECT f FROM FechaProceso f")
    , @NamedQuery(name = "FechaProceso.findByCodigo", query = "SELECT f FROM FechaProceso f WHERE f.codigo = :codigo")
    , @NamedQuery(name = "FechaProceso.findByFecha", query = "SELECT f FROM FechaProceso f WHERE f.fecha = :fecha")})
public class FechaProceso implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private BigDecimal codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public FechaProceso() {
    }

    public FechaProceso(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public FechaProceso(BigDecimal codigo, Date fecha) {
        this.codigo = codigo;
        this.fecha = fecha;
    }

    public BigDecimal getCodigo() {
        return codigo;
    }

    public void setCodigo(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FechaProceso)) {
            return false;
        }
        FechaProceso other = (FechaProceso) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.prestamo.Entidades.FechaProceso[ codigo=" + codigo + " ]";
    }
    
}
