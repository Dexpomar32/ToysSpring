package com.study.DTO.Records;

import com.study.Model.Category;

public record ToyDTO(
        String cod,
        String name,
        Double price,
        Integer quantity,
        String country,
        Integer minimAge,
        Category category
) {}
