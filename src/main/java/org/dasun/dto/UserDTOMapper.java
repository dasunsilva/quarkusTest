package org.dasun.dto;

import jakarta.enterprise.context.ApplicationScoped;
import org.dasun.model.User;

@ApplicationScoped
public class UserDTOMapper {
    public PostDTO mapUserAndDTO(User user) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(user.getId());
        postDTO.setName(user.getName());
        postDTO.setEmail(user.getEmail());
        postDTO.setPhone(user.getPhoneNumber());
        return postDTO;
    }
}