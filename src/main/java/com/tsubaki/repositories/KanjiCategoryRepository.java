package com.tsubaki.repositories;

import com.tsubaki.models.KanjiCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KanjiCategoryRepository extends JpaRepository<KanjiCategory, Long> {
}
