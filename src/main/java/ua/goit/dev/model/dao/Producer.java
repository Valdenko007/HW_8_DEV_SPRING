package ua.goit.dev.model.dao;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producer")
public class Producer  {
    @Id
    @GeneratedValue

    @Column(name= "id")
    private UUID id;

    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "producer", fetch = FetchType.EAGER,cascade=CascadeType.REMOVE)
    private List<Product> products;

}
