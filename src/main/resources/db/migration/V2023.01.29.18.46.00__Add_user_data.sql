CREATE TABLE genders
(
    id         BIGINT                      NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    gender     VARCHAR(20),
    CONSTRAINT pk_genders PRIMARY KEY (id)
);

create sequence public.genders_seq
    increment by 50;

CREATE TABLE countries
(
    id               BIGINT                      NOT NULL,
    created_at       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at       TIMESTAMP WITHOUT TIME ZONE,
    country_name     VARCHAR(255),
    country_iso_code VARCHAR(255),
    CONSTRAINT pk_countries PRIMARY KEY (id)
);

ALTER TABLE countries
    ADD CONSTRAINT uc_countries_country_iso_code UNIQUE (country_iso_code);

ALTER TABLE countries
    ADD CONSTRAINT uc_countries_country_name UNIQUE (country_name);

create sequence public.countries_seq
    increment by 50;

CREATE TABLE languages
(
    id            BIGINT                      NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at    TIMESTAMP WITHOUT TIME ZONE,
    language_name VARCHAR(255),
    language_code VARCHAR(255),
    CONSTRAINT pk_languages PRIMARY KEY (id)
);

create sequence public.languages_seq
    increment by 50;

alter table users add column first_name text;
alter table users add column last_name text;
alter table users add column middle_name text;
alter table users add column preferred_name text;
alter table users add column first_name_native text;
alter table users add column last_name_native text;
alter table users add column middle_name_native text;
alter table users add column preferred_name_native text;

alter table users add column profile_picture text;

alter table users add column gender_id bigint;

alter table users add column birth_date TIMESTAMP WITHOUT TIME ZONE;

alter table users add column country_of_residence_id bigint;

alter table users add column birth_country_id bigint;

alter table users drop column settings_id;

CREATE TABLE user_known_languages
(
    language_id BIGINT NOT NULL,
    user_id     BIGINT NOT NULL,
    CONSTRAINT pk_user_known_languages PRIMARY KEY (language_id, user_id)
);

CREATE TABLE user_learning_languages
(
    language_id BIGINT NOT NULL,
    user_id     BIGINT NOT NULL,
    CONSTRAINT pk_user_learning_languages PRIMARY KEY (language_id, user_id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_BIRTHCOUNTRY FOREIGN KEY (birth_country_id) REFERENCES countries (id);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_COUNTRYOFRESIDENCE FOREIGN KEY (country_of_residence_id) REFERENCES countries (id);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_GENDER FOREIGN KEY (gender_id) REFERENCES genders (id);

ALTER TABLE user_known_languages
    ADD CONSTRAINT fk_useknolan_on_language FOREIGN KEY (language_id) REFERENCES languages (id);

ALTER TABLE user_known_languages
    ADD CONSTRAINT fk_useknolan_on_user FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE user_learning_languages
    ADD CONSTRAINT fk_uselealan_on_language FOREIGN KEY (language_id) REFERENCES languages (id);

ALTER TABLE user_learning_languages
    ADD CONSTRAINT fk_uselealan_on_user FOREIGN KEY (user_id) REFERENCES users (id);

drop table user_settings;