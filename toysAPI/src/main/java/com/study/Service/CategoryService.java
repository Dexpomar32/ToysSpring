package com.study.Service;

import com.study.DTO.Mapper.CategoryMapper;
import com.study.DTO.Records.CategoryDTO;
import com.study.Model.Category;
import com.study.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public Optional<List<CategoryDTO>> getAll() {
        List<CategoryDTO> categoryDTOList = categoryRepository
                .findAll()
                .stream()
                .map(categoryMapper)
                .collect(Collectors.toList());

        return Optional.of(categoryDTOList);
    }

    public Optional<CategoryDTO> getOne(String cod) {
        return Optional.ofNullable(categoryRepository.findByCod(cod))
                .map(categoryMapper);
    }

    public Optional<CategoryDTO> create(Category newCategory) {
        if (newCategory.getCod() == null || newCategory.getCod().isEmpty() ||
                newCategory.getName() == null || newCategory.getName().isEmpty()) {
            return Optional.empty();
        }

        Category category = new Category(
                newCategory.getId(),
                newCategory.getCod(),
                newCategory.getName()
        );

        categoryRepository.save(category);
        return Optional.ofNullable(categoryMapper.apply(category));
    }

    public Optional<CategoryDTO> update(Category updateCategory) {
        Optional<Category> optionalCategory = Optional.ofNullable(categoryRepository.findByCod(updateCategory.getCod()));

        return optionalCategory.map(category -> {
            category.setName(!updateCategory.getName().isEmpty() ? updateCategory.getName() : category.getName());
            categoryRepository.save(category);
            return categoryMapper.apply(category);
        });
    }

    public Optional<CategoryDTO> delete(String cod) {
        Optional<Category> optionalCategory = Optional.ofNullable(categoryRepository.findByCod(cod));

        return optionalCategory.map(category -> {
            optionalCategory.ifPresent(categoryRepository::delete);
            return categoryMapper.apply(category);
        });
    }
}
