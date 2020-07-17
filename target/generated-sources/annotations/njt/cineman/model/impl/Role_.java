package njt.cineman.model.impl;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import njt.cineman.model.impl.Actor;
import njt.cineman.model.impl.CompositeRoleID;
import njt.cineman.model.impl.Content;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-15T12:38:38")
@StaticMetamodel(Role.class)
public class Role_ { 

    public static volatile SingularAttribute<Role, Actor> actor;
    public static volatile SingularAttribute<Role, String> image;
    public static volatile SingularAttribute<Role, CompositeRoleID> roleID;
    public static volatile SingularAttribute<Role, String> name;
    public static volatile SingularAttribute<Role, Content> content;

}