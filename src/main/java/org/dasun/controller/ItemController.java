package org.dasun.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.dasun.dto.ItemDTO;
import org.dasun.service.ItemService;
import org.dasun.service.ItemService;

import java.util.List;
@RequestScoped
@Path("items")
public class ItemController {

    @Inject
    ItemService itemService;

    // --------------------------------------------------------------------------
    // Get requests

    // Get all
    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemDTO> getAllItems(){
        return itemService.getAllItems();
    }

    // Get using id
    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ItemDTO getItem(@PathParam("id") Long id){
        return itemService.getItem(id);
    }

    // --------------------------------------------------------------------------
    // Post Requests

    // Add with request body
    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public String addItem(@Valid ItemDTO itemDTO) {
        return itemService.addItem(itemDTO);
    }

    // --------------------------------------------------------------------------
    // Put Requests

    // Update using id and body
    @PUT
    @Path("edit/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String editItem(@PathParam("id") Long id, @Valid ItemDTO itemDTO){
        return itemService.updateItem(itemDTO,id);
    }


    // --------------------------------------------------------------------------
    // Delete Requests

    // Delete using id
    @DELETE
    @Path("remove/{id}")
    public String removeItem(@PathParam("id") Long id) {
        return itemService.deleteItem(id);
    }
}
