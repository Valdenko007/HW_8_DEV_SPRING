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
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;


    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private List<User> users;


}
