/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package njt.cineman.model.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import njt.cineman.model.MyEntity;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.eclipse.persistence.annotations.CascadeOnDelete;

/**
 *
 * @author cineman
 */
@JsonDeserialize(using = PropertyPresentDeserializer.class)
@JsonSubTypes({
    @Type(name = "budget", value = Movie.class)
    ,
        @Type(name = "numberOfSeasons", value = Show.class)
})
@Entity
@Table(name = "CONTENT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CONTENT_TYPE")

public abstract class Content implements MyEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_CONTENT")
    @TableGenerator(name = "GEN_CONTENT", table = "GEN_ID",
            pkColumnName = "PK_GEN", valueColumnName = "VALUE_GEN",
            pkColumnValue = "TBL_CONTENT", initialValue = 0, allocationSize = 1)
    private int id;
    private String name;
    private String producer;
    private String image;
    private String description;
    private int year;
    private String country;
    private double grade;
    private int peopleRated;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "content")
    @CascadeOnDelete
    private List<Rating> ratings = new ArrayList<Rating>();

    @ManyToOne
    @JoinColumn(name = "GENRE_ID")
    private Genre genre;

    public Content() {
    }

    public Content(int id, String name, String producer, String image, String description, int year, String country, double grade, int peopleRated, Genre genre) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.image = image;
        this.description = description;
        this.year = year;
        this.country = country;
        this.grade = grade;
        this.peopleRated = peopleRated;
        this.genre = genre;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getPeopleRated() {
        return peopleRated;
    }

    public void setPeopleRated(int peopleRated) {
        this.peopleRated = peopleRated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Content{" + "name=" + name + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.id;
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + Objects.hashCode(this.producer);
        hash = 31 * hash + Objects.hashCode(this.image);
        hash = 31 * hash + Objects.hashCode(this.description);
        hash = 31 * hash + this.year;
        hash = 31 * hash + Objects.hashCode(this.country);
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.grade) ^ (Double.doubleToLongBits(this.grade) >>> 32));
        hash = 31 * hash + this.peopleRated;
        hash = 31 * hash + Objects.hashCode(this.genre);
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
        final Content other = (Content) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

}
