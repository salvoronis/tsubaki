package com.tsubaki.repositories;

import com.tsubaki.models.anki.AnkiCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KanjiCategoryRepository extends JpaRepository<AnkiCategory, Long> {
}
