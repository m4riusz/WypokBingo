package pl.wykop.service;

import org.springframework.stereotype.Service;
import pl.wykop.domain.Category;
import pl.wykop.dto.CategoryFormCreate;
import pl.wykop.exception.CategoryAlreadyExistsExistException;
import pl.wykop.exception.CategoryNotFoundException;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.stream.Stream;

/**
 * Created by mariusz on 24.03.17.
 */
@Service
@Transactional
public interface CategoryService {

    Category addCategory(CategoryFormCreate categoryFormCreate) throws CategoryAlreadyExistsExistException;

    Category findByName(String name) throws CategoryNotFoundException;

    Stream<Category> findByNameLike(String name, Pageable pageable);

    Stream<Category> getAllCategories(Pageable pageable);

}
