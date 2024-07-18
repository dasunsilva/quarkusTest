package org.dasun.dto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.dasun.model.Bill;

import java.util.List;

@Getter
@Setter
@ApplicationScoped
public class ItemDTO {
    private Long id;

    @Size(message = "Name is too short", min = 2)
    private String name;

    private int price;
    private int stock;

    private List<Bill> bills;
}
