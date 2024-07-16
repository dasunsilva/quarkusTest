package org.dasun.dto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ApplicationScoped
public class UserDTO {
    private Long id;

    @Min(message = "Name is too short", value = 2)
    private String name;

    @Email(message = "Email is not valid")
    private String email;

    private String phone;
    private String accountNumber;
}
