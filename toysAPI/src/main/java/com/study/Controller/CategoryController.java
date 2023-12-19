package com.study.Controller;

import com.study.DTO.Records.CategoryDTO;
import com.study.Model.Category;
import com.study.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Optional<List<CategoryDTO>>> getAll() {
        Optional<List<CategoryDTO>> categoryDTOList = categoryService.getAll();
        return categoryDTOList.isPresent() ?
                new ResponseEntity<>(categoryDTOList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getOne")
    public ResponseEntity<Optional<CategoryDTO>> getOne(@RequestParam String cod) {
        Optional<CategoryDTO> categoryDTOList = categoryService.getOne(cod);
        return categoryDTOList.isPresent() ?
                new ResponseEntity<>(categoryDTOList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<Optional<CategoryDTO>> create(@RequestBody Category newCategory) {
        Optional<CategoryDTO> categoryDTO = categoryService.create(newCategory);
        return categoryDTO.isPresent() ?
                new ResponseEntity<>(categoryDTO, HttpStatus.CREATED) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/update")
    public ResponseEntity<Optional<CategoryDTO>> update(@RequestBody Category newCategory) {
        Optional<CategoryDTO> categoryDTO = categoryService.update(newCategory);
        return categoryDTO.isPresent() ?
                new ResponseEntity<>(categoryDTO, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Optional<CategoryDTO>> delete(@RequestParam String cod) {
        Optional<CategoryDTO> categoryDTO = categoryService.delete(cod);
        return categoryDTO.isPresent() ?
                new ResponseEntity<>(categoryDTO, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}