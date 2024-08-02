package org.dasun.controller;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.dasun.dto.ItemDTO;
import org.dasun.exceptions.DatabaseException;
import org.dasun.exceptions.InvalidLongException;
import org.dasun.service.ItemService;
import org.dasun.service.ItemService;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import java.util.List;

/**
 * This is the controller which interacts with the user
 * @author Dasun
 */
@RequestScoped
@Path("items")
@SecurityRequirement(name = "Keycloak")
public class ItemController {
    /**
     * This is used to get the item service
     */
    @Inject
    ItemService itemService;

    /**
     * This method is used to get all item details
     * @return A list of ItemDTO which holds the details about bills.
     */
    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemDTO> getAllItems(){
        return itemService.getAllItems();
    }

    /**
     * This metood is used to get the item using the item id
     * @param id The id of the item that need to retrieved
     * @return This will return a single ItemDTO corresponding to the given id
     */
    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ItemDTO getItem(@PathParam("id") Long id){
        return itemService.getItem(id);
    }

    /**
     * This method is used to add a item to the database
     * @param itemDTO is the use input
     * @return This will return a string telling the status of the add item.
     * This will return a success message or a failure message depending on the situation
     */
    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public String addItem(@Valid ItemDTO itemDTO) {
        try {
            return itemService.addItem(itemDTO);
        } catch (DatabaseException e) {
            return e.getMessage();
        }
    }

    /**
     * This method is used to edit the item with the given id
     * @param id The id of the item user want to edit
     * @param itemDTO This will contain the information of the new item that need to be updated
     * @return return This will return a string telling the status of the add item operation.
     * This will return a success message or a failure message depending on the situation
     */
    @PUT
    @Path("edit/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String editItem(@PathParam("id") Long id, @Valid ItemDTO itemDTO){
        try {
            return itemService.updateItem(itemDTO,id);
        } catch (DatabaseException e) {
            return e.getMessage();
        }
    }

    /**'
     * This will delete a item with the given id
     * @param id The id of the item that need to be deleted
     * @return return This will return a string telling the status of the delete item.
     * This will return a success message or a failure message depending on the situation
     */

    @DELETE
    @Path("remove/{id}")
    public String removeItem(@PathParam("id") Long id) {
        try {
            return itemService.deleteItem(id);
        } catch (DatabaseException e) {
            return e.getMessage();
        }
    }
}
