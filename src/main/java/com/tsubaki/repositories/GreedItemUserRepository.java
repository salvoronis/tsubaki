package com.tsubaki.repositories;

import com.tsubaki.models.GreedItemUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GreedItemUserRepository extends JpaRepository<GreedItemUser, Long> {
    List<GreedItemUser> findGreedItemUsersByUserId(Long id);
}
