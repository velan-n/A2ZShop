package com.a2zshop.microservices.ratingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

@Entity(name = "user_ratings")
@Data
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "Id")
    private long userId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<@Valid Rating> ratings;

}
