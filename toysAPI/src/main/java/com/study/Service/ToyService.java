package com.study.Service;

import com.study.DTO.Mapper.ToyMapper;
import com.study.DTO.Records.ToyDTO;
import com.study.Model.Category;
import com.study.Model.Toy;
import com.study.Model.PopularProduct;
import com.study.Repository.CategoryRepository;
import com.study.Repository.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ToyService {
    private final ToyRepository toyRepository;
    private final CategoryRepository categoryRepository;
    private final ToyMapper toyMapper;

    @Autowired
    public ToyService(ToyRepository toyRepository, ToyMapper toyMapper, CategoryRepository categoryRepository) {
        this.toyRepository = toyRepository;
        this.toyMapper = toyMapper;
        this.categoryRepository = categoryRepository;
    }

    public Optional<List<ToyDTO>> getAll() {
        List<ToyDTO> toyDTOList = toyRepository
                .findAll()
                .stream()
                .map(toyMapper)
                .toList();

        return Optional.of(toyDTOList);
    }

    public Optional<ToyDTO> getOne(String cod) {
        return Optional.ofNullable(toyRepository.findByCod(cod))
                .map(toyMapper);
    }

    public Optional<ToyDTO> create(Toy newToy) {
        if (isToyInvalid(newToy)) {
            return Optional.empty();
        }

        Category category = categoryRepository.findByCod(newToy.getCategory().getCod());

        Toy toy;
        if (category != null) {
            toy = createToyWithExistingCategory(newToy, category);
        } else {
            Category newCategory = newToy.getCategory();
            categoryRepository.save(newCategory);
            toy = createToyWithNewCategory(newToy, newCategory);
        }

        toyRepository.save(toy);
        return Optional.ofNullable(toyMapper.apply(toy));
    }

    public Optional<ToyDTO> update(Toy updateToy) {
        Optional<Toy> optionalToy = Optional.ofNullable(toyRepository.findByCod(updateToy.getCod()));

        return optionalToy.map(toy -> {
            toy.setName(!updateToy.getName().isEmpty() ? updateToy.getName() : toy.getName());
            toy.setPrice(updateToy.getPrice() != null ? updateToy.getPrice() : toy.getPrice());
            toy.setQuantity(updateToy.getQuantity() != null ? updateToy.getQuantity() : toy.getQuantity());
            toy.setCountry(!updateToy.getCountry().isEmpty() ? updateToy.getCountry() : toy.getCountry());
            toy.setMinimAge(updateToy.getMinimAge() != null ? updateToy.getMinimAge() : toy.getMinimAge());
            toy.setCategory(updateToy.getCategory() != null ? updateToy.getCategory() : toy.getCategory());

            toyRepository.save(toy);
            return toyMapper.apply(toy);
        });
    }

    public Optional<ToyDTO> delete(String cod) {
        Optional<Toy> optionalToy = Optional.ofNullable(toyRepository.findByCod(cod));

        return optionalToy.map(toy -> {
            optionalToy.ifPresent(toyRepository::delete);
            return toyMapper.apply(toy);
        });
    }

    public Optional<List<ToyDTO>> exclude() {
        Optional<List<ToyDTO>> optionalToyDTOList = quantity();
        toyRepository.exclude();
        return optionalToyDTOList;
    }

    public Optional<List<ToyDTO>> expensiveCheap() {
        List<ToyDTO> toyDTOList = toyRepository
                .expensiveCheap()
                .stream()
                .map(toyMapper)
                .toList();

        return Optional.of(toyDTOList);
    }

    public String avgPrice(String country) {
        Double price = toyRepository.avgPrice(country);
        return (price != null) ?
                String.format("%.2f", price) :
                null;
    }

    public Optional<List<ToyDTO>> insertMoldova() {
        Optional<List<ToyDTO>> optionalToyDTOList = toysMoldova();
        toyRepository.insertMoldova();
        return optionalToyDTOList;
    }

    public Optional<List<ToyDTO>> filters(double x, int n1, int n2) {
        List<ToyDTO> toyDTOList = toyRepository
                .filters(x, n1, n2)
                .stream()
                .map(toyMapper)
                .toList();

        return Optional.of(toyDTOList);
    }

    public Optional<List<PopularProduct>> popular() {
        return Optional.ofNullable(toyRepository.popular());
    }

    public Optional<List<ToyDTO>> quantity() {
        List<ToyDTO> toyDTOList = toyRepository
                .quantity()
                .stream()
                .map(toyMapper)
                .collect(Collectors.toList());

        return Optional.of(toyDTOList);
    }

    public Optional<List<ToyDTO>> toysMoldova() {
        List<ToyDTO> toyDTOList = toyRepository
                .toysMoldova()
                .stream()
                .map(toyMapper)
                .collect(Collectors.toList());

        return Optional.of(toyDTOList);
    }

    private boolean isToyInvalid(Toy toy) {
        return toy.getCod() == null || toy.getCod().isEmpty() ||
                toy.getName() == null || toy.getName().isEmpty() ||
                toy.getPrice() == null ||
                toy.getQuantity() == null ||
                toy.getCountry() == null || toy.getCountry().isEmpty() ||
                toy.getMinimAge() == null ||
                toy.getCategory() == null;
    }

    private Toy createToyWithExistingCategory(Toy newToy, Category category) {
        return new Toy(
                newToy.getId(),
                newToy.getCod(),
                newToy.getName(),
                newToy.getPrice(),
                newToy.getQuantity(),
                newToy.getCountry(),
                newToy.getMinimAge(),
                category
        );
    }

    private Toy createToyWithNewCategory(Toy newToy, Category newCategory) {
        return new Toy(
                newToy.getId(),
                newToy.getCod(),
                newToy.getName(),
                newToy.getPrice(),
                newToy.getQuantity(),
                newToy.getCountry(),
                newToy.getMinimAge(),
                newCategory
        );
    }
}
