package com.study.Service;

import com.study.DTO.Mapper.DollMapper;
import com.study.DTO.Records.DollDTO;
import com.study.Model.Category;
import com.study.Model.Doll;
import com.study.Model.Toy;
import com.study.Repository.CategoryRepository;
import com.study.Repository.DollRepository;
import com.study.Repository.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DollService {
    private final DollRepository dollRepository;
    private final DollMapper dollMapper;
    private final ToyRepository toyRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public DollService(DollRepository dollRepository, DollMapper dollMapper, ToyRepository toyRepository, CategoryRepository categoryRepository) {
        this.dollRepository = dollRepository;
        this.dollMapper = dollMapper;
        this.toyRepository = toyRepository;
        this.categoryRepository = categoryRepository;
    }

    public Optional<List<DollDTO>> getAll() {
        List<DollDTO> dollDTOList = dollRepository
                .findAll()
                .stream()
                .map(dollMapper)
                .toList();

        return Optional.of(dollDTOList);
    }

    public Optional<DollDTO> getOne(String cod) {
        return Optional.ofNullable(dollRepository.findByCod(cod))
                .map(dollMapper);
    }

    public Optional<DollDTO> create(Doll newDoll) {
        if (isDollInvalid(newDoll)) {
            return Optional.empty();
        }

        if (newDoll.getToy().getCod() != null) {
            Toy existingToy = toyRepository.findByCod(newDoll.getToy().getCod());
            if (existingToy == null) {
                existingToy = createNewToy(newDoll.getToy());
            }
            newDoll.setToy(existingToy);
        } else {
            return Optional.empty();
        }

        if (newDoll.getToy().getCategory() != null && newDoll.getToy().getCategory().getCod() != null) {
            Category existingCategory = categoryRepository.findByCod(newDoll.getToy().getCategory().getCod());
            if (existingCategory == null) {
                existingCategory = createNewCategory(newDoll.getToy().getCategory());
            }
            newDoll.getToy().setCategory(existingCategory);
        }

        Doll doll = new Doll(
                newDoll.getId(),
                newDoll.getCod(),
                newDoll.getToy(),
                newDoll.getMaterial(),
                newDoll.getHeight()
        );

        dollRepository.save(doll);
        return Optional.ofNullable(dollMapper.apply(doll));
    }

    public Optional<DollDTO> update(Doll updateDoll) {
        Optional<Doll> optionalDoll = Optional.ofNullable(dollRepository.findByCod(updateDoll.getCod()));

        optionalDoll.ifPresent(doll -> {
            Toy updateToy = updateDoll.getToy();
            if (updateToy != null) {
                Toy existingToy = doll.getToy();

                if (existingToy != null) {
                    updateToy(existingToy, updateToy);
                    updateCategory(existingToy.getCategory(), updateToy.getCategory());
                } else {
                    Toy newToy = createNewToy(updateToy);
                    doll.setToy(newToy);
                }
            }

            if (!updateDoll.getMaterial().isEmpty()) {
                doll.setMaterial(updateDoll.getMaterial());
            }
            if (updateDoll.getHeight() != null) {
                doll.setHeight(updateDoll.getHeight());
            }

            dollRepository.save(doll);
        });

        return optionalDoll.map(dollMapper);
    }

    public Optional<DollDTO> delete(String cod) {
        Optional<Doll> optionalDoll = Optional.ofNullable(dollRepository.findByCod(cod));

        return optionalDoll.map(papusile -> {
            optionalDoll.ifPresent(dollRepository::delete);
            return dollMapper.apply(papusile);
        });
    }

    public Optional<List<DollDTO>> ascending() {
        List<DollDTO> dollDTOList = dollRepository
                .ascending()
                .stream()
                .map(dollMapper)
                .toList();

        return Optional.of(dollDTOList);
    }

    private boolean isDollInvalid(Doll newDoll) {
        return newDoll.getCod() == null || newDoll.getCod().isEmpty() ||
                newDoll.getToy() == null ||
                newDoll.getMaterial() == null ||
                newDoll.getHeight() == null;
    }

    private Toy createNewToy(Toy newToy) {
        toyRepository.save(newToy);
        return newToy;
    }

    private Category createNewCategory(Category newCategory) {
        categoryRepository.save(newCategory);
        return newCategory;
    }

    private void updateToy(Toy existingToy, Toy updateToy) {
        existingToy.setName(updateToy.getName());
        existingToy.setPrice(updateToy.getPrice());
        existingToy.setQuantity(updateToy.getQuantity());
        existingToy.setCountry(updateToy.getCountry());
        existingToy.setMinimAge(updateToy.getMinimAge());
    }

    private void updateCategory(Category existingCategory, Category updateCategory) {
        if (updateCategory != null && updateCategory.getCod() != null) {
            Category updatedCategory = categoryRepository.findByCod(updateCategory.getCod());
            if (updatedCategory != null) {
                existingCategory.setName(updatedCategory.getName());
            }
        }
    }

}