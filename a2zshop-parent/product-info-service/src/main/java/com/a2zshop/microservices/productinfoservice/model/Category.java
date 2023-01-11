package com.a2zshop.microservices.productinfoservice.model;

import com.a2zshop.microservices.productinfoservice.validation.WhitespaceValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CategorySequence")
    @GenericGenerator(strategy = "CategorySequence", name = "sequence")
    @Column(name = "Id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long categoryId;

    @NotBlank
    @WhitespaceValidation
    @Column(unique = true, nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<@Valid SubCategory> subCategories=new ArrayList<>();

//    public void addSubCategory(@Valid SubCategory subCategory){
//        if(subCategory!=null)
//            subCategories.add(subCategory);
//    }
}
