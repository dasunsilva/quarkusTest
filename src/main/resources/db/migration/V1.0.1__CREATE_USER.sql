CREATE TABLE public.users
(
    user_id             BIGINT PRIMARY KEY ,
    user_account_number VARCHAR(255) NOT NULL UNIQUE,
    user_email          VARCHAR(255),
    user_name           VARCHAR(255) NOT NULL,
    phone_number        VARCHAR(255)
);

CREATE SEQUENCE public.user_sequence
    START WITH 1
    INCREMENT BY 50;
