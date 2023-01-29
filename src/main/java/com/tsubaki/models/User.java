package com.tsubaki.models;

import com.tsubaki.models.anki.AnkiCategory;
import com.tsubaki.models.anki.UserAnki;
import com.tsubaki.models.gender.Gender;
import com.tsubaki.models.role.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

import org.hibernate.annotations.Comment;
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

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "preferred_name")
    private String preferredName;

    @Column(name = "first_name_native")
    private String firstNameNative;

    @Column(name = "last_name_native")
    private String lastNameNative;

    @Column(name = "middle_name_native")
    private String middleNameNative;

    @Column(name = "preferred_name_native")
    private String preferredNameNative;

    @Comment("path to google cloud picture")
    @Column(name = "profile_picture")
    private String profilePicture;

    @ManyToOne(fetch = FetchType.LAZY)
    private Gender gender;

    @Column(name = "birth_date")
    private Timestamp birthDate;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Country countryOfResidence;

    @ManyToOne(fetch = FetchType.LAZY)
    private Country birthCountry;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_learning_languages",
            inverseJoinColumns = @JoinColumn(name = "language_id", referencedColumnName = "id"),
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private Set<Language> learningLanguages;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_known_languages",
            inverseJoinColumns = @JoinColumn(name = "language_id", referencedColumnName = "id"),
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private Set<Language> knownLanguages;

}
