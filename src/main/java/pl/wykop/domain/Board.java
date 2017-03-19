package pl.wykop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import pl.wykop.domain.annotations.BoardName;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mariusz on 09.03.17.
 */
@Data
@Builder
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "owner_id"})
})
@NoArgsConstructor
@AllArgsConstructor
public class Board extends AbstractEntity {

    @BoardName
    private String name;

    @Length(max = 255)
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bingo_image_id")
    private BingoImage bingoImage;

    @ManyToOne
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "board_id")
    private List<Cell> cells;

}
