package com.study.DTO.Records;

import com.study.Model.Toy;

public record SaleDTO(
        String cod,
        Toy toy,
        String saleDate,
        Integer quantity
) {}
