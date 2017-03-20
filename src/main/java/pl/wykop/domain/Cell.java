package pl.wykop.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.Entity;

/**
 * Created by mariusz on 09.03.17.
 */
@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(name = "field", value = Field.class),
        @JsonSubTypes.Type(name = "image", value = Image.class)
})
public abstract class Cell extends AbstractEntity {

}
