CREATE TABLE application
(
    id           BIGINT NOT NULL,
    created_at   date   NOT NULL,
    updated_at   date,
    name         VARCHAR(255),
    url          VARCHAR(255),
    CONSTRAINT pk_application PRIMARY KEY (id)
);

ALTER TABLE menu_item drop column if exists name;
ALTER TABLE menu_item drop column if exists url;
ALTER TABLE menu_item add column if not exists application_id BIGINT;

ALTER TABLE menu_item
    ADD CONSTRAINT FK_MENU_ITEM_ON_APPLICATION FOREIGN KEY (application_id) REFERENCES application (id);

create sequence public.application_seq
    increment by 50;