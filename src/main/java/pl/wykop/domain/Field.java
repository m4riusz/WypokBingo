package pl.wykop.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by mariusz on 20.03.17.
 */
@Data
@Builder
@Entity
public class Field extends Cell {

    @ManyToOne
    private Category category;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Link confirmLink;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "field_id")
    private Set<Link> requestedLinks;
}
