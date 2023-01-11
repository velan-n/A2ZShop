package com.a2zshop.microservices.ratingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Data
@NoArgsConstructor
public class Rating {

    @EmbeddedId
    @JsonIgnore
    private RatingKey ratingKey;

    @ManyToOne(cascade = CascadeType.MERGE)
    @MapsId("userId")
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @MapsId("productId")
    private Product product;

    @Min(1)
    @Max(5)
    private short ratingValue;

    public void initKey(){
       RatingKey key = new RatingKey(user.getUserId(),product.getProductId());
       this.setRatingKey(key);
   }
}
