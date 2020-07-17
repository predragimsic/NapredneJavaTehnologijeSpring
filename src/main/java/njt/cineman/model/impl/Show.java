/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package njt.cineman.model.impl;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 *
 * @author cineman
 */
@Entity
@DiscriminatorValue(value = "SHOW")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class Show extends Content {

    private int numberOfSeasons;
    private int averageDuration;

    public Show() {
        super(-1, null, null, null, null, 0, null, 0, 0, null);
    }

    
    public Show(int numberOfSeasons,int averageDuration,
            int id,  String name,  String producer,
             String image,  String description,
             int year,  String country,
             double grade,  int peopleRated,
             Genre genre) {
        super(id, name, producer, image, description, year, country, grade, peopleRated, genre);
        this.numberOfSeasons = numberOfSeasons;
        this.averageDuration = averageDuration;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public int getAverageDuration() {
        return averageDuration;
    }

    public void setAverageDuration(int averageDuration) {
        this.averageDuration = averageDuration;
    }

}
