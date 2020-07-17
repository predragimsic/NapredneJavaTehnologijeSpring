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
import njt.cineman.model.impl.Award;
import njt.cineman.model.impl.Category;
import njt.cineman.model.impl.Content;
import njt.cineman.model.impl.Genre;
import njt.cineman.model.impl.Movie;
import njt.cineman.model.impl.Show;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author cineman
 */
@Path("/award")
public class AwardService {

    RestHelpClass help;

    public AwardService() {
        help = new RestHelpClass();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAwards(@HeaderParam("authorization") String authorization, @QueryParam("id") int id, @QueryParam("awardID") int awardID) {
        EntityManager em = help.getEntityManager();
        if (help.isLogged(id, em)) {
            String query = "SELECT a FROM Award a";
            if (awardID != 0) {
                Award award = em.find(Award.class, awardID);
                if (award != null) {
                    return Response.ok().entity(award).build();
                } else {
                    throw new DataNotFoundException("There is no award!");
                }
            }
            List<Award> awards = em.createQuery(query).getResultList();
            return Response.ok().entity(awards).build();
        } else {
            throw new NotAuthorizedException("You are not logged in!");
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response insertShow(@HeaderParam("authorization") String authorization, @QueryParam("id") int id, @RequestBody Show show) {
        EntityManager em = help.getEntityManager();
        if (help.isLogged(id, em)) {
            try {
                show = (Show) help.mergeObject(em, show);
                return Response.status(Response.Status.CREATED).entity(show.getId()).build();
            } catch (RollbackException e) {
                throw new MyRollbackException("Insert error!");
            }
        } else {
            throw new NotAuthorizedException("You are not logged in!");
        }
    }
    
    @DELETE
    public Response deleteAward(@HeaderParam("authorization") String authorization, @QueryParam("id") int id, @QueryParam("awardID") int awardID) {
        EntityManager em = help.getEntityManager();
        if (help.isLogged(id, em)) {
            Award award = em.find(Award.class, awardID);
            if (award != null) {
                help.removeObject(em, award);
                return Response.ok().build();
            } else {
                throw new DataNotFoundException("There is no award that you want to delete!");
            }
        } else {
            throw new NotAuthorizedException("You are not logged in!");
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAward(@HeaderParam("authorization") String authorization, @RequestBody Award award, @QueryParam("id") int id) {
        EntityManager em = help.getEntityManager();
        if (help.isLogged(id, em)) {
            Award awardToUpdate = em.find(Award.class, award.getId());
            if (awardToUpdate != null) {
                Category category = em.find(Category.class, award.getCategory().getId());
                Content content = em.find(Content.class, award.getContent().getId());
                Actor actor = em.find(Actor.class, award.getActor().getId());
           
                award.setContent(content);
                award.setCategory(category);
                award.setActor(actor);
                
                Award newAward = (Award) help.mergeValues(awardToUpdate, award);
                help.mergeObject(em, newAward);
                return Response.ok().build();
            } else {
                throw new DataNotFoundException("There is no actor that you want to update!");
            }
        } else {
            throw new NotAuthorizedException("You are not logged in!");
        }
    }
}


