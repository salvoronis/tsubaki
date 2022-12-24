package com.tsubaki.repositories;

import com.tsubaki.models.UserKanji;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserKanjiRepository extends JpaRepository<UserKanji, Long> {
}
