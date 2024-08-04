CREATE SEQUENCE public.item_sequence
    START WITH 100
    INCREMENT BY 50;

CREATE TABLE public.items
(
    item_id    BIGINT PRIMARY KEY DEFAULT NEXTVAL('public.item_sequence'),
    item_name  VARCHAR(255) NOT NULL ,
    item_price INTEGER      NOT NULL ,
    item_stock INTEGER      NOT NULL
);

