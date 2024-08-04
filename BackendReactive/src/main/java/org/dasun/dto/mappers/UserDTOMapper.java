package org.dasun.dto.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dasun.dto.UserDTO;
import org.dasun.model.Bill;
import org.dasun.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines the mapping between user object and userDTO object
 */
@ApplicationScoped
public class UserDTOMapper {
    /**
     * This method will map the given User object to a userDTO
     * @param user is the input from the repository
     * @return this will return a userDTO corresponding to the given item
     */
    public UserDTO mapUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhoneNumber());
        List<Long> billIds = new ArrayList<>();
        for (Bill bill : user.getBills()) {
            billIds.add(bill.getId());
        }
        userDTO.setBillIDs(billIds);
        return userDTO;

    }
    /**
     * This method will define the mapping between UserDTO and User object
     * @param userDTO will be the user input
     * @return this will return a User object which can be used to store the User in the database
     */
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
