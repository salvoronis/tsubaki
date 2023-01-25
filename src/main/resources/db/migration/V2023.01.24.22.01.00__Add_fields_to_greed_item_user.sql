CREATE TABLE if not exists greed_items_user
(
    id            BIGINT  NOT NULL,
    user_id       BIGINT,
    greed_item_id BIGINT,
    pos_x         INTEGER NOT NULL,
    pos_y         INTEGER NOT NULL,
    CONSTRAINT pk_greed_items_user PRIMARY KEY (id)
);

ALTER TABLE greed_items_user
    ADD CONSTRAINT FK_GREED_ITEMS_USER_ON_GREEDITEM FOREIGN KEY (greed_item_id) REFERENCES greed_item (id);

ALTER TABLE greed_items_user
    ADD CONSTRAINT FK_GREED_ITEMS_USER_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

create sequence public.greed_items_user_seq
    increment by 50;

drop table if exists greed_item_user;