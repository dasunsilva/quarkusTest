package org.dasun.Controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.dasun.DTO.PostDTO;
import org.dasun.DTO.UserDTOMapper;
import org.dasun.Model.User;
import org.dasun.Service.UserService;
import org.dasun.Service.UserServiceImpl;
import org.jboss.resteasy.reactive.*;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Path("users")
public class UserController {

    private UserServiceImpl userService = new UserServiceImpl();

    private UserDTOMapper userDTOMapper = new UserDTOMapper();
    // --------------------------------------------------------------------------
    // Get reqests

    // Get all
    @GET
    @Path("get")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllUsers(){
        List<PostDTO> tempDTOList = new ArrayList<>();
        List<User> userList = userService.getAllUsers();
        for (User tempUser:userList) {
            PostDTO postDTO = userDTOMapper.UserDTOMapper(tempUser);
            tempDTOList.add(postDTO);
        }
        return tempDTOList.toString();
    }

    // Get using id
    @GET
    @Path("get/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public PostDTO getUser(String id){
        User tempUser = userService.getUser(id);
        PostDTO tempDTO = userDTOMapper.UserDTOMapper(tempUser);
        return tempDTO;
    }

    // --------------------------------------------------------------------------
    // Post Requests

    // Add with request body
    @POST
    @Path("add")
    public String addUser(String requestBody) {
        User tempUser = userService.strToUser(requestBody);
        return userService.addUser(tempUser);
    }

    // --------------------------------------------------------------------------
    // Put Requests

    // Update using id and body
    @PUT
    @Path("edit/{id}")
    public String editUser(@PathParam("id") String id, String requestBody) {
        User tempUser = userService.strToUser(requestBody);
        return userService.updateUser(tempUser,id);
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
