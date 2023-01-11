package com.a2zshop.microservices.productinfoservice.model;

import com.a2zshop.microservices.productinfoservice.validation.WhitespaceValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity(name = "product_details")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ProductSequence")
    @GenericGenerator(strategy = "ProductSequence", name = "sequence")
    @Column(name = "Id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long productId;

    @NotBlank
    @Size(min = 2, max = 50)
    @WhitespaceValidation
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @Valid
    private SubCategory subCategory;

    @NotBlank
    @WhitespaceValidation
    private String description;

    private long stock;

    private BigDecimal price;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double rating;
}
