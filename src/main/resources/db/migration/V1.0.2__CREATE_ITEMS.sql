CREATE TABLE public.items
(
    item_id    BIGINT PRIMARY KEY ,
    item_name  VARCHAR(255) NOT NULL ,
    item_price INTEGER      NOT NULL ,
    item_stock INTEGER      NOT NULL
);

CREATE SEQUENCE public.item_sequence
    START WITH 1
    INCREMENT BY 50;

