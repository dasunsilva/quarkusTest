package org.dasun.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.dasun.dto.UserDTO;
import org.dasun.service.UserService;

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

    // --------------------------------------------------------------------------
    // Get requests

    // Get all
    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    // Get using id
    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO getUser(@PathParam("id") Long id){
        return userService.getUser(id);
    }

    // --------------------------------------------------------------------------
    // Post Requests

    // Add with request body
    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public String addUser(@Valid UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    // --------------------------------------------------------------------------
    // Put Requests

    // Update using id and body
    @PUT
    @Path("edit/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String editUser(@PathParam("id") Long id, @Valid UserDTO userDTO){
        return userService.updateUser(userDTO,id);
    }


    // --------------------------------------------------------------------------
    // Delete Requests

    // Delete using id
    @DELETE
    @Path("remove/{id}")
    public String removeUser(@PathParam("id") Long id) {
        return userService.deleteUser(id);
    }
}
