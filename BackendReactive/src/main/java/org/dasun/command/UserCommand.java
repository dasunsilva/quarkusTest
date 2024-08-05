package org.dasun.command;

import io.smallrye.mutiny.Uni;
import org.dasun.dto.UserDTO;

public interface UserCommand {

    /**
     * This is used to add a user to the database
     *
     * @param userDTO is the user input. It will be taken as a userDTO
     * @return this will return a string indicating the status of the transaction
     */
    public Uni<String> addUser(UserDTO userDTO);

    /**
     * This method is used to update a user in the user repository
     *
     * @param userDTO User input will be taken as a userDTO
     * @param id      is the id of the user that need to be updated
     * @return this will return a string indicating the status of the transaction
     */
    public Uni<String> updateUser(UserDTO userDTO, Long id);

    /**
     * This method is used to delete a user in the user repository
     * @param id is the id of the user that need to be deleted
     * @return this will return a string indicating the status of the transaction
     */
    public Uni<String> deleteUser(Long id);

}
