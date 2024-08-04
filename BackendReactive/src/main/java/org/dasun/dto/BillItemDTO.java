//package org.dasun.dto;
//
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.validation.constraints.Min;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//import org.dasun.model.Item;
//import org.dasun.model.User;
//
//import java.time.LocalDate;
//import java.util.List;
//
///**
// * This class defines the fields of the Bill item data transfer object
// */
//@Getter
//@Setter
//@ToString
//@ApplicationScoped
//public class BillItemDTO {
//    private Long id;
//
//    private String name;
//    @Min(value = 1, message = "Please enter a valid quantity")
//    private int quantity;
//    @Min(value = 1, message = "Please enter a price at purchase")
//
//    private int priceAtPurchase;
//    private Long itemId;
//    private Long billId;
//}
//
