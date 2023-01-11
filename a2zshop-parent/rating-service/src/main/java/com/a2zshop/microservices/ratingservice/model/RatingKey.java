package com.a2zshop.microservices.ratingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable //composite key
@Data  //need hashcode and equals
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.ALWAYS)
public class RatingKey implements Serializable {
    private long userId;
    private long productId;
}
