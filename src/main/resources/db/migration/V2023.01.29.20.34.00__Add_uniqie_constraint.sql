ALTER TABLE genders
    ADD CONSTRAINT uc_gender_gender UNIQUE (gender);

ALTER TABLE roles
    ADD CONSTRAINT uc_role_name UNIQUE (name);

ALTER TABLE application
    ADD CONSTRAINT uc_application_name UNIQUE (name);