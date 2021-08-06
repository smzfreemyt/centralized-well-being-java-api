package com.cewb.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Category {

    private Long id;

    private String name;

    private String slug;

    public Category(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }
}
