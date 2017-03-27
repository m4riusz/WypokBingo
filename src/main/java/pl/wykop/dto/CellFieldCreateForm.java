package pl.wykop.dto;

import lombok.Data;
import pl.wykop.domain.annotations.CategoryName;

/**
 * Created by mariusz on 24.03.17.
 */
@Data
public class CellFieldCreateForm {

    @CategoryName
    private String categoryName;
}
