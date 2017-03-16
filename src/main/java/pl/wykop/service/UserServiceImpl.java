package pl.wykop.service;

import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wykop.domain.User;
import pl.wykop.dto.UserCreateForm;
import pl.wykop.dto.UserDto;
import pl.wykop.exception.UserCreateException;
import pl.wykop.exception.UserNotFoundException;
import pl.wykop.mapper.UserMapper;
import pl.wykop.repository.UserRepository;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

import static java.util.stream.Collectors.toList;

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

    @Override
    public UserDto findByUsername(String username) throws UserNotFoundException {
        return UserMapper.INSTANCE.userToUserDto(userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(getMessage("user.find.error.username"))));
    }

    @Override
    public List<UserDto> findByNameLike(String username, Pageable pageable) {
        return userRepository.findByUsernameLike(username, pageable).map(UserMapper.INSTANCE::userToUserDto).collect(toList());
    }

    @Override
    public UserDto getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return UserMapper.INSTANCE.userToUserDto(loadUserByUsername(username));
    }
}
