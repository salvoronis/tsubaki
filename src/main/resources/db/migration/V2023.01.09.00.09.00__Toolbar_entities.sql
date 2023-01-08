CREATE TABLE if not exists menu_item
(
    id                  BIGINT  NOT NULL,
    created_at          date    NOT NULL,
    updated_at          date,
    is_default          BOOLEAN NOT NULL,
    name                VARCHAR(255) NOT NULL,
    url                 VARCHAR(255) NOT NULL,
    parent_menu_item_id BIGINT,
    CONSTRAINT pk_menu_item PRIMARY KEY (id)
);

CREATE TABLE if not exists menu_item_user
(
    menu_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_menu_item_user PRIMARY KEY (menu_id, user_id)
);

ALTER TABLE menu_item
    ADD CONSTRAINT FK_MENU_ITEM_ON_PARENTMENUITEM FOREIGN KEY (parent_menu_item_id) REFERENCES menu_item (id);

ALTER TABLE menu_item_user
    ADD CONSTRAINT fk_meniteuse_on_menu_item FOREIGN KEY (menu_id) REFERENCES menu_item (id);

ALTER TABLE menu_item_user
    ADD CONSTRAINT fk_meniteuse_on_user FOREIGN KEY (user_id) REFERENCES users (id);

create sequence public.menu_item_seq
    increment by 50;

INSERT INTO menu_item(id, created_at, updated_at, is_default, name, url, parent_menu_item_id)
VALUES (nextval('menu_item_seq'), current_date, current_date, TRUE, 'Application Store', 'localhost:8080', null);