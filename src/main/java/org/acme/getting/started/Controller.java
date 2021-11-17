package org.acme.getting.started;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Controller {
    @Inject
    UserRepo userRepo;

    @GET
    public List<User> list(){
        return userRepo.listAll();
    }
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User get(@PathParam("id") Long id) {
        User user=userRepo.findById(id);
        System.out.println(user.getName());
        return user;
    }
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User user){
        userRepo.persist(user);
        return Response.ok(user).status(201).build();
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        User user = userRepo.findById(id);
        if (user == null)
            throw new WebApplicationException("User does not exist!", 404);
        userRepo.delete(user);
        return Response.status(204).build();
    }
    @PUT
    @Transactional
    @Path("/{id}")
    public User update(@PathParam("id")Long id,User user){
        User userUpdate=userRepo.findById(id);
        userUpdate.setName(user.getName());
        userUpdate.setGender(user.getGender());
        return userUpdate;
    }

    @GET
    @Path("/count")
    public Long count() {
        return userRepo.count();
    }

    @GET
    @Path("/search/{name}")
    public User search(@PathParam("name") String name) {
        return userRepo.findByName(name);
    }

}