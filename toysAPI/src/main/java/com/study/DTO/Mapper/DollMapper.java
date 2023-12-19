package com.study.DTO.Mapper;

import com.study.DTO.Records.DollDTO;
import com.study.Model.Doll;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DollMapper implements Function<Doll, DollDTO> {
    @Override
    public DollDTO apply(Doll doll) {
        return new DollDTO(
                doll.getCod(),
                doll.getToy(),
                doll.getMaterial(),
                doll.getHeight()
        );
    }
}
