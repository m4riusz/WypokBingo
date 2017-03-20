package pl.wykop.service;

import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wykop.domain.User;
import pl.wykop.dto.UserCreateForm;
import pl.wykop.exception.UserCreateException;
import pl.wykop.exception.UserNotFoundException;
import pl.wykop.repository.UserRepository;
import pl.wykop.util.ConfigSource;
import pl.wykop.util.LocaleMessageSource;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.stream.Stream;

/**
 * Created by mariusz on 06.03.17.
 */
@Data
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfigSource configSource;
    private final LocaleMessageSource localeMessageSource;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public User create(UserCreateForm userCreateForm) throws UserCreateException {
        if (userRepository.findByUsername(userCreateForm.getUsername()).isPresent()) {
            throw new UserCreateException(localeMessageSource.getMessage("user.create.error.username"));
        } else if (userRepository.findByEmail(userCreateForm.getEmail()).isPresent()) {
            throw new UserCreateException(localeMessageSource.getMessage("user.create.error.email"));
        }
        User user = new User();
        user.setUsername(userCreateForm.getUsername());
        user.setEmail(userCreateForm.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateForm.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) throws UserNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(localeMessageSource.getMessage("user.find.error.username")));
    }

    @Override
    public Stream<User> findByNameLike(String username, Pageable pageable) {
        return userRepository.findByUsernameLike(username, pageable);
    }

    @Override
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return loadUserByUsername(username);
    }
}
