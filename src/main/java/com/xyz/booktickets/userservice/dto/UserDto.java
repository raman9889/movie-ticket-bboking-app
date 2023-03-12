package com.xyz.booktickets.userservice.dto;

import com.xyz.booktickets.userservice.model.UserTypeEnum;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    @NotEmpty
    @Size(min = 2, message = "user name should have at least 2 characters")
    private final String userName;
    @NotEmpty
    @Size(min = 8, message = "password should have at least 8 characters")
    private final String password;
    @Email
    private final String emailId;
    @NotNull
    private final UserTypeEnum userType;

}
