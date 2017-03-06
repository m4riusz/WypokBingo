package pl.wykop.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by mariusz on 06.03.17.
 */

@Data
public class UserDto {
    private Long id;
    private String createDate;
    private String modifyDate;
    private String username;
    private String email;
    private List<String> roles;
    private boolean active;
}
