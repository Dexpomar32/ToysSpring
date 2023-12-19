package com.study.DTO.Mapper;

import com.study.DTO.Records.DateSalesChartDTO;
import com.study.Model.DateSalesChart;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

@Service
public class DateSalesChartMapper implements Function<DateSalesChart, DateSalesChartDTO> {
    @Override
    public DateSalesChartDTO apply(DateSalesChart dateSalesChart) {
        return new DateSalesChartDTO(
                dateSalesChart.getCod(),
                formatDate(dateSalesChart.getName()),
                dateSalesChart.getQuantity()
        );
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
