/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package njt.cineman.model.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import njt.cineman.model.MyEntity;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.eclipse.persistence.annotations.PrivateOwned;

/**
 *
 * @author cineman
 */
@Entity
@Table(name = "ACTOR")
public class Actor implements MyEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_AWARD")
    @TableGenerator(name = "GEN_ACTOR", table = "GEN_ID",
            pkColumnName = "PK_GEN", valueColumnName = "VALUE_GEN",
            pkColumnValue = "TBL_ACTOR", initialValue = 0, allocationSize = 1)
    private int id;
    private String name;
    private String aka;
    private String biography;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private String country;
    private int height;
    private String image;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "actor")
    private List<Role> roles;

    public Actor() {
    }

    public Actor(int id, String name, String aka, String biography, Date dateOfBirth, String country, int height, String image, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.aka = aka;
        this.biography = biography;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.height = height;
        this.image = image;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAka() {
        return aka;
    }

    public void setAka(String aka) {
        this.aka = aka;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.aka);
        hash = 59 * hash + Objects.hashCode(this.biography);
        hash = 59 * hash + Objects.hashCode(this.dateOfBirth);
        hash = 59 * hash + Objects.hashCode(this.country);
        hash = 59 * hash + this.height;
        hash = 59 * hash + Objects.hashCode(this.image);
        hash = 59 * hash + Objects.hashCode(this.roles);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Actor other = (Actor) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

}
