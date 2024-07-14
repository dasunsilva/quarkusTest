package org.dasun.DTO;

import org.dasun.Model.User;
public class UserDTOMapper {
    public PostDTO UserDTOMapper(User user) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(user.getId());
        postDTO.setName(user.getName());
        postDTO.setEmail(user.getEmail());
        postDTO.setPhone(user.getPhoneNumber());
        return postDTO;
    }
}