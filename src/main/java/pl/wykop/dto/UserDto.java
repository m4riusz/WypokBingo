package pl.wykop.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by mariusz on 06.03.17.
 */

@Data
public class UserDto {
    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private String username;
    private String email;
    private List<String> roles;
    private boolean active;
}
