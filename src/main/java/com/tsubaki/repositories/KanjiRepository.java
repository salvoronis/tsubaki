package com.tsubaki.repositories;

import com.tsubaki.models.Kanji;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KanjiRepository extends JpaRepository<Kanji, Long> {
}
