package org.dasun.dto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * This class defines the fields of the Item data transfer object
 */
@Getter
@Setter
@ApplicationScoped
public class ItemDTO {
    private Long id;

    @Size(message = "Name is too short", min = 2)
    private String name;

    @Min(value = 1, message = "Please enter a valid price")
    private int price;

    @Min(value = 1, message = "Please enter a valid stock number")
    private int stock;

    private List<Long> billIds;
}
