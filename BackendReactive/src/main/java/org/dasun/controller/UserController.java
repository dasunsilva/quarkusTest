package org.dasun.controller;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.dasun.dto.UserDTO;
import org.dasun.service.UserService;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import java.util.List;

/*

Application Scoped Bean
An @ApplicationScoped bean is created once for the entire application lifecycle and shared across all clients.

Request Scoped Bean
A @RequestScoped bean is created for each HTTP request and destroyed after the request is completed.

Singleton Bean
A @Singleton bean is similar to an @ApplicationScoped bean but has a more explicit singleton semantic.

*/


/**
 * This is the controller which interacts with the user
 * @author Dasun
 */

@RequestScoped
@Path("users")
@SecurityRequirement(name = "Keycloak")
public class UserController {
    /**
     * This is used to get the user service
     */
    @Inject
    UserService userService;
//
    /**
     * This method is used to get all user details
     *
     * @return A list of UserDTO which holds the details about bills.
     */
    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<UserDTO>> getAllUsers(){
        return userService.getAllUsers();
    }

    /**
     * This metood is used to get the user using the user id
     *
     * @param id The id of the user
     * @return This will return a single UserDTO corresponding to the given id
     */
    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<UserDTO> getUser(@PathParam("id") Long id){
        return userService.getUser(id);
    }

    /**
     * This method is used to add a user to the database
     *
     * @param userDTO is the use input
     * @return This will return a string telling the status of the add user.
     * This will return a success message or a failure message depending on the situation
     */
    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<String> addUser(@Valid UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

//
//    /**
//     * This method is used to edit the user with the given id
//     * @param id The id of the user that need to be edited.
//     * @param userDTO This will contain the information of the new user that need to be updated
//     * @return return This will return a string telling the status of the edit user.
//     * This will return a success message or a failure message depending on the situation
//     */
//    @PUT
//    @Path("edit/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String editUser(@PathParam("id") Long id, @Valid UserDTO userDTO){
//        try {
//            return userService.updateUser(userDTO,id);
//        } catch (DatabaseException e) {
//            return e.getMessage();
//        }
//    }
//
//
//    /**'
//     * This will delete a user with the given id
//     * @param id The id of the user that need to be deleted
//     * @return return This will return a string telling the status of the add bill.
//     * This will return a success message or a failure message depending on the situation
//     */
//    @DELETE
//    @Path("remove/{id}")
//    public String removeUser(@PathParam("id") Long id) {
//        try {
//            return userService.deleteUser(id);
//        } catch (DatabaseException e) {
//            return e.getMessage();
//        }
//    }
}
