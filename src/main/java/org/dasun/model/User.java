package org.dasun.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private int id;
    private String name;
    private String accNumber;
    private String email;
    private String phoneNumber;
}
