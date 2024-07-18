package org.dasun.dto;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.Setter;
import org.dasun.model.Item;
import org.dasun.model.User;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ApplicationScoped
public class BillDTO {
    private Long id;
    private LocalDate date;
    private int amount;

    private User user;

    private List<Item> items;
}
