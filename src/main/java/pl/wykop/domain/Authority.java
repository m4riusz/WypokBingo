package pl.wykop.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by mariusz on 06.03.17.
 */
@Data
@Entity
public class Authority extends AbstractEntity implements GrantedAuthority {
    private String authority;
    private String description;
    @ManyToMany
    private List<User> users;
}
