package org.dasun.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "account_number",nullable = false, unique = true)
    private String accNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;
}
