package org.dasun.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_account_number",nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_phone_number", unique = true, nullable = false)
    private String phoneNumber;
}
