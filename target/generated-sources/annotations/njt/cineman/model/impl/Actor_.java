package njt.cineman.model.impl;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import njt.cineman.model.impl.Role;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-15T12:38:38")
@StaticMetamodel(Actor.class)
public class Actor_ { 

    public static volatile SingularAttribute<Actor, String> country;
    public static volatile SingularAttribute<Actor, String> image;
    public static volatile SingularAttribute<Actor, String> aka;
    public static volatile ListAttribute<Actor, Role> roles;
    public static volatile SingularAttribute<Actor, String> name;
    public static volatile SingularAttribute<Actor, Date> dateOfBirth;
    public static volatile SingularAttribute<Actor, Integer> id;
    public static volatile SingularAttribute<Actor, String> biography;
    public static volatile SingularAttribute<Actor, Integer> height;

}