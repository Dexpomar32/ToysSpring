package com.study.Service;

import com.study.DTO.Mapper.SaleMapper;
import com.study.DTO.Records.SaleDTO;
import com.study.Model.Category;
import com.study.Model.Sale;
import com.study.Model.Toy;
import com.study.Repository.CategoryRepository;
import com.study.Repository.SaleRepository;
import com.study.Repository.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final ToyRepository toyRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository, SaleMapper saleMapper, ToyRepository toyRepository, CategoryRepository categoryRepository) {
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
        this.toyRepository = toyRepository;
        this.categoryRepository = categoryRepository;
    }

    public Optional<List<SaleDTO>> getAll() {
        List<SaleDTO> saleDTOList = saleRepository
                .findAll()
                .stream()
                .map(saleMapper)
                .toList();

        return Optional.of(saleDTOList);
    }

    public Optional<SaleDTO> getOne(String cod) {
        return Optional.ofNullable(saleRepository.findByCod(cod))
                .map(saleMapper);
    }

    public Optional<SaleDTO> create(Sale newSale) {
        if (isSaleInvalid(newSale)) {
            return Optional.empty();
        }

        if (newSale.getToy().getCod() != null) {
            Toy existingToy = toyRepository.findByCod(newSale.getToy().getCod());
            if (existingToy == null) {
                existingToy = createNewToy(newSale.getToy());
            }
            newSale.setToy(existingToy);
        } else {
            return Optional.empty();
        }

        if (newSale.getToy().getCategory() != null && newSale.getToy().getCategory().getCod() != null) {
            Category existingCategory = categoryRepository.findByCod(newSale.getToy().getCategory().getCod());
            if (existingCategory == null) {
                existingCategory = createNewCategory(newSale.getToy().getCategory());
            }
            newSale.getToy().setCategory(existingCategory);
        }

        Sale sale = new Sale(
                newSale.getId(),
                newSale.getCod(),
                newSale.getToy(),
                newSale.getSaleDate(),
                newSale.getQuantity()
        );

        saleRepository.save(sale);
        return Optional.ofNullable(saleMapper.apply(sale));
    }

    public Optional<SaleDTO> update(Sale updateSale) {
        Optional<Sale> optionalSale = Optional.ofNullable(saleRepository.findByCod(updateSale.getCod()));

        return optionalSale.map(sale -> {
            if (updateSaleToy(updateSale, sale)) return null;

            sale.setSaleDate(updateSale.getSaleDate() != null ? updateSale.getSaleDate() : sale.getSaleDate());
            sale.setQuantity(updateSale.getQuantity() != null ? updateSale.getQuantity() : sale.getQuantity());

            saleRepository.save(sale);
            return saleMapper.apply(sale);
        });
    }

    private boolean updateSaleToy(Sale updateSale, Sale sale) {
        if (updateSale.getToy() != null) {
            Toy updatedToy = updateSale.getToy();
            if (updatedToy.getCod() != null) {
                Toy existingToy = toyRepository.findByCod(updatedToy.getCod());
                if (existingToy != null) {
                    updateToy(existingToy, updatedToy);
                    updateCategory(existingToy.getCategory(), updatedToy.getCategory());
                    sale.setToy(existingToy);
                } else {
                    Toy newToy = createNewToy(updatedToy);
                    sale.setToy(newToy);
                }
            } else {
                return true;
            }
        }
        return false;
    }

    public Optional<SaleDTO> delete(String cod) {
        Optional<Sale> optionalSale = Optional.ofNullable(saleRepository.findByCod(cod));

        return optionalSale.map(sale -> {
            optionalSale.ifPresent(saleRepository::delete);
            return saleMapper.apply(sale);
        });
    }

    public Integer sales(LocalDate date) {
        return saleRepository.sales(date);
    }

    public void updateToyQuantityAndSales(String cod, int quantity) {
        Toy toy = toyRepository.findByCod(cod);

        if (toy != null && toy.getQuantity() >= quantity) {
            toyRepository.updateQuantityByCod(cod, quantity);
            toy.setQuantity(toy.getQuantity() - quantity);

            Sale sale = saleRepository.findByCod(cod);
            if (sale == null) {
                sale = new Sale();
                sale.setCod(cod);
                sale.setToy(toy);
                sale.setSaleDate(Date.valueOf(LocalDate.now()));
            }

            if (sale.getQuantity() == null) {
                sale.setQuantity(0);
            }

            sale.setQuantity(sale.getQuantity() + quantity);

            saleRepository.save(sale);
        }
    }

    private boolean isSaleInvalid(Sale newSale) {
        return newSale.getCod() == null || newSale.getCod().isEmpty() ||
                newSale.getToy() == null ||
                newSale.getSaleDate() == null ||
                newSale.getQuantity() == null;
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