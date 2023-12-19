package com.study.DTO.Records;

import com.study.Model.Toy;

public record DollDTO(
        String cod,
        Toy toy,
        String material,
        Double height
) {}
