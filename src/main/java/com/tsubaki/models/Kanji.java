package com.tsubaki.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.sql.Date;

@Entity
@Table(name = "kanji")
@Getter
@Setter
@NoArgsConstructor
@EnableJpaAuditing
public class Kanji {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "kanji")
    private String kanji;

    @Column(name = "on_yomi")
    private String onYomi;

    @Column(name = "kun_yomi")
    private String kunYomi;

    @Column(name = "example_words")
    private String examples; // TODO rewrite to another entity

    @ManyToMany(mappedBy = "kanji")
    private Set<KanjiCategory> categories;

    @OneToMany(mappedBy = "kanji", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserKanji> learningUsers;
}
