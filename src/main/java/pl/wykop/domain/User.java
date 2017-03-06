package pl.wykop.domain;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created by mariusz on 06.03.17.
 */
@Data
@Entity
public class User extends AbstractEntity implements UserDetails {

    private String username;
    private String password;
    private String email;
    private List<Authority> authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = false;
}
