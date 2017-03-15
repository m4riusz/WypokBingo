package pl.wykop.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.userdetails.UserDetails;
import pl.wykop.domain.annotations.Email;
import pl.wykop.domain.annotations.Username;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariusz on 06.03.17.
 */
@Data
@Entity(name = "users")
public class User extends AbstractEntity implements UserDetails {
    @Username
    @Column(unique = true)
    private String username;
    @NotBlank
    private String password;
    @Email
    @Column(unique = true)
    private String email;
    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<>();
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "owner")
    private List<Board> boards;
}
