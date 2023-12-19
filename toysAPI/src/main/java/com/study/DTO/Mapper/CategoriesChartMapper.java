package com.study.DTO.Mapper;

import com.study.DTO.Records.CategoriesChartDTO;
import com.study.Model.CategoriesChart;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CategoriesChartMapper implements Function<CategoriesChart, CategoriesChartDTO> {
    @Override
    public CategoriesChartDTO apply(CategoriesChart categoriesChart) {
        return new CategoriesChartDTO(
                categoriesChart.getCod(),
                categoriesChart.getName(),
                categoriesChart.getQuantity()
        );
    }
}
