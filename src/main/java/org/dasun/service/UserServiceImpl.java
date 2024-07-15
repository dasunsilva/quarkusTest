package org.dasun.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dasun.dto.UserDTOMapper;
import org.dasun.model.User;
import org.dasun.dto.PostDTO;
import org.dasun.repo.UserRepo;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class UserServiceImpl implements UserService{

    @Inject
    UserRepo userRepo;

    @Inject
    UserDTOMapper userDTOMapper;

    private static final String phoneNumberRegex = "^\\+94\\d{9}$";
    private static final Pattern phoneNumberPattern = Pattern.compile(phoneNumberRegex);

    @Override
    public List<User> getAllUsers() {
        return userRepo.getUserList();
    }

    @Override
    public String getUser(String id) {
        User tempUser = userRepo.findByID(id);
        PostDTO tempDTO = userDTOMapper.mapUserAndDTO(tempUser);
        return tempDTO.toString();
    }

    @Override
    public String addUser(String request) {
        User tempUser = strToUser(request);
        if(phoneNumberValidator(tempUser)){
            return userRepo.addUser(tempUser);
        }else{
            return "Enter a valid phone number in the form +94xxxxxxxxx";
        }
    }

    private boolean phoneNumberValidator(User tempUser) {
        if(tempUser.getPhoneNumber() == null){
            return false;
        }
        Matcher matcher = phoneNumberPattern.matcher(tempUser.getPhoneNumber());
        return matcher.matches();
    }

    @Override
    public String updateUser(String request, String id) {
        User tempUser = strToUser(request);
        if(phoneNumberValidator(tempUser)){
            return userRepo.updateUser(tempUser, id);
        }else{
            return "Enter a valid phone number in the form +94xxxxxxxxx";
        }
    }

    @Override
    public String deleteUser(String id) {
        return userRepo.deleteUser(id);
    }

    @Override
    public User strToUser(String requestBody) {
        JSONObject jsonObject = new JSONObject(requestBody);
        String name = jsonObject.getString("name");
        String acc = jsonObject.getString("acc");
        String email = jsonObject.getString("email");
        String phone = jsonObject.getString("phone");

        User tempUser = new User();
        tempUser.setName(name);
        tempUser.setEmail(email);
        tempUser.setAccNumber(acc);
        tempUser.setPhoneNumber(phone);
        return tempUser;
    }

    @Override
    public String getAllUsersAsString() {
        List<PostDTO> tempDTOList = new ArrayList<>();
        List<User> userList = getAllUsers();

        for (User tempUser:userList) {
            PostDTO postDTO = userDTOMapper.mapUserAndDTO(tempUser);
            tempDTOList.add(postDTO);
        }
        return tempDTOList.toString();
    }
}
