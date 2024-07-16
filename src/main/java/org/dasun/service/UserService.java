package org.dasun.service;

import org.dasun.dto.UserDTO;
import org.dasun.model.User;

import java.util.List;

public interface UserService {
    public List<UserDTO> getAllUsers();
    public UserDTO getUser(Long id);
    public String addUser(UserDTO userDTO);
    public String updateUser(UserDTO userDTO, Long id);
    public String deleteUser(Long id);
}
