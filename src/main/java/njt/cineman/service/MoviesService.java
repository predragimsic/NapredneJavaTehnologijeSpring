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
import njt.cineman.model.impl.Genre;
import njt.cineman.model.impl.Movie;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author cineman
 */
@Path("/movie")
public class MoviesService {

    RestHelpClass help;

    public MoviesService() {
        help = new RestHelpClass();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovies(@HeaderParam("authorization") String authorization, @QueryParam("id") int id, @QueryParam("movieID") int movieID) {
        EntityManager em = help.getEntityManager();
        if (help.isLogged(id, em)) {
            String query = "SELECT m FROM Movie m";
            if (movieID != 0) {
                Movie movie = em.find(Movie.class, movieID);
                if (movie != null) {
                    return Response.ok().entity(movie).build();
                } else {
                    throw new DataNotFoundException("There is no movie!");
                }
            }
            List<Movie> movies = em.createQuery(query).getResultList();
            return Response.ok().entity(movies).build();
        } else {
            throw new NotAuthorizedException("You are not logged in!");
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response insertMovie(@HeaderParam("authorization") String authorization, @QueryParam("id") int id, @RequestBody Movie movie) {
        EntityManager em = help.getEntityManager();
        if (help.isLogged(id, em)) {
            try {
                movie = (Movie) help.mergeObject(em, movie);
                System.out.println(movie.getId()+ "**********************************************************************");
                return Response.status(Response.Status.CREATED).entity(movie.getId()).build();
            } catch (RollbackException e) {
                throw new MyRollbackException("Insert error!");
            }
        } else {
            throw new NotAuthorizedException("You are not logged in!");
        }
    }
    
    @DELETE
    public Response deleteMovie(@HeaderParam("authorization") String authorization, @QueryParam("id") int id, @QueryParam("movieID") int movieID) {
        EntityManager em = help.getEntityManager();
        if (help.isLogged(id, em)) {
            Movie movie = em.find(Movie.class, movieID);
            if (movie != null) {
                help.removeObject(em, movie);
                return Response.ok().build();
            } else {
                throw new DataNotFoundException("There is no movie that you want to delete!");
            }
        } else {
            throw new NotAuthorizedException("You are not logged in!");
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMovie(@HeaderParam("authorization") String authorization, @RequestBody Movie movie, @QueryParam("id") int id) {
        EntityManager em = help.getEntityManager();
        if (help.isLogged(id, em)) {
            Movie movieToUpdate = em.find(Movie.class, movie.getId());
            if (movieToUpdate != null) {
                Genre genre = em.find(Genre.class, movie.getGenre().getId());
           
                movie.setGenre(genre);
                Movie newMovie = (Movie) help.mergeValues(movieToUpdate, movie);
                help.mergeObject(em, newMovie);
                return Response.ok().build();
            } else {
                throw new DataNotFoundException("There is no movie that you want to update!");
            }
        } else {
            throw new NotAuthorizedException("You are not logged in!");
        }
    }
}


