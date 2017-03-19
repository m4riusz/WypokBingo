package pl.wykop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wykop.dto.UserCreateForm;
import pl.wykop.dto.UserDto;
import pl.wykop.exception.UserCreateException;
import pl.wykop.exception.UserNotFoundException;
import pl.wykop.mapper.UserMapper;
import pl.wykop.service.UserService;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static pl.wykop.config.Route.USER;
import static pl.wykop.config.Route.USER_BY_USERNAME;

/**
 * Created by mariusz on 06.03.17.
 */

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @RequestMapping(value = USER, method = POST)
    public UserDto createUser(@RequestBody UserCreateForm userCreateForm) throws UserCreateException {
        return userMapper.userToUserDto(userService.create(userCreateForm));
    }

    @RequestMapping(value = {USER, USER_BY_USERNAME}, method = GET)
    public UserDto getCurrentLoggedUser(@PathVariable Optional<String> username) throws UserNotFoundException {
        return userMapper.userToUserDto(username.isPresent() ? userService.findByUsername(username.get()) : userService.getCurrentUser());
    }


}
