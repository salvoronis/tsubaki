package com.tsubaki.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "greed_items_user")
@Getter
@Setter
@NoArgsConstructor
public class GreedItemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private GreedItem greedItem;

    @Column(name = "pos_x")
    private int x;

    @Column(name = "pos_y")
    private int y;
}
