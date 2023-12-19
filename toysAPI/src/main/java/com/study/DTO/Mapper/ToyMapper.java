    package com.study.DTO.Mapper;

    import com.study.DTO.Records.ToyDTO;
    import com.study.Model.Toy;
    import org.springframework.stereotype.Service;

    import java.util.function.Function;

    @Service
    public class ToyMapper implements Function<Toy, ToyDTO> {
        @Override
        public ToyDTO apply(Toy toy) {
            return new ToyDTO(
                    toy.getCod(),
                    toy.getName(),
                    toy.getPrice(),
                    toy.getQuantity(),
                    toy.getCountry(),
                    toy.getMinimAge(),
                    toy.getCategory()
            );
        }
    }
