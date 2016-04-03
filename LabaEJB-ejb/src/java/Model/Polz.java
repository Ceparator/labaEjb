/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ceparator
 */
@Entity
@Table(name = "polz")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Polz.findAll", query = "SELECT p FROM Polz p"),
    @NamedQuery(name = "Polz.findByIdPolz", query = "SELECT p FROM Polz p WHERE p.idPolz = :idPolz"),
    @NamedQuery(name = "Polz.findByUsername", query = "SELECT p FROM Polz p WHERE p.username = :username")})
public class Polz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPolz")
    private Integer idPolz;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "username")
    private String username;

    @ManyToMany(cascade
            = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "polzs_tasks",
            joinColumns = @JoinColumn(name = "idPolz", referencedColumnName = "idPolz"),
            inverseJoinColumns = @JoinColumn(name = "idTask", referencedColumnName = "idTask"))
    private List<Task> tasks;

    public Polz() {
    }

    public Polz(Integer idPolz) {
        this.idPolz = idPolz;
    }

    public Polz(Integer idPolz, String username) {
        this.idPolz = idPolz;
        this.username = username;
    }

    public Integer getIdPolz() {
        return idPolz;
    }

    public void setIdPolz(Integer idPolz) {
        this.idPolz = idPolz;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPolz != null ? idPolz.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Polz)) {
            return false;
        }
        Polz other = (Polz) object;
        if ((this.idPolz == null && other.idPolz != null) || (this.idPolz != null && !this.idPolz.equals(other.idPolz))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Polz[ idPolz=" + idPolz + " ]";
    }

}
