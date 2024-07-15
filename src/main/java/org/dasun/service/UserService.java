package org.dasun.service;

import org.dasun.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public String getUser(String id);
    public String addUser(String request);
    public String updateUser(String request, String id);
    public String deleteUser(String id);
    public User strToUser(String requestBody);
    public String getAllUsersAsString();
}
