package pl.wykop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.wykop.config.Route;
import pl.wykop.dto.CategoryDto;
import pl.wykop.dto.CategoryFormCreate;
import pl.wykop.exception.CategoryAlreadyExistsExistException;
import pl.wykop.mapper.CategoryMapper;
import pl.wykop.service.CategoryService;

import javax.validation.Valid;

/**
 * Created by mariusz on 26.03.17.
 */
@RestController
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @RequestMapping(value = Route.CATEGORY_URL, method = RequestMethod.POST)
    public CategoryDto createCategory(@Valid @RequestBody CategoryFormCreate categoryFormCreate) throws CategoryAlreadyExistsExistException {
        return categoryMapper.categoryToCategoryDto(categoryService.addCategory(categoryFormCreate));
    }
}
