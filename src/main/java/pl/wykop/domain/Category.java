package pl.wykop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by mariusz on 09.03.17.
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Category extends AbstractEntity {

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Field> cells;

}
