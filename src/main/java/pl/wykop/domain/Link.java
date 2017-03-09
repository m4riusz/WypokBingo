package pl.wykop.domain;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * Created by mariusz on 09.03.17.
 */
@Data
@Builder
@Entity
public class Link extends AbstractEntity {

    @Column(unique = true)
    @URL(protocol = "http", host = "wykop", port = 80, message = "Invalid url!")
    private String url;

    @OneToOne
    private Cell cell;

    @ManyToMany
    private List<Cell> cells;
}
