/*
 * Copyright (C) 2018 Markus
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Markus
 */
@Entity
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startingDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endingDate;
    private Double coverPercent;
    
    @OneToMany(mappedBy = "project")
    @JsonManagedReference
    private List<Product> products;
    
    @ManyToMany
    @JsonManagedReference
    private List<Color> colors;
    
    //GETTERS
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public Double getCoverPercent() {
        return coverPercent;
    }

    //@JsonbTransient
    @XmlTransient
    public List<Color> getColors() {
        return colors;
    }

    public List<Product> getProducts() {
        return products;
    }
    
    
    //SETTERS
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public void setCoverPercent(Double coverPercent) {
        this.coverPercent = coverPercent;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Project[ id=" + id + " ]";
    }
    
}
