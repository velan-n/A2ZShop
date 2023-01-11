package com.a2zshop.microservices.userinfoservice.model;

import com.a2zshop.microservices.userinfoservice.validation.WhitespaceValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressSequence")
    @GenericGenerator(strategy = "addressSequence", name = "sequence")
    @Column(name = "Id")
    @JsonIgnore
    private long addrId;

    @NotBlank
    @WhitespaceValidation
    private String doorNo;

    @NotBlank
    @WhitespaceValidation
    private String street;

    @NotBlank
    @WhitespaceValidation
    private String city;

    @NotBlank
    @WhitespaceValidation
    private String state;

    @NotBlank
    @Pattern(regexp="([0-9]{6})", message = "Invalid pincode")
    @WhitespaceValidation
    private String pincode;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

}
