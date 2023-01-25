package com.tsubaki.models.anki;

import com.tsubaki.models.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;

@Entity
@Table(name = "anki_category", schema = "anki")
@Getter
@Setter
@NoArgsConstructor
public class AnkiCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "created_by")
    private long createdBy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User createdUser;

    @ManyToMany
    @JoinTable(
            name = "anki_category_subscribers", schema = "anki",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> subscribers;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "access_modification_id", nullable = false)
    private AccessModification accessModification;

    @ManyToMany
    @JoinTable(
            name = "anki_category_anki", schema = "anki",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "anki_id", referencedColumnName = "id"))
    private Set<Anki> anki;
}
