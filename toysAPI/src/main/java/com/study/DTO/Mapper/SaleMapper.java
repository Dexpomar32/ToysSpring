package com.study.DTO.Mapper;

import com.study.DTO.Records.SaleDTO;
import com.study.Model.Sale;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

@Service
public class SaleMapper implements Function<Sale, SaleDTO> {
    @Override
    public SaleDTO apply(Sale sale) {
        return new SaleDTO(
                sale.getCod(),
                sale.getToy(),
                formatDate(sale.getSaleDate()),
                sale.getQuantity()
        );
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
