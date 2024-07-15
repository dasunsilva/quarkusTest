package org.dasun.service;

import org.dasun.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User getUser(String id);
    public String addUser(User user);
    public String updateUser(User user, String id);
    public String deleteUser(String id);
    public User strToUser(String requestBody);
}
