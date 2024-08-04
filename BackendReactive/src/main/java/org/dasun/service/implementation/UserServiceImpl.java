package org.dasun.service.implementation;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dasun.dto.UserDTO;
import org.dasun.dto.mappers.UserDTOMapper;
import org.dasun.events.eventProducer.UserEventProducer;
import org.dasun.model.User;
import org.dasun.repo.UserRepo;
import org.dasun.service.UserService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserRepo userRepo;

    @Inject
    UserDTOMapper userDTOMapper;
    @Inject
    UserEventProducer userEventProducer;


    /**
     * This is used to validate a phone number that use inputs
     */
    private static final String phoneNumberRegex = "^\\+94\\d{9}$";
    private static final Pattern phoneNumberPattern = Pattern.compile(phoneNumberRegex);

    /**
     * {@inheritDoc}
     */
    @Override
    public Uni<List<UserDTO>> getAllUsers() {
        // Get all users as a list of DTOs
        return userRepo.getAllUsers()
                        .onItem()
                        .transform(userList ->
                                userList.stream()
                                        .map(userDTOMapper::mapUserToDTO)
                                        .collect(Collectors.toList()))
                        .onFailure()
                        .invoke(error -> System.out.println(error.getMessage()));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Uni<UserDTO> getUser(Long id) {
        return userRepo.getUserById(id)
                .onItem().transform(userDTOMapper::mapUserToDTO)
                .onFailure().transform(error -> new Exception("Error fetching user information" + error.getMessage()));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Uni<String> addUser(UserDTO userDTO) {
        return phoneNumberValidator(userDTO.getPhone())
                .flatMap(isCorrect -> {
                    if(isCorrect){
                        User tempUser = userDTOMapper.mapDTOtoUser(userDTO);
                        return userRepo.addUser(tempUser)
                                .onItem().transformToUni( item -> {
                                    userEventProducer.setUserDTO(userDTO);
                                    userEventProducer.setEvent("Add");
                                    userEventProducer.publishUserEvent();
                                    return Uni.createFrom().item("User is added successfully");
                                })
                                .onFailure().transform(error ->
                                        new Exception("Error when adding a new user " + error.getMessage()));
                    }
                    else{
                        return Uni.createFrom()
                                .failure(new Exception("Please enter a valid mobile number in the format +94xxxxxxxxx"));
                    }
                });
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Uni<String> updateUser(UserDTO userDTO, Long id){
        return phoneNumberValidator(userDTO.getPhone())
                .flatMap(isCorrect -> {
                    if(isCorrect){
                        return userRepo.getUserById(id)
                                .flatMap(existingUser -> {
                                    if(existingUser != null){
                                        existingUser.setName(userDTO.getName());
                                        existingUser.setEmail(userDTO.getEmail());
                                        existingUser.setPhoneNumber(userDTO.getPhone());
                                        existingUser.setAccountNumber(userDTO.getAccountNumber());
                                        return userRepo.addUser(existingUser)
                                                .onItem().transformToUni(item ->
                                                        Uni.createFrom().item("User is updated successfully"))
                                                .onFailure().transform(error ->
                                                        new Exception("Error when updating the user" + error.getMessage())
                                                );
                                    }else{
                                        return Uni.createFrom().failure(new Exception("User is not found with id: " + id));
                                    }
                                });
                    }else {
                        return Uni.createFrom()
                                .failure(new Exception("Please enter a valid mobile number in the format +94xxxxxxxxx"));
                    }
                });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Uni<String> deleteUser(Long id){
        return userRepo.getUserById(id)
                .flatMap(user -> {
                    return userRepo.removeUser(user)
                            .onItem().transformToUni(item ->
                                    Uni.createFrom().item("User is deleted successfully!"))
                            .onFailure().transform(error ->
                                    new Exception("Error removing the user" + error.getMessage()));
                });
    }
//
    /**
     * This method will validate the phone number of the user.
     * @param phoneNumber is the user input
     * @return this will return a boolean value
     * true - Phone number is valid
     * false - Phone number is invalid
     */
    private Uni<Boolean> phoneNumberValidator(String phoneNumber) {
        if(phoneNumber == null){
            return Uni.createFrom().item(false);
        }
        // Match regex
        Matcher matcher = phoneNumberPattern.matcher(phoneNumber);
        return Uni.createFrom().item(matcher.matches());
    }
}
