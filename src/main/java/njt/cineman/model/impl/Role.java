/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package njt.cineman.model.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import njt.cineman.model.MyEntity;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author cineman
 */
@Entity
@Table(name = "ROLE")
public class Role implements MyEntity, Serializable{
    
    @EmbeddedId
    private CompositeRoleID roleID;
    private String name;
    private String image;
    @MapsId("actorID")
    @ManyToOne
    private Actor actor;
    @OneToOne
    private Content content;
    @Transient
    @JsonInclude
    private String status;

    public Role() {
    }

    public Role(CompositeRoleID roleID, String name, String image, Actor actor, Content content, String status) {
        this.roleID = roleID;
        this.name = name;
        this.image = image;
        this.actor = actor;
        this.content = content;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

    

    public CompositeRoleID getRoleID() {
        return roleID;
    }

    public void setRoleID(CompositeRoleID roleID) {
        this.roleID = roleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    @JsonIgnore
    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.roleID);
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.image);
        hash = 41 * hash + Objects.hashCode(this.actor);
        hash = 41 * hash + Objects.hashCode(this.content);
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
        final Role other = (Role) obj;
        if (!Objects.equals(this.roleID, other.roleID)) {
            return false;
        }
        if (!Objects.equals(this.actor, other.actor)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Role{" + "roleID=" + roleID + ", name=" + name + ", image=" + image + ", actor=" + actor + ", content=" + content + '}';
    }

    

    
    
    
    
}
