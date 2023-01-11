package com.a2zshop.microservices.ratingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

@Entity(name = "product_ratings")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @Column(name = "Id")
    private long productId;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<@Valid Rating> ratings;
}
