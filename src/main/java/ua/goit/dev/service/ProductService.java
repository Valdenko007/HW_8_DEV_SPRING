package ua.goit.dev.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.goit.dev.converter.ConverterProduct;
import ua.goit.dev.model.dao.Product;
import ua.goit.dev.model.dto.ProductDto;
import ua.goit.dev.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createOrUpdate(Product product) {
        return productRepository.save(product);

    }

    public List<Product> getAll() {

        return productRepository.findAll();
    }



    public List<ProductDto> getAllDto() {
        ConverterProduct convertProducer=new ConverterProduct();
        return getAll()
                .stream()
                .map(entity -> convertProducer.from(entity))
                .collect(Collectors.toList());
    }

    public Optional<Product> findById(UUID id) {

        return productRepository.findById(id);
    }

    public void deleteById(UUID id) {

        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("deleteById . No such index in database");
            throw new NoSuchElementException("No such index in database");
        }
    }
}
