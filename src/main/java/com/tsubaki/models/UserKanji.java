package com.tsubaki.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;

@Entity
@Table(name = "user_kanji")
@Getter
@Setter
@NoArgsConstructor
public class UserKanji {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "repeated_times")
    private int repeatedTimes;

    @Column(name = "last_repeat_date")
    private Date lastRepeatDate;

    @Column(name = "already_know")
    private boolean alreadyKnow;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kanji_id", nullable = false)
    private Kanji kanji;
}
