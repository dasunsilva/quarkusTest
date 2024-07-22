package org.dasun.service.implementation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.dasun.dto.mappers.ItemDTOMapper;
import org.dasun.dto.mappers.UserDTOMapper;
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
    UserDTOMapper userDTOMapper;

    /**
     * This is used to validate a phone number that use inputs
     */
    private static final String phoneNumberRegex = "^\\+94\\d{9}$";
    private static final Pattern phoneNumberPattern = Pattern.compile(phoneNumberRegex);

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserDTO> getAllUsers() {
        // Get all users as a list of DTOs
        List<User> tempUser = userRepo.listAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : tempUser) {
            userDTOList.add(userDTOMapper.mapUserToDTO(user));
        }
        return userDTOList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDTO getUser(Long id) {
        // Get user using ID
        User tempUser = userRepo.findById(id);
        return userDTOMapper.mapUserToDTO(tempUser);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public String addUser(UserDTO userDTO) {
        // To validate the phone number
        if(phoneNumberValidator(userDTO.getPhone())){
            // Create a user using DTO given
            User tempUser = userDTOMapper.mapDTOtoUser(userDTO);
            try { // Save the user
                userRepo.persist(tempUser);
                return "User is added succesfully";
            }catch (Exception e){
                return "User is not added. " + e.getMessage();
            }
        }else{
            return "Enter a valid phone number in the form +94xxxxxxxxx";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public String updateUser(UserDTO userDTO, Long id) {
        // To validate the phone number
        if(phoneNumberValidator(userDTO.getPhone())){
            // Temp user will be the user input
            User tempUser = userDTOMapper.mapDTOtoUser(userDTO);

            // We fetch the user using id, and update the old user using the temp user
            User newUser = userRepo.findById(id);
            newUser.setName(tempUser.getName());
            newUser.setEmail(tempUser.getEmail());
            newUser.setPhoneNumber(tempUser.getPhoneNumber());
            newUser.setAccountNumber(tempUser.getAccountNumber());

            try{ // Save the new user aka update
                userRepo.persist(newUser);
                return "User is updated succesfully";
            }catch (Exception e){
                return "User is not updated. " + e.getMessage();
            }
        }else{
            return "Enter a valid phone number in the form +94xxxxxxxxx";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public String deleteUser(Long id) {
        User tempUser = userRepo.findById(id);
        try { // Delete the tempUser
            userRepo.delete(tempUser);
            return "User is deleted succesfully";
        }catch (Exception e){
            return "User delete failed. " + e.getMessage();
        }
    }

    /**
     * This method will validate the phone number of the user.
     * @param phoneNumber is the user input
     * @return this will return a boolean value
     * true - Phone number is valid
     * false - Phone number is invalid
     */
    private boolean phoneNumberValidator(String phoneNumber) {
        if(phoneNumber == null){
            return false;
        }
        // Match regex
        Matcher matcher = phoneNumberPattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
