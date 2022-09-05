package ua.goit.dev.converter;

import ua.goit.dev.model.dao.Producer;
import ua.goit.dev.model.dto.ProducerDto;

import java.util.stream.Collectors;

public class ConvertProducer {
    public ProducerDto from(Producer producer) {
        ConverterProduct converterProduct = new ConverterProduct();
        return ProducerDto.builder()
                .id(producer.getId())
                .name(producer.getName())
                .productDto(producer.getProducts().stream().map(p -> converterProduct.from(p)).collect(Collectors.toList()))
                .build();
    }
}
