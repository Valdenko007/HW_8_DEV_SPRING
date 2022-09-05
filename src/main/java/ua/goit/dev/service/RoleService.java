package ua.goit.dev.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.dev.model.dao.Role;
import ua.goit.dev.repository.RoleRepository;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
