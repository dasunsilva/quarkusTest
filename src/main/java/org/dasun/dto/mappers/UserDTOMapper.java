package org.dasun.dto.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.dasun.dto.UserDTO;
import org.dasun.model.User;

@ApplicationScoped
public class UserDTOMapper {

    public UserDTO mapUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhoneNumber());
        userDTO.setBills(user.getBills());

        return userDTO;

    }

    public User mapDTOtoUser(UserDTO userDTO) {
        User user = new User();

        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhone());
        user.setAccountNumber(userDTO.getAccountNumber());

        return user;

    }
}
