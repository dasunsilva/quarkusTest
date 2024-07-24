package org.dasun.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.dasun.dto.ItemDTO;
import org.dasun.dto.UserDTO;
import org.dasun.model.Item;
import org.dasun.service.ReportService;
import org.dasun.service.implementation.ReportServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * This is the controller which interacts with the user
 * @author Dasun
 */
@RequestScoped
@Path("report")
public class ReportController {
    /**
     * This is used to get the report service
     */
    @Inject
    ReportService reportService;

    /**
     * This method is used to get all sales details
     * @return the total sales
     */
    @GET
    @Path("total-sales")
    @Produces(MediaType.APPLICATION_JSON)
    // Manager, Owner
    public String totalSales(){
        return reportService.getTotalSales();
    }

    /**
     * This method is used to get the highest selling item
     * @return This will return a List of ItemDTO that has information about the highest selling item(s)
     */
    @GET
    @Path("max-sale-item")
    @Produces(MediaType.APPLICATION_JSON)
    // Manager, Owner
    public List<ItemDTO> maxItem(){
        return reportService.getMaxItem();

    }

    /**
     * This is used to get the information about the least selling item in the store
     * @return this will return a ItemDTO list that contains the information about the least selling item(s)
     */
    @GET
    @Path("min-sale-item")
    @Produces(MediaType.APPLICATION_JSON)
    // Manager, Owner
    public List<ItemDTO> minItem(){
        return reportService.getMinItem();
    }

    /**
     * This method is used to get the average spending of uses in the store
     * @return this will return a string indicating what is the average spending in the store
     */
    @GET
    @Path("average-spending")
    @Produces(MediaType.APPLICATION_JSON)
    // Manager, Owner
    public String averageSpending(){
        return reportService.getAverageSpending();

    }

    /**
     * This is used to get the active users of the store.
     * Active user means a user that have atleast one bill from the store.
     * @return This will return a list of UserDTO indicating who are the active users.
     */
    @GET
    @Path("active-users")
    @Produces(MediaType.APPLICATION_JSON)
    // Manager, Owner
    public List<UserDTO> activeUsers(){
        return reportService.getActiveUsers();

    }
}
