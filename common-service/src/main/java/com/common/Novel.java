package com.common;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Novel {

    @Id
    private Integer id;
    private String name;

    @ManyToMany
    @JoinTable(name = "novel_writer", 
    	joinColumns = @JoinColumn(name = "novel_id"),
    	inverseJoinColumns = @JoinColumn(name = "writer_id"))
    private List<Writer> writers;
}
