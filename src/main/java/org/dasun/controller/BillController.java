package org.dasun.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.dasun.dto.BillDTO;
import org.dasun.service.BillService;
import org.dasun.service.BillService;

import java.util.List;

@RequestScoped
@Path("bills")
public class BillController {

    @Inject
    BillService billService;

    // --------------------------------------------------------------------------
    // Get requests

    // Get all
    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BillDTO> getAllBills(){
        return billService.getAllBills();
    }

    // Get using id
    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public BillDTO getBill(@PathParam("id") Long id){
        return billService.getBill(id);
    }

    // --------------------------------------------------------------------------
    // Post Requests

    // Add with request body
    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public String addBill(BillDTO billDTO) {
        return billService.addBill(billDTO);
    }


    // --------------------------------------------------------------------------
    // Put Requests

    // Update using id and body
    @PUT
    @Path("edit/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String editBill(@PathParam("id") Long id, BillDTO billDTO) {
        return billService.updateBill(billDTO, id);
    }


    // --------------------------------------------------------------------------
    // Delete Requests

    // Delete using id
    @DELETE
    @Path("remove/{id}")
    public String removeBill(@PathParam("id") Long id) {
        return billService.deleteBill(id);
    }
}
