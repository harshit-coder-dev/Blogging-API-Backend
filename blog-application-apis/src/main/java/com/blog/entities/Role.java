package com.blog.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Role {

    @Id
    private Integer id;
    private String name;
}
