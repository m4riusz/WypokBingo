package pl.wykop.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.wykop.dto.UserCreateForm;
import pl.wykop.dto.UserDto;
import pl.wykop.exception.UserCreateException;
import pl.wykop.exception.UserException;

/**
 * Created by mariusz on 06.03.17.
 */
@Service
public interface UserService extends UserDetailsService{

    UserDto create(UserCreateForm userCreateForm) throws UserCreateException;
}
