package pl.wykop.service;

import lombok.Data;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wykop.domain.User;
import pl.wykop.dto.UserCreateForm;
import pl.wykop.dto.UserDto;
import pl.wykop.exception.UserCreateException;
import pl.wykop.mapper.UserMapper;
import pl.wykop.repository.UserRepository;

import javax.transaction.Transactional;

/**
 * Created by mariusz on 06.03.17.
 */
@Data
@Service
@Transactional
public class UserServiceImpl extends AbstractService implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public UserDto create(UserCreateForm userCreateForm) throws UserCreateException {
        if (userRepository.findByUsername(userCreateForm.getUsername()).isPresent()) {
            throw new UserCreateException(getMessage("user.create.error.username"));
        } else if (userRepository.findByEmail(userCreateForm.getEmail()).isPresent()) {
            throw new UserCreateException(getMessage("user.create.error.email"));
        }
        User user = new User();
        user.setUsername(userCreateForm.getUsername());
        user.setEmail(userCreateForm.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateForm.getPassword()));
        return UserMapper.INSTANCE.userToUserDto(userRepository.save(user));
    }
}
