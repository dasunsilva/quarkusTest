package org.dasun.service.implementation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dasun.dto.mappers.DTOtoUserMapper;
import org.dasun.dto.mappers.UserToDTOMapper;
import org.dasun.model.User;
import org.dasun.dto.UserDTO;
import org.dasun.repo.UserRepo;
import org.dasun.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserRepo userRepo;

    @Inject
    DTOtoUserMapper DTOtoUserMapper;

    @Inject
    UserToDTOMapper userToDTOMapper;

    private static final String phoneNumberRegex = "^\\+94\\d{9}$";
    private static final Pattern phoneNumberPattern = Pattern.compile(phoneNumberRegex);

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> tempUser = userRepo.getUserList();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : tempUser) {
            userDTOList.add(userToDTOMapper.mapUserToDTO(user));
        }
        return userDTOList;
    }

    @Override
    public UserDTO getUser(Long id) {
        User tempUser = userRepo.findByID(id);
        return userToDTOMapper.mapUserToDTO(tempUser);
    }

    @Override
    public String addUser(UserDTO userDTO) {
        if(phoneNumberValidator(userDTO.getPhone())){
            User tempUser = DTOtoUserMapper.mapDTOtoUser(userDTO);
            return userRepo.addUser(tempUser);
        }else{
            return "Enter a valid phone number in the form +94xxxxxxxxx";
        }
    }

    private boolean phoneNumberValidator(String phoneNumber) {
        if(phoneNumber == null){
            return false;
        }
        Matcher matcher = phoneNumberPattern.matcher(phoneNumber);
        return matcher.matches();
    }

    @Override
    public String updateUser(UserDTO userDTO, Long id) {
        if(phoneNumberValidator(userDTO.getPhone())){
            User tempUser = DTOtoUserMapper.mapDTOtoUser(userDTO);
            return userRepo.updateUser(tempUser, id);
        }else{
            return "Enter a valid phone number in the form +94xxxxxxxxx";
        }
    }

    @Override
    public String deleteUser(Long id) {
        return userRepo.deleteUser(id);
    }
}
