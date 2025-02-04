package com.iuri.datebook.service;

import com.iuri.datebook.model.Role;
import com.iuri.datebook.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository roleRepository) {
        this.repository = roleRepository;
    }

    public Role findByName(String name) {
        return repository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException("Permissão não exitente.")
        );
    }

}
