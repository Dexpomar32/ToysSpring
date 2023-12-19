package com.study.DTO.Mapper;

import com.study.DTO.Records.DollMaterialChartDTO;
import com.study.Model.DollMaterialChart;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DollMaterialChartMapper implements Function<DollMaterialChart, DollMaterialChartDTO> {
    @Override
    public DollMaterialChartDTO apply(DollMaterialChart dollMaterialChart) {
        return new DollMaterialChartDTO(
                dollMaterialChart.getCod(),
                dollMaterialChart.getName(),
                dollMaterialChart.getQuantity()
        );
    }
}
