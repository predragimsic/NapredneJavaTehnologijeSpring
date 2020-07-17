package njt.cineman.model.impl;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import njt.cineman.model.impl.Actor;
import njt.cineman.model.impl.Category;
import njt.cineman.model.impl.Content;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-15T12:38:38")
@StaticMetamodel(Award.class)
public class Award_ { 

    public static volatile SingularAttribute<Award, Date> date;
    public static volatile SingularAttribute<Award, Actor> actor;
    public static volatile SingularAttribute<Award, String> image;
    public static volatile SingularAttribute<Award, String> country;
    public static volatile SingularAttribute<Award, String> director;
    public static volatile SingularAttribute<Award, String> name;
    public static volatile SingularAttribute<Award, String> description;
    public static volatile SingularAttribute<Award, Integer> id;
    public static volatile SingularAttribute<Award, Category> category;
    public static volatile SingularAttribute<Award, Content> content;

}