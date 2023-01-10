CREATE TABLE greed_item
(
    id             BIGINT  NOT NULL,
    created_at     date    NOT NULL,
    updated_at     date,
    width          INTEGER NOT NULL,
    height         INTEGER NOT NULL,
    application_id BIGINT,
    CONSTRAINT pk_greed_item PRIMARY KEY (id)
);

ALTER TABLE greed_item
    ADD CONSTRAINT FK_GREED_ITEM_ON_APPLICATION FOREIGN KEY (application_id) REFERENCES application (id);

create sequence public.greed_item_seq
    increment by 50;

CREATE TABLE if not exists greed_item_user
(
    greed_item_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_greed_item_user PRIMARY KEY (greed_item_id, user_id)
);

ALTER TABLE greed_item_user
    ADD CONSTRAINT fk_meniteuse_on_menu_item FOREIGN KEY (greed_item_id) REFERENCES greed_item (id);

ALTER TABLE greed_item_user
    ADD CONSTRAINT fk_meniteuse_on_user FOREIGN KEY (user_id) REFERENCES users (id);