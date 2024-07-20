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

@RequestScoped
@Path("report")
public class ReportController {

    @Inject
    ReportService reportService;

    @GET
    @Path("total-sales")
    @Produces(MediaType.APPLICATION_JSON)
    public String totalSales(){
        return reportService.getTotalSales();
    }

    @GET
    @Path("max-sale-item")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemDTO> maxItem(){
        return reportService.getMaxItem();

    }

    @GET
    @Path("min-sale-item")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemDTO> minItem(){
        return reportService.getMinItem();
    }

    @GET
    @Path("average-spending")
    @Produces(MediaType.APPLICATION_JSON)
    public String averageSpending(){
        return reportService.getAverageSpending();

    }

    @GET
    @Path("active-users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> activeUsers(){
        return reportService.getActiveUsers();

    }
}
