package org.dasun.service.implementation;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dasun.dto.UserDTO;
import org.dasun.dto.mappers.UserDTOMapper;
import org.dasun.repo.UserRepo;
import org.dasun.service.UserService;
import org.hibernate.reactive.mutiny.Mutiny;

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

    /**
     * {@inheritDoc}
     */
    @Override
    @WithSession
    public Uni<List<UserDTO>> getAllUsers() {
        // Get all users as a list of DTOs
        // TODO : Fetch data when the entities have fetch type lazy
        return userRepo.listAll()
                .onItem().transform(users ->
                        users.stream().map(userDTOMapper::mapUserToDTO)
                                .collect(Collectors.toList()))
                .onFailure().transform(error ->
                        new Exception("User Fetching Users failed with message: " + error.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @WithSession
    public Uni<UserDTO> getUser(Long id) {
        return userRepo.findById(id)
                .onItem()
                .transform(userDTOMapper::mapUserToDTO)
                .onFailure().transform(error ->
                        new Exception("User Fetching User Information. failed with message: " + error.getMessage()));
    }
}
