package org.dasun.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.dasun.dto.PostDTO;
import org.dasun.dto.UserDTOMapper;
import org.dasun.model.User;
import org.dasun.service.UserService;

import java.util.ArrayList;
import java.util.List;

/*

Application Scoped Bean
An @ApplicationScoped bean is created once for the entire application lifecycle and shared across all clients.

Request Scoped Bean
A @RequestScoped bean is created for each HTTP request and destroyed after the request is completed.

Singleton Bean
A @Singleton bean is similar to an @ApplicationScoped bean but has a more explicit singleton semantic.

*/
@RequestScoped
@Path("users")
public class UserController {

    @Inject
    UserService userService;

    @Inject
    UserDTOMapper userDTOMapper;

    // --------------------------------------------------------------------------
    // Get requests

    // Get all
    @GET
    @Path("get")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllUsers(){
        return userService.getAllUsersAsString();
    }

    // Get using id
    @GET
    @Path("get/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getUser(String id){
        return userService.getUser(id);
    }

    // --------------------------------------------------------------------------
    // Post Requests

    // Add with request body
    @POST
    @Path("add")
    public String addUser(String requestBody) {
        return userService.addUser(requestBody);
    }

    // --------------------------------------------------------------------------
    // Put Requests

    // Update using id and body
    @PUT
    @Path("edit/{id}")
    public String editUser(@PathParam("id") String id, String requestBody) {
        return userService.updateUser(requestBody,id);
    }

    // --------------------------------------------------------------------------
    // Delete Requests

    // Delete using id
    @DELETE
    @Path("remove/{id}")
    public String removeUser(@PathParam("id") String id) {
        return userService.deleteUser(id);
    }

}
