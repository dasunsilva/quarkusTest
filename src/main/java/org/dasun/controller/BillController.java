package org.dasun.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.dasun.dto.BillDTO;
import org.dasun.exceptions.DatabaseException;
import org.dasun.exceptions.InvalidLongException;
import org.dasun.service.BillService;
import org.dasun.service.BillService;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import java.util.List;

/**
 * This is the controller which interacts with the user
 * @author Dasun
 */
@RequestScoped
@Path("bills")
@SecurityRequirement(name = "Keycloak")
public class BillController {

    /**
     * This is used to get the bill service
     */
    @Inject
    BillService billService;

    /**
     * This method is used to get all bill details
     * @return A list of BillDTO which holds the details about bills.
     */
    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BillDTO> getAllBills(){
        return billService.getAllBills();
    }

    /**
     * This method is used to get the bill using teh bill id
     * @param id The id of the bill
     * @return This will return a single BillDTO corresponding to the given id
     */
    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public BillDTO getBill(@PathParam("id") Long id){
        return billService.getBill(id);
    }

    /**
     * This method is used to add a bill to the database
     * @param billDTO is the use input
     * @return This will return a string telling the status of the add bill.
     * This will return a success message or a failure message depending on the situation
     */
    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public String addBill(BillDTO billDTO) {
        try {
            return billService.addBill(billDTO);
        } catch (DatabaseException e) {
            return e.getMessage();
        }
    }

    /**
     * This method is used to edit the bill with the given id
     * @param id The id of the bill user want to edit
     * @param billDTO This will contain the information of the new bill that need to be updated
     * @return return This will return a string telling the status of the add bill.
     * This will return a success message or a failure message depending on the situation
     */
    @PUT
    @Path("edit/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String editBill(@PathParam("id") Long id, BillDTO billDTO) {
        try {
            return billService.updateBill(billDTO, id);
        } catch (DatabaseException e) {
            return e.getMessage();
        }
    }

    /**'
     * This will delete a bill with the given id
     * @param id The id of the bill that need to be deleted
     * @return return This will return a string telling the status of the delete bill.
     * This will return a success message or a failure message depending on the situation
     */
    @DELETE
    @Path("remove/{id}")
    public String removeBill(@PathParam("id") Long id) {
        try {
            return billService.deleteBill(id);
        } catch (DatabaseException e) {
            return e.getMessage();
        }
    }
}
