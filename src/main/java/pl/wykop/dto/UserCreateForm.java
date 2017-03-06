package pl.wykop.dto;

import lombok.Data;
import pl.wykop.domain.Email;
import pl.wykop.domain.Password;
import pl.wykop.domain.Username;

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
