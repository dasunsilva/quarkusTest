package org.dasun.controller;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.dasun.command.ItemCommand;
import org.dasun.dto.ItemDTO;
import org.dasun.service.ItemService;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

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

    @Inject
    ItemCommand itemCommand;

    /**
     * This method is used to get all item details
     *
     * @return A list of ItemDTO which holds the details about bills.
     */
    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getAllItems(){
        return itemService.getAllItems()
                .onItem().transform(itemDTOS -> Response.ok(itemDTOS).build())
                .onFailure().recoverWithItem(error ->
                        Response.status(Response.Status.BAD_REQUEST)
                                .entity(error.getMessage()).build());
    }

    /**
     * This metood is used to get the item using the item id
     *
     * @param id The id of the item that need to retrieved
     * @return This will return a single ItemDTO corresponding to the given id
     */
    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getItem(@PathParam("id") Long id){
        return itemService.getItem(id)
                .onItem().transform(itemDTO ->
                        Response.ok(itemDTO).build())
                .onFailure().recoverWithItem(error ->
                        Response.status(Response.Status.BAD_REQUEST)
                                .entity(error.getMessage()).build()
                );
    }

    /**
     * This method is used to add a item to the database
     *
     * @param itemDTO is the use input
     * @return This will return a string telling the status of the add item.
     * This will return a success message or a failure message depending on the situation
     */
    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> addItem(@Valid ItemDTO itemDTO) {
        return itemCommand.addItem(itemDTO)
                .onItem().transform(item -> Response.ok(item).build())
                .onFailure().recoverWithItem(error ->
                        Response.status(Response.Status.BAD_REQUEST)
                                .entity(error.getMessage()).build());
    }

    /**
     * This method is used to edit the item with the given id
     *
     * @param id      The id of the item user want to edit
     * @param itemDTO This will contain the information of the new item that need to be updated
     * @return return This will return a string telling the status of the add item operation.
     * This will return a success message or a failure message depending on the situation
     */
    @PUT
    @Path("edit/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> editItem(@PathParam("id") Long id, @Valid ItemDTO itemDTO){
        return itemCommand.updateItem(itemDTO,id)
                .onItem().transform(res -> Response.ok(res).build())
                .onFailure().recoverWithItem(error ->
                        Response.status(Response.Status.BAD_REQUEST)
                                .entity(error.getMessage()).build());
    }

    /**
     * '
     * This will delete a item with the given id
     *
     * @param id The id of the item that need to be deleted
     * @return return This will return a string telling the status of the delete item.
     * This will return a success message or a failure message depending on the situation
     */
    @DELETE
    @Path("remove/{id}")
    public Uni<Response> removeItem(@PathParam("id") Long id) {
        return itemCommand.deleteItem(id)
                .onItem().transform(res -> Response.ok(res).build())
                .onFailure().recoverWithItem(error ->
                        Response.status(Response.Status.BAD_REQUEST)
                                .entity(error.getMessage()).build());
    }
}
