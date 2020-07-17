/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package njt.cineman.model.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import njt.cineman.model.MyEntity;

/**
 *
 * @author cineman
 */
@Entity
@Table(name = "AWARD")
public class Award implements Serializable, MyEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_AWARD")
    @TableGenerator(name = "GEN_AWARD", table = "GEN_ID",
            pkColumnName = "PK_GEN", valueColumnName = "VALUE_GEN",
            pkColumnValue = "TBL_AWARD", initialValue = 0, allocationSize = 1)
    private int id;
    private String name;
    private String director;
    private String image;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String country;
    
    @JoinColumn(name = "category", referencedColumnName = "id")
    @ManyToOne
    private Category category;
    
    @JoinColumn(name = "content", referencedColumnName = "id")
    @ManyToOne
    private Content content;
    
    @JoinColumn(name = "actor", referencedColumnName = "id")
    @ManyToOne
    private Actor actor;
    

    public Award() {
    }

    public Award(int id, String name, String director, String image, String description, Date date, String country, Category category, Content content, Actor actor) {
        this.id = id;
        this.name = name;
        this.director = director;
        this.image = image;
        this.description = description;
        this.date = date;
        this.country = country;
        this.category = category;
        this.content = content;
        this.actor = actor;
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

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    @Override
    public String toString() {
        return "Award{" + "id=" + id + ", name=" + name + ", director=" + director + ", image=" + image + ", description=" + description + ", date=" + date + ", country=" + country + ", category=" + category + ", content=" + content + ", actor=" + actor + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.director);
        hash = 37 * hash + Objects.hashCode(this.image);
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + Objects.hashCode(this.date);
        hash = 37 * hash + Objects.hashCode(this.country);
        hash = 37 * hash + Objects.hashCode(this.category);
        hash = 37 * hash + Objects.hashCode(this.content);
        hash = 37 * hash + Objects.hashCode(this.actor);
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
        final Award other = (Award) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
    
    
}
