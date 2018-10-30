package com.infodev.pscrms.repository;


import com.infodev.pscrms.entities.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Long> {

    Role findByName(String name);

    @Override
    void delete(Role role);
}
