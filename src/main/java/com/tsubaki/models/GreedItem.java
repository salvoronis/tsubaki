package com.tsubaki.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "greed_item")
@Getter
@Setter
@NoArgsConstructor
public class GreedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;

    @ManyToOne(fetch = FetchType.LAZY)
    private Application application;

    @OneToMany(mappedBy = "greedItem")
    private Set<GreedItemUser> usersGreed;

}
