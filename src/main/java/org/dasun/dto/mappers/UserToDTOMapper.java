package org.dasun.dto.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.dasun.dto.UserDTO;
import org.dasun.model.User;

@ApplicationScoped
public class UserToDTOMapper {

    public UserDTO mapUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhoneNumber());

        return userDTO;

    }
}
