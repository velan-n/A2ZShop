package com.a2zshop.microservices.userinfoservice.model;

import com.a2zshop.microservices.userinfoservice.validation.WhitespaceValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Entity(name="user_details")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
    @GenericGenerator(strategy = "userSequence", name = "sequence")
    @Column(name = "Id")
    private long userId;

    private String userName;
    @Column(unique = true)
    private String email;

    private String password;

    @Column(unique = true)
    private String mobileNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

}
