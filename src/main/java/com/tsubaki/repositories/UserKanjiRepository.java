package com.tsubaki.repositories;

import com.tsubaki.models.anki.UserAnki;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserKanjiRepository extends JpaRepository<UserAnki, Long> {
}
