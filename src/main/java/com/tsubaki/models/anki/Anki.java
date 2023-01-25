package com.tsubaki.models.anki;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;

@Entity
@Table(name = "anki", schema = "anki")
@Getter
@Setter
@NoArgsConstructor
public class Anki {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "word")
    private String word;

    @Column(name = "major_transcription")
    private String majorTranscription;

    @Column(name = "minor_transcription")
    private String minorTranscription;

    @Column(name = "meaning")
    private String meaning;

    @ManyToMany(mappedBy = "anki")
    private Set<AnkiCategory> categories;

    @OneToMany(mappedBy = "anki", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserAnki> learningUsers;
}
