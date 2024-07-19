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
public class BillDTOInput {
    private Long id;
    private LocalDate date;
    private int amount;
    private Long userId;

    private List<Item> items;
    private User user;

    private UserDTO userDTO;

    private List<BillItemDTO> billItemDTOS;
}
