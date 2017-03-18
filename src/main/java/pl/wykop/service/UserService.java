package pl.wykop.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.wykop.domain.User;
import pl.wykop.dto.UserCreateForm;
import pl.wykop.exception.UserCreateException;
import pl.wykop.exception.UserNotFoundException;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.stream.Stream;

/**
 * Created by mariusz on 06.03.17.
 */
@Service
@Transactional
public interface UserService extends UserDetailsService {

    @PreAuthorize("isAnonymous()")
    User create(UserCreateForm userCreateForm) throws UserCreateException;

    @PreAuthorize("isAuthenticated()")
    User findByUsername(String username) throws UserNotFoundException;

    @PreAuthorize("isAuthenticated()")
    Stream<User> findByNameLike(String username, Pageable pageable);

    @PreAuthorize("isAuthenticated()")
    User getCurrentUser();
}
