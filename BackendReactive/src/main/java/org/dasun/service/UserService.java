package org.dasun.service;

import io.smallrye.mutiny.Uni;
import org.dasun.dto.UserDTO;

import java.util.List;

/**
 * This interface defines the user related services
 */
public interface UserService {

    /**
     * This method is used to get all the users from the bill repo
     *
     * @return a list of userDTO that can be used to display the results.
     */
    public Uni<List<UserDTO>> getAllUsers();

    /**
     * This method is used to get a single user information from the user repo
     *
     * @param id is the id of the user that need to be displayed
     * @return a UserDTO that can be used to display the results.
     */
    public Uni<UserDTO> getUser(Long id);
}
