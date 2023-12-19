package com.study.DTO.Mapper;

import com.study.DTO.Records.CountriesChartDTO;
import com.study.Model.CountriesChart;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CountriesChartMapper implements Function<CountriesChart, CountriesChartDTO> {
    @Override
    public CountriesChartDTO apply(CountriesChart countriesChart) {
        return new CountriesChartDTO(
                countriesChart.getCod(),
                countriesChart.getName(),
                countriesChart.getQuantity()
        );
    }
}
