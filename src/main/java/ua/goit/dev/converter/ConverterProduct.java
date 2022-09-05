package ua.goit.dev.converter;

import ua.goit.dev.model.dao.Product;
import ua.goit.dev.model.dto.ProductDto;

public class ConverterProduct {
public ProductDto from(Product product){
    return ProductDto.builder()
            .id(product.getId())
            .name(product.getName())
            .price(product.getPrice()).build();
}
}
