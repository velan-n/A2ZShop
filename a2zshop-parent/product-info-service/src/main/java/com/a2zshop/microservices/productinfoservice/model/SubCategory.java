package com.a2zshop.microservices.productinfoservice.model;

import com.a2zshop.microservices.productinfoservice.validation.WhitespaceValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SubCategorySequence")
    @GenericGenerator(strategy = "SubCategorySequence", name = "sequence")
    @Column(name = "Id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long subCategoryId;

    @NotBlank
    @WhitespaceValidation
    @Column(unique = true,nullable = false)
    private String subCategoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @Valid
    private Category category;

    @OneToMany(mappedBy = "subCategory", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<@Valid Product> products=new ArrayList<>();
}
