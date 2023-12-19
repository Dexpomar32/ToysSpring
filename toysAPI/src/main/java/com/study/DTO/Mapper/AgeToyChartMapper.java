package com.study.DTO.Mapper;

import com.study.DTO.Records.AgeToyChartDTO;
import com.study.Model.AgeToyChart;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AgeToyChartMapper implements Function<AgeToyChart, AgeToyChartDTO> {
    @Override
    public AgeToyChartDTO apply(AgeToyChart ageToyChart) {
        return new AgeToyChartDTO(
                ageToyChart.getCod(),
                ageToyChart.getName(),
                ageToyChart.getQuantity()
        );
    }
}