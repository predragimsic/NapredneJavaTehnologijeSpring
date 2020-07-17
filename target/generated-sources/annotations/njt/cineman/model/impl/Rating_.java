package njt.cineman.model.impl;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import njt.cineman.model.impl.Content;
import njt.cineman.model.impl.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-15T12:38:38")
@StaticMetamodel(Rating.class)
public class Rating_ { 

    public static volatile SingularAttribute<Rating, Integer> rate;
    public static volatile SingularAttribute<Rating, Integer> id;
    public static volatile SingularAttribute<Rating, User> user;
    public static volatile SingularAttribute<Rating, Content> content;

}