create table public.users
(
    id          bigint       not null primary key,
    created_at  timestamp(6) not null,
    email       varchar(255) unique,
    password    varchar(255),
    updated_at  timestamp(6),
    username    varchar(255),
    settings_id bigint
);

create table public.user_settings
(
    id bigint not null
        primary key,
    created_at date not null,
    repeat_count integer,
    updated_at date
);

create table public.roles
(
    id bigint not null
        primary key,
    name varchar(20)
);

create table public.kanji
(
    id bigint not null
        primary key,
    created_at date not null,
    example_words varchar(255),
    kanji varchar(255),
    kun_yomi varchar(255),
    on_yomi varchar(255),
    updated_at date
);

create table public.access_modification
(
    id bigint not null
        primary key,
    access_type varchar(255),
    created_at date not null,
    updated_at date
);

create table public.user_roles
(
    user_id bigint not null
            references public.users,
    role_id bigint not null
            references public.roles,
    primary key (user_id, role_id)
);

create table public.user_kanji
(
    id bigint not null
        primary key,
    already_know boolean,
    created_at date not null,
    last_repeat_date date,
    repeated_times integer,
    updated_at date,
    kanji_id bigint not null
            references public.kanji,
    user_id bigint not null
            references public.users
);

create table public.kanji_category
(
    id bigint not null
        primary key,
    created_at date not null,
    created_by bigint,
    updated_at date,
    access_modification_id bigint not null
        references public.access_modification,
    user_id bigint not null
        references public.users
);

create table public.kanji_category_subscribers
(
    category_id bigint not null
            references public.kanji_category,
    user_id bigint not null
            references public.users,
    primary key (category_id, user_id)
);

create table public.kanji_category_kanji
(
    category_id bigint not null
            references public.kanji_category,
    kanji_id bigint not null
            references public.kanji,
    primary key (category_id, kanji_id)
);

create sequence public.access_modification_seq
    increment by 50;

create sequence public.kanji_category_seq
    increment by 50;

create sequence public.kanji_seq
    increment by 50;

create sequence public.roles_seq
    increment by 50;

create sequence public.user_kanji_seq
    increment by 50;

create sequence public.user_settings_seq
    increment by 50;

create sequence public.users_seq
    increment by 50;