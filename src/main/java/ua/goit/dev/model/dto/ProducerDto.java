package ua.goit.dev.model.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProducerDto {
    private UUID id;
    private String name;
    private List<ProductDto> productDto;
}
