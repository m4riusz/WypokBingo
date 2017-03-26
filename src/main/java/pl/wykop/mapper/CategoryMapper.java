package pl.wykop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.wykop.domain.Category;
import pl.wykop.dto.CategoryDto;

/**
 * Created by mariusz on 26.03.17.
 */
@Mapper(componentModel = "spring", uses = BaseMapper.class)
public interface CategoryMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createDate", target = "createDate")
    @Mapping(source = "name", target = "name")
    CategoryDto categoryToCategoryDto(Category category);
}
