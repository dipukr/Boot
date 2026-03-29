package com.common;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Author {

    @Id
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "author")
    private List<Book> books;
}