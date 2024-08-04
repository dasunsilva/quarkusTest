package org.dasun.dto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * This class defines the fields of the User data transfer object
 */
@Getter
@Setter
@ApplicationScoped
public class UserDTO {
    private Long id;

    @Size(message = "Name is too short", min = 2)
    private String name;

    @Email(message = "Email is not valid")
    private String email;

    private String phone;
    private String accountNumber;
    private List<Long> billIDs;
}
