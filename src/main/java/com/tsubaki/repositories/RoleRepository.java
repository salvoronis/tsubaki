package com.tsubaki.repositories;

import com.tsubaki.models.role.ERole;
import com.tsubaki.models.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole role);
}
