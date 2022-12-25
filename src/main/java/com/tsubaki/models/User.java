package com.tsubaki.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

import org.hibernate.mapping.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.sql.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@EnableJpaAuditing
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "settings_id")
    private long settingsId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "settings_id")
    private UserSettings settings;

    @OneToMany(mappedBy = "createdUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<KanjiCategory> authoredCategories;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserKanji> learningKanji;

    @ManyToMany(mappedBy = "subscribers")
    private Set<KanjiCategory> subscribedCategories;
}
