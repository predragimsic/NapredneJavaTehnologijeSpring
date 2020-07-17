/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package njt.cineman.model.impl;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 *
 * @author cineman
 */
@Embeddable
public class CompositeRoleID implements Serializable{
    private int actorID;
    private int roleID;

    public CompositeRoleID() {
    }

    public CompositeRoleID(int actorID, int roleID) {
        this.actorID = actorID;
        this.roleID = roleID;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.actorID;
        hash = 83 * hash + this.roleID;
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
        final CompositeRoleID other = (CompositeRoleID) obj;
        if (this.actorID != other.actorID) {
            return false;
        }
        if (this.roleID != other.roleID) {
            return false;
        }
        return true;
    }
    
    

    

    public int getActorID() {
        return actorID;
    }

    public void setActorID(int actorID) {
        this.actorID = actorID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    @Override
    public String toString() {
        return "CompositeRoleID{" + "actorID=" + actorID + ", roleID=" + roleID + '}';
    }
    
    
    
}
