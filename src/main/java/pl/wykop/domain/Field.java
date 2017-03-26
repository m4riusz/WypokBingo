package pl.wykop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by mariusz on 20.03.17.
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Field extends Cell {

    @ManyToOne
    private Category category;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Link confirmLink;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "field_id")
    private Set<Link> requestedLinks;
}
