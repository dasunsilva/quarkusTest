package org.dasun.dto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.dasun.model.Item;
import org.dasun.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@ApplicationScoped
public class BillDTO {
    private Long id;
    private LocalDate date;

    @Min(value = 1, message = "Please enter a valid amount")
    private int amount;
    private UserDTO userDTO;
    private Long userId;
    private List<BillItemDTO> billItemDTOS;
}
