package com.a2zshop.microservices.userinfoservice.dto.response;

import com.a2zshop.microservices.userinfoservice.model.Address;
import com.a2zshop.microservices.userinfoservice.validation.WhitespaceValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    @NotBlank
    @Size(min=2, max=25)
    @WhitespaceValidation
    private String userName;
    @NotBlank
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;
    @NotBlank
    @Pattern(regexp="([6-9]{1}[0-9]{9})", message = "{validation.message.invalid}")
    private String mobileNumber;
    @NotNull
    @Valid
    private Address address;
}
