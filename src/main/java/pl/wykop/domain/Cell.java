package pl.wykop.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by mariusz on 09.03.17.
 */
@Data
@Builder
@Entity
public class Cell extends AbstractEntity {

    @ManyToOne
    private Category category;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cell")
    private Link confirmLink;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cells")
    private Set<Link> requestedLinks;

    @ManyToOne
    private Board board;
}
