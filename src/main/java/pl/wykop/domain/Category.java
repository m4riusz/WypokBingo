package pl.wykop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wykop.domain.annotations.CategoryName;

import javax.persistence.Entity;

/**
 * Created by mariusz on 09.03.17.
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Category extends AbstractEntity {

    @CategoryName
    private String name;

}
