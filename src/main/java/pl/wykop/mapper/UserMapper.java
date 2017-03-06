package pl.wykop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.wykop.domain.Authority;
import pl.wykop.domain.User;
import pl.wykop.dto.UserDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by mariusz on 06.03.17.
 */

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    String DATE_FORMAT = "HH:MM:SS dd/mm/yyyy";

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createDate", target = "createDate")
    @Mapping(source = "updateDate", target = "modifyDate")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "authorities", target = "roles")
    @Mapping(source = "accountNonLocked", target = "active")
    UserDto userToUserDto(User user);

    default String authorityToString(Authority authority) {
        return authority.getAuthority();
    }

    default String localDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
