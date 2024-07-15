package org.dasun.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.dasun.model.User;
import org.dasun.dto.PostDTO;
import org.dasun.repo.UserRepo;
import org.json.JSONObject;

import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService{

    private UserRepo userRepo = new UserRepo();

    private PostDTO postDTO = new PostDTO();

    @Override
    public List<User> getAllUsers() {
        return userRepo.getUserList();
    }

    @Override
    public User getUser(String id) {
        return userRepo.getUser(id);
    }

    @Override
    public String addUser(User user) {
        return userRepo.addUser(user);
    }

    @Override
    public String updateUser(User user, String id) {
        return userRepo.updateUser(user, id);
    }

    @Override
    public String deleteUser(String id) {
        return userRepo.deleteUser(id);
    }

    @Override
    public User strToUser(String requestBody) {
        JSONObject jsonObject = new JSONObject(requestBody);
        int id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        String acc = jsonObject.getString("acc");
        String email = jsonObject.getString("email");
        String phone = jsonObject.getString("phone");
        User user = new User(id,name,acc,email,phone);
        return user;
    }
}
