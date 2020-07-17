///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package njt.cineman.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import njt.cineman.exception.DataNotFoundException;
import njt.cineman.exception.MyRollbackException;
import njt.cineman.exception.NotAuthorizedException;
import njt.cineman.help.RestHelpClass;
import njt.cineman.model.impl.Actor;
import njt.cineman.model.impl.Content;
import njt.cineman.model.impl.Genre;
import njt.cineman.model.impl.Movie;
import njt.cineman.model.impl.Role;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author cineman
 */
@Path("/actor")
public class ActorService {

    RestHelpClass help;

    public ActorService() {
        help = new RestHelpClass();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActors(@HeaderParam("authorization") String authorization, @QueryParam("id") int id, @QueryParam("actorID") int actorID) {
        EntityManager em = help.getEntityManager();
        System.out.println("vracam actore");
        if (help.isLogged(id, em)) {
            String query = "SELECT a FROM Actor a";
            if (actorID != 0) {
                Actor actor = em.find(Actor.class, actorID);
                if (actor != null) {
                    return Response.ok().entity(actor).build();
                } else {
                    throw new DataNotFoundException("There is no actor!");
                }
            }
            List<Actor> actors = em.createQuery(query).getResultList();
            System.out.println("nasao actore i saljem odgovor");
            return Response.ok().entity(actors).build();
        } else {
            throw new NotAuthorizedException("You are not logged in!");
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response insertActor(@HeaderParam("authorization") String authorization, @QueryParam("id") int id, @RequestBody Actor actor) {
        EntityManager em = help.getEntityManager();
        if (help.isLogged(id, em)) {
            try {
                System.out.println("------------**********************************--------------------");
                String query = "SELECT MAX(a.id) FROM Actor a";
                int max = (int) em.createQuery(query).getSingleResult();
                System.out.println(max);
                
                actor.setId(max+1);
                
                System.out.println("actor id:"+actor.getId());
                for (Role role : actor.getRoles()) {
                    System.out.println("usao u for");
                    role.setActor(actor);
                    role.getRoleID().setActorID(actor.getId());
                    System.out.println("da li role ima actora: "+role.getActor().getId());
                    
                }
                actor = (Actor) help.mergeObject(em, actor);
                return Response.status(Response.Status.CREATED).entity(actor.getId()).build();
            } catch (RollbackException e) {
                throw new MyRollbackException("Insert error!");
            }
        } else {
            throw new NotAuthorizedException("You are not logged in!");
        }
    }

    @DELETE
    public Response deleteActor(@HeaderParam("authorization") String authorization, @QueryParam("id") int id, @QueryParam("actorID") int actorID) {
        EntityManager em = help.getEntityManager();
        if (help.isLogged(id, em)) {
            Actor actor = em.find(Actor.class, actorID);
            if (actor != null) {
                help.removeObject(em, actor);
                return Response.ok().build();
            } else {
                throw new DataNotFoundException("There is no actor that you want to delete!");
            }
        } else {
            throw new NotAuthorizedException("You are not logged in!");
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateActor(@HeaderParam("authorization") String authorization, @RequestBody Actor actor, @QueryParam("id") int id) {
        EntityManager em = help.getEntityManager();
        if (help.isLogged(id, em)) {
            Actor actorToUpdate = em.find(Actor.class, actor.getId());
            if (actorToUpdate != null) {
                Actor newActor = (Actor) help.mergeValues(actorToUpdate, actor);
                help.mergeObject(em, newActor);
                for (Role role : actor.getRoles()) {
                    role.getRoleID().setActorID(actorToUpdate.getId());
                    if (role.getStatus().equals("INSERT")) {
                        help.mergeObject(em, role);
                    }
                    if (role.getStatus().equals("UPDATE")) {
                        Role roleToUpdate = em.find(Role.class, role.getRoleID());
                        if (roleToUpdate != null) {
                            Content content = em.find(Content.class, role.getContent().getId());

                            role.setContent(content);
                            Role newRole = (Role) help.mergeValues(roleToUpdate, role);
                            help.mergeObject(em, newRole);
                        }
                    }
                    if (role.getStatus().equals("DELETE")) {
                        Role roleTD = em.find(Role.class, role.getRoleID());
                        if (roleTD != null) {
                            help.removeObject(em, roleTD);
                        }
                    }
                    
                }
                return Response.ok().build();
            } else {
                throw new DataNotFoundException("There is no actor that you want to update!");
            }
        } else {
            throw new NotAuthorizedException("You are not logged in!");
        }
    }

}
