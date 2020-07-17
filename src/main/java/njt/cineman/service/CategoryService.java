///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package njt.cineman.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import njt.cineman.exception.DataNotFoundException;
import njt.cineman.exception.NotAuthorizedException;
import njt.cineman.help.RestHelpClass;
import njt.cineman.model.impl.Category;
import njt.cineman.model.impl.Movie;

/**
 *
 * @author cineman
 */
@Path("/category")
public class CategoryService {

    RestHelpClass help;

    public CategoryService() {
        help = new RestHelpClass();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories(@HeaderParam("authorization") String authorization, @QueryParam("id") int id) {
        EntityManager em = help.getEntityManager();
        if (help.isLogged(id, em)) {
            List<Category> categories = em.createNamedQuery("Category.findAll").getResultList();
            if (categories.isEmpty()){
                throw new DataNotFoundException("There are no categories!");
            } else {
                return Response.ok().entity(categories).build();
            }
        } else {
            throw new NotAuthorizedException("You are not logged in!");
        }
    }
}


