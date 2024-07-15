package org.dasun.dto;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Setter;

@Setter
@ApplicationScoped
public class PostDTO {
    private int id;
    private String name;
    private String email;
    private String phone;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
