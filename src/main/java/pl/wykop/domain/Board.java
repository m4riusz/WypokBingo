package pl.wykop.domain;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mariusz on 09.03.17.
 */
@Data
@Builder
@Entity
public class Board extends AbstractEntity {

    @NotBlank
    @Length(min = 1,max = 255)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private BingoImage bingoImage;

    @ManyToOne
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "board")
    private List<Cell> cells;

}
