package ua.goit.dev.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.goit.dev.converter.ConvertProducer;
import ua.goit.dev.model.dao.Producer;
import ua.goit.dev.model.dto.ProducerDto;
import ua.goit.dev.repository.ProducerRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ProducerService {
    @Autowired
    private ProducerRepository producerRepository;


    public Producer createOrUpdate(Producer producer) {
        return producerRepository.save(producer);

    }

    public List<Producer> getAll() {

        return producerRepository.findAll();
    }



    public List<ProducerDto> getAllDto() {
        ConvertProducer convertProducer=new ConvertProducer();
        return getAll()
                .stream()
                .map(entity -> convertProducer.from(entity))
                .collect(Collectors.toList());
    }

    public Optional<Producer> findById(UUID id) {

        return producerRepository.findById(id);
    }

    public void deleteById(UUID id) {

        try {
            producerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("deleteById . No such index in database");
            throw new NoSuchElementException("No such index in database");
        }
    }
}