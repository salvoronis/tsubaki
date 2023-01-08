package com.tsubaki.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "menu_item")
@Getter
@Setter
@NoArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "is_default")
    private boolean isDefault;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    private MenuItem parentMenuItem;

    @OneToMany(mappedBy = "parentMenuItem")
    private Set<MenuItem> subItems = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "menu_item_user",
            joinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> menuUsers;

}
