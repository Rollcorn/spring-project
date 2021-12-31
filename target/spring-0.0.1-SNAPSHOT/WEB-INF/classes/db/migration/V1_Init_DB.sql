CREATE SEQUENCE compositions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647 CACHE 1;

CREATE TABLE compositions
(
    id     int8 NOT NULL DEFAULT nextval('compositions_id_seq'),
    title  VARCHAR(255),
    author VARCHAR(255),
    genre VARCHAR(255),
    PRIMARY KEY (id)
);

ALTER TABLE if EXISTS compositions_id_seq
    ADD CONSTRAINT fc_team_fk
