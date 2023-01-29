package com.blog.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private Integer categoryId;

    @NotEmpty
    @Size(min = 4, message = "Title must be min of 4 characters")
    private String categoryTitle;

    @NotEmpty
    private String categoryDescription;
}
