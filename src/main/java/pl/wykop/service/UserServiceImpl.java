package pl.wykop.service;

import lombok.AllArgsConstructor;
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
@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public UserDto create(UserCreateForm userCreateForm) throws UserCreateException {
        if (userRepository.findByUsername(userCreateForm.getUsername()).isPresent()) {
            throw new UserCreateException("User with this username already exists!");
        } else if (userRepository.findByEmail(userCreateForm.getEmail()).isPresent()) {
            throw new UserCreateException("User with this email already exists!");
        }
        User user = new User();
        user.setUsername(userCreateForm.getUsername());
        user.setEmail(userCreateForm.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateForm.getPassword()));
        return UserMapper.INSTANCE.userToUserDto(userRepository.save(user));
    }
}
