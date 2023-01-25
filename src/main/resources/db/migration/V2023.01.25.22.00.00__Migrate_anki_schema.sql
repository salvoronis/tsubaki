create schema if not exists anki;

alter table if exists public.kanji
    rename to anki;
alter table if exists public.kanji_category
    rename to anki_category;
alter table if exists public.kanji_category_kanji
    rename to anki_category_anki;
alter table if exists public.kanji_category_subscribers
    rename to anki_category_subscribers;
alter table if exists public.user_kanji
    rename to user_anki;

alter table anki
    set schema anki;
alter table anki_category
    set schema anki;
alter table anki_category_anki
    set schema anki;
alter table anki_category_subscribers
    set schema anki;
alter table user_anki
    set schema anki;
alter table access_modification
    set schema anki;

alter sequence if exists kanji_seq
    rename to anki_seq;
alter sequence if exists kanji_category_seq
    rename to anki_category_seq;
alter sequence if exists user_kanji_seq
    rename to user_anki_seq;

alter sequence anki_seq
    set schema anki;
alter sequence anki_category_seq
    set schema anki;
alter sequence user_anki_seq
    set schema anki;
alter sequence access_modification_seq
    set schema anki;

alter table anki.anki
    rename column kanji to word;
alter table anki.anki
    rename column on_yomi to major_transcription;
alter table anki.anki
    rename column kun_yomi to minor_transcription;
alter table anki.anki
    add column meaning text;
alter table anki.anki
    drop column example_words;
alter table anki.anki_category_anki
    rename column kanji_id to anki_id;
alter table anki.user_anki rename column kanji_id to anki_id;