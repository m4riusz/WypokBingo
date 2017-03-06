package pl.wykop.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by mariusz on 06.03.17.
 */
@Data
public class Authority implements GrantedAuthority {
    private String authority;
}
