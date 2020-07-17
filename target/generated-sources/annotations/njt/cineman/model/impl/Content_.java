package njt.cineman.model.impl;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import njt.cineman.model.impl.Genre;
import njt.cineman.model.impl.Rating;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-15T12:38:38")
@StaticMetamodel(Content.class)
public abstract class Content_ { 

    public static volatile SingularAttribute<Content, String> image;
    public static volatile SingularAttribute<Content, String> country;
    public static volatile SingularAttribute<Content, Integer> year;
    public static volatile SingularAttribute<Content, Integer> peopleRated;
    public static volatile ListAttribute<Content, Rating> ratings;
    public static volatile SingularAttribute<Content, Double> grade;
    public static volatile SingularAttribute<Content, String> name;
    public static volatile SingularAttribute<Content, Genre> genre;
    public static volatile SingularAttribute<Content, String> producer;
    public static volatile SingularAttribute<Content, String> description;
    public static volatile SingularAttribute<Content, Integer> id;

}