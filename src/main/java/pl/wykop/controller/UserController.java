package pl.wykop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.wykop.config.Route;
import pl.wykop.dto.UserCreateForm;
import pl.wykop.dto.UserDto;
import pl.wykop.exception.UserCreateException;
import pl.wykop.service.UserService;

import java.util.Locale;

/**
 * Created by mariusz on 06.03.17.
 */

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping(value = Route.USER, method = RequestMethod.POST)
    public UserDto createUser(@RequestBody UserCreateForm userCreateForm) throws UserCreateException {
        return userService.create(userCreateForm);
    }
}
