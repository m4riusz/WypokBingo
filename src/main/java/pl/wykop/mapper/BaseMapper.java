package pl.wykop.mapper;

import org.mapstruct.Mapper;
import pl.wykop.domain.Authority;
import pl.wykop.domain.Category;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by mariusz on 17.03.17.
 */
@Mapper(componentModel = "spring")
public interface BaseMapper {
    String DATE_FORMAT = "HH:MM:SS dd/mm/yyyy";

    default String localDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    default String authorityToString(Authority authority) {
        return authority.getAuthority();
    }

    default String categoryToString(Category category) {
        return category.getName();
    }
}
