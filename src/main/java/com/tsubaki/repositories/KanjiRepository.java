package com.tsubaki.repositories;

import com.tsubaki.models.anki.Anki;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KanjiRepository extends JpaRepository<Anki, Long> {
}
