/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package njt.cineman.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import njt.cineman.exception.BasicAuthenticationException;
import njt.cineman.exception.MyRollbackException;
import njt.cineman.help.JsonToken;
import njt.cineman.help.RestHelpClass;
import njt.cineman.model.impl.User;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author cineman
 */
@Path("/user")
public class UserService {

    RestHelpClass help;

    public UserService() {
        help = new RestHelpClass();
    }

    @POST
    @CrossOrigin
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@HeaderParam("authorization") String authorization) {
        try {
            String[] userPass = help.getAbstractToken().decodeBasicAuth(authorization);
            EntityManager em = help.getEntityManager();
            User u = (User) em
                    .createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
                    .setParameter("email", userPass[0])
                    .setParameter("password", userPass[1])
                    .getSingleResult();

            if (u.getToken() == null || u.getToken().equals("")) {
                u.setToken(help.getAbstractToken().createToken(u.getId()));
                help.mergeObject(em, u);
            }
            JsonToken jsonToken = new JsonToken(help.getAbstractToken().encode(u.getToken()));
            u.setToken(jsonToken.getToken());
            return Response.ok().entity(u).build();
        } catch (RuntimeException e) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, e);
            throw new BasicAuthenticationException(e.getMessage());
        }
    }

    @GET
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@QueryParam("id") int id) {
        EntityManager em = help.getEntityManager();
        User u = em.find(User.class, id);
        if (u != null) {
            u.setToken(null);
            help.mergeObject(em, u);
            return Response.ok().entity(u).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(@HeaderParam("authorization") String authorization, @RequestBody Identity identity) {
        EntityManager em = help.getEntityManager();
        String[] userPass = help.getAbstractToken().decodeBasicAuth(authorization);
        User user = new User(Integer.MIN_VALUE, identity.getName(), identity.getSurname(), userPass[0], userPass[1], null);
        try {
            help.persistObject(em, user);
            if (user.getToken() == null || user.getToken().equals("")) {
                user.setToken(help.getAbstractToken().createToken(user.getId()));
                help.mergeObject(em, user);
            }
            return Response.status(Response.Status.CREATED).entity(user).build();
        } catch (Exception e) {
            throw new MyRollbackException("User already exists!");
        }

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Identity {

        private String name;
        private String surname;

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }
    }

}
