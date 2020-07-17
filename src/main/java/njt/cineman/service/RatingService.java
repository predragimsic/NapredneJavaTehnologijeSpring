/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package njt.cineman.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import njt.cineman.exception.DataNotFoundException;
import njt.cineman.exception.MyRollbackException;
import njt.cineman.exception.NotAuthorizedException;
import njt.cineman.help.RestHelpClass;
import njt.cineman.model.impl.Genre;
import njt.cineman.model.impl.Movie;
import njt.cineman.model.impl.Rating;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author cineman
 */
@Path("/rating")
public class RatingService {

    RestHelpClass help;

    public RatingService() {
        help = new RestHelpClass();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRatings(@HeaderParam("authorization") String authorization, @QueryParam("id") int id) {
        EntityManager em = help.getEntityManager();
        if (help.isLogged(id, em)) {
            List<Rating> ratings = em.createNamedQuery("Rating.findAll").getResultList();
            if (ratings.isEmpty()){
                throw new DataNotFoundException("There are no ratings!");
            } else {
                return Response.ok().entity(ratings).build();
            }
        } else {
            throw new NotAuthorizedException("You are not logged in!");
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response insertRating(@HeaderParam("authorization") String authorization, @QueryParam("id") int id, @RequestBody Rating rating) {
        EntityManager em = help.getEntityManager();
        if (help.isLogged(id, em)) {
            try {
                rating = (Rating) help.mergeObject(em, rating);
                return Response.status(Response.Status.CREATED).entity(rating.getId()).build();
            } catch (RollbackException e) {
                throw new MyRollbackException("Insert error!");
            }
        } else {
            throw new NotAuthorizedException("You are not logged in!");
        }
    }
    @DELETE
    public Response deleteRating(@HeaderParam("authorization") String authorization, @QueryParam("id") int id, @QueryParam("ratingID") int ratingID) {
        EntityManager em = help.getEntityManager();
        if (help.isLogged(id, em)) {
            System.out.println(ratingID);
            Rating rating = em.find(Rating.class, ratingID);
            System.out.println("Trazim rating");
            if (rating != null) {
                help.removeObject(em, rating);
                System.out.println("Nasao ratings");
                return Response.ok().build();
            } else {
                throw new DataNotFoundException("There is no rating that you want to delete!");
            }
        } else {
            throw new NotAuthorizedException("You are not logged in!");
        }
    }
}
