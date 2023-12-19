package com.study.Service;

import com.study.DTO.Mapper.*;
import com.study.DTO.Records.*;
import com.study.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ChartsService {
    private final CategoriesChartRepository categoriesChartRepository;
    private final CategoriesChartMapper categoriesChartMapper;
    private final CountriesChartRepository countriesChartRepository;
    private final CountriesChartMapper countriesChartMapper;
    private final AgeToyChartRepository ageToyChartRepository;
    private final AgeToyChartMapper ageToyChartMapper;
    private final DateSalesChartRepository dateSalesChartRepository;
    private final DateSalesChartMapper dateSalesChartMapper;
    private final DollMaterialChartRepository dollMaterialChartRepository;
    private final DollMaterialChartMapper dollMaterialChartMapper;

    @Autowired
    public ChartsService(CategoriesChartRepository categoriesChartRepository, CategoriesChartMapper categoriesChartMapper,
                         CountriesChartRepository countriesChartRepository, CountriesChartMapper countriesChartMapper,
                         AgeToyChartRepository ageToyChartRepository, AgeToyChartMapper ageToyChartMapper,
                         DateSalesChartRepository dateSalesChartRepository, DateSalesChartMapper dateSalesChartMapper,
                         DollMaterialChartRepository dollMaterialChartRepository, DollMaterialChartMapper dollMaterialChartMapper) {
        this.categoriesChartRepository = categoriesChartRepository;
        this.categoriesChartMapper = categoriesChartMapper;
        this.countriesChartRepository = countriesChartRepository;
        this.countriesChartMapper = countriesChartMapper;
        this.ageToyChartRepository = ageToyChartRepository;
        this.ageToyChartMapper = ageToyChartMapper;
        this.dateSalesChartRepository = dateSalesChartRepository;
        this.dateSalesChartMapper = dateSalesChartMapper;
        this.dollMaterialChartRepository = dollMaterialChartRepository;
        this.dollMaterialChartMapper = dollMaterialChartMapper;
    }

    public Optional<List<CountriesChartDTO>> getAllCountries() {
        updateTableCountries();
        List<CountriesChartDTO> countriesChartDTOList = countriesChartRepository
                .findAll()
                .stream()
                .map(countriesChartMapper)
                .toList();

        return Optional.of(countriesChartDTOList);
    }

    public Optional<List<CategoriesChartDTO>> getAllCategories() {
        updateTableCategories();
        List<CategoriesChartDTO> categoriesChartDTOList = categoriesChartRepository
                .findAll()
                .stream()
                .map(categoriesChartMapper)
                .toList();

        return Optional.of(categoriesChartDTOList);
    }

    public Optional<List<AgeToyChartDTO>> getAllAgeToy() {
        updateTableAgeToy();
        List<AgeToyChartDTO> ageToyChartDTOList = ageToyChartRepository
                .findAll()
                .stream()
                .map(ageToyChartMapper)
                .toList();

        return Optional.of(ageToyChartDTOList);
    }

    public Optional<List<DateSalesChartDTO>> getAllDateSales() {
        updateTableDateSales();
        List<DateSalesChartDTO> dateSalesChartDTOList = dateSalesChartRepository
                .findAll()
                .stream()
                .map(dateSalesChartMapper)
                .toList();

        return Optional.of(dateSalesChartDTOList);
    }

    public Optional<List<DollMaterialChartDTO>> getAllDollMaterial() {
        updateTableDollMaterial();
        List<DollMaterialChartDTO> dollMaterialChartDTOList = dollMaterialChartRepository
                .findAll()
                .stream()
                .map(dollMaterialChartMapper)
                .toList();

        return Optional.of(dollMaterialChartDTOList);
    }

    public void updateTableCountries() {
        countriesChartRepository.updateTable();
    }

    public void updateTableCategories() {
        categoriesChartRepository.updateTable();
    }

    public void updateTableAgeToy() {
        ageToyChartRepository.updateTable();
    }

    public void updateTableDateSales() {
        dateSalesChartRepository.updateTable();
    }

    public void updateTableDollMaterial() {
        dollMaterialChartRepository.updateTable();
    }
}
