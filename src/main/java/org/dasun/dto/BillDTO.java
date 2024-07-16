package org.dasun.dto;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@ApplicationScoped
public class BillDTO {
    private Long id;
    private LocalDate date;
    private int amount;
}
