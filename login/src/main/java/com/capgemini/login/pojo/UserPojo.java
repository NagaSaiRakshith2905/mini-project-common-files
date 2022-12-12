package com.capgemini.login.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPojo {
//    @NotNull(message = "UserName cannot be empty.")
    private String userName;
//    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
//            "A-Z]{2,7}$", message = "Invalid Email.")
    private String email;
//    @Pattern(regexp = "^(?=.*[0-9])"+"(?=.*[a-z])(?=.*[A-Z])"+"(?=.*[@#$%^&+=])"+"(?=\\S+$).{8,20}$",
//            message = "Password must contains:\n"+
//                    "At least 8 characters and at most 20 characters.\n" +
//                    "At least one digit.\n" +
//                    "At least one upper case alphabet.\n" +
//                    "At least one lower case alphabet.\n" +
//                    "At least one special character which includes !@#$%&*()-+=^.\n" +
//                    "Must not contain any white space.")
    private String password;
}
