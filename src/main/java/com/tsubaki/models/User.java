package com.tsubaki.models;

import com.tsubaki.models.anki.AnkiCategory;
import com.tsubaki.models.anki.UserAnki;
import com.tsubaki.models.role.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    public User(String username, String email, String password, Set<Role> roles){
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private UserSettings settings;

    @OneToMany(mappedBy = "createdUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AnkiCategory> authoredCategories;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserAnki> learningKanji;

    @ManyToMany(mappedBy = "subscribers")
    private Set<AnkiCategory> subscribedCategories;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @ManyToMany(mappedBy = "menuUsers")
    private Set<MenuItem> userMenuItems; // Not include default menu items

    @OneToMany(mappedBy = "user")
    private Set<GreedItemUser> usersGreed;

}
