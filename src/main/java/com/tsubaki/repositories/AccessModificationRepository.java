package com.tsubaki.repositories;

import com.tsubaki.models.anki.AccessModification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessModificationRepository extends JpaRepository<AccessModification, Long> {
}
