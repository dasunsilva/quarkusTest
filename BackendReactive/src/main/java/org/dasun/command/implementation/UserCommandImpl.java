package org.dasun.command.implementation;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dasun.command.UserCommand;
import org.dasun.dto.UserDTO;
import org.dasun.dto.mappers.UserDTOMapper;
import org.dasun.repo.UserRepo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class UserCommandImpl implements UserCommand {

    @Inject
    EventBus bus;

    @Inject
    UserDTOMapper userDTOMapper;

    @Inject
    UserRepo userRepo;

    /**
     * This is used to validate a phone number that use inputs
     */
    private static final String phoneNumberRegex = "^\\+94\\d{9}$";
    private static final Pattern phoneNumberPattern = Pattern.compile(phoneNumberRegex);


    @Override
    public Uni<String> addUser(UserDTO userDTO) {
        return phoneNumberValidator(userDTO.getPhone())
                .onItem()
                .transformToUni(isCorrect -> {
                    if (isCorrect) {
                        return bus.<String>request("add-user", userDTOMapper.mapDTOtoUser(userDTO))
                                .onItem().transform(Message::body);
                    }
                    else {
                        return Uni.createFrom().failure(new Exception("Enter a valid phone number in format +94xxxxxxxxx"));
                    }
                });

    }

    @Override
    @WithSession
    public Uni<String> updateUser(UserDTO userDTO, Long id) {
        return phoneNumberValidator(userDTO.getPhone())
                .onItem().transformToUni(isCorrect -> {
                    if (isCorrect) {
                        return userRepo.findById(id)
                                .onItem().ifNotNull()
                                .transformToUni(existingUser-> {
                                    existingUser.setName(userDTO.getName());
                                    existingUser.setEmail(userDTO.getEmail());
                                    existingUser.setAccountNumber(userDTO.getAccountNumber());
                                    existingUser.setPhoneNumber(userDTO.getPhone());
                                    // TODO: Add bill informations

                                    return bus.<String>request("edit-user", existingUser)
                                            .onItem().transformToUni(item ->
                                                    Uni.createFrom().item(item.body()));
                                })
                                .onItem().ifNull().failWith(new Exception("User not found with id: "  + id));
                    }
                    else {
                        return Uni.createFrom().failure(new Exception("Enter a valid phone number in format +94xxxxxxxxx"));
                    }
                });

    }

    @Override
    @WithSession
    public Uni<String> deleteUser(Long id) {
        return bus.<String>request("remove-user",id)
                .onItem().transformToUni(item ->
                        Uni.createFrom().item(item.body()));
    }

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
