package com.study.DTO.Mapper;

import com.study.DTO.Records.CategoryDTO;
import com.study.Model.Category;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CategoryMapper implements Function<Category, CategoryDTO> {
    @Override
    public CategoryDTO apply(Category category) {
        return new CategoryDTO(
                category.getCod(),
                category.getName()
        );
    }
}