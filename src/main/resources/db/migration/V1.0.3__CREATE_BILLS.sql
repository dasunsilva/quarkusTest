CREATE TABLE public.bills
(
    bill_id     BIGINT PRIMARY KEY ,
    bill_amount INTEGER NOT NULL ,
    bill_date   DATE    NOT NULL ,
    user_id     BIGINT,
    CONSTRAINT fk_bills_user
        FOREIGN KEY (user_id) REFERENCES public.users(user_id)
);

CREATE SEQUENCE public.bills_sequence
    START WITH 1
    INCREMENT BY 50;

