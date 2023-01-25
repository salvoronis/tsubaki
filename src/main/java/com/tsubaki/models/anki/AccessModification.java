package com.tsubaki.models.anki;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "access_modification", schema = "anki")
@Getter
@Setter
@NoArgsConstructor
public class AccessModification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "access_type")
    private String access_type; //TODO rewrite to enum

    @OneToMany(mappedBy = "accessModification", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AnkiCategory> categories;
}
