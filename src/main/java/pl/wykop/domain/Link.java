package pl.wykop.domain;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by mariusz on 09.03.17.
 */
@Data
@Builder
@Entity
public class Link extends AbstractEntity {

    @Column(updatable = false, nullable = false)
    @URL(protocol = "http", host = "wykop", port = 80, message = "Invalid url!")
    private String url;

}
