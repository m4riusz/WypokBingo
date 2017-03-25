package pl.wykop.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import pl.wykop.domain.Category;
import pl.wykop.dto.CategoryFormCreate;
import pl.wykop.exception.CategoryAlreadyExistsExistException;
import pl.wykop.exception.CategoryNotFoundException;
import pl.wykop.repository.CategoryRepository;
import pl.wykop.util.LocaleMessageSource;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.stream.Stream;

/**
 * Created by mariusz on 25.03.17.
 */
@Data
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final LocaleMessageSource localeMessageSource;

    @Override
    public Category addCategory(CategoryFormCreate categoryFormCreate) throws CategoryAlreadyExistsExistException {
        if (categoryRepository.findByName(categoryFormCreate.getName()).isPresent()) {
            throw new CategoryAlreadyExistsExistException("category.create.error.name");
        }
        Category category = Category.builder()
                .name(categoryFormCreate.getName())
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public Category findByName(String name) throws CategoryNotFoundException {
        return categoryRepository.findByName(name).orElseThrow(() -> new CategoryNotFoundException(localeMessageSource.getMessage("category.find.error.name")));
    }

    @Override
    public Stream<Category> findByNameLike(String name, Pageable pageable) {
        return categoryRepository.findByNameLike(name, pageable);
    }

    @Override
    public Stream<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
}
