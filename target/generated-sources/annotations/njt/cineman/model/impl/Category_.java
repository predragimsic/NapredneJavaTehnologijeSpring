package njt.cineman.model.impl;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import njt.cineman.model.impl.Award;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-15T12:38:38")
@StaticMetamodel(Category.class)
public class Category_ { 

    public static volatile ListAttribute<Category, Award> awards;
    public static volatile SingularAttribute<Category, String> name;
    public static volatile SingularAttribute<Category, Integer> id;

}