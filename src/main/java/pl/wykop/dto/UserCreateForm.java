package pl.wykop.dto;

import lombok.Data;
import pl.wykop.domain.annotations.Email;
import pl.wykop.domain.annotations.Password;
import pl.wykop.domain.annotations.Username;

/**
 * Created by mariusz on 06.03.17.
 */

@Data
public class UserCreateForm {
    @Username
    private String username;
    @Password
    private String password;
    @Email
    private String email;
}
