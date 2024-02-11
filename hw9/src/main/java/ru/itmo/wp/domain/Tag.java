package ru.itmo.wp.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @noinspection unused
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Tag {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String name;

    /**
     * @noinspection unused
     */
    public Tag() {
    }

    public Tag(@NotNull String tag) {
        this.name = tag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
