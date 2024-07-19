package org.dasun.dto;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.dasun.model.Item;
import org.dasun.model.User;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@ApplicationScoped
public class BillItemDTO {
    private Long id;
    private String name;
    private int quantity;
    private int priceAtPurchase;
    private Long itemId;
    private Long billId;
}

