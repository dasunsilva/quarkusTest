CREATE SEQUENCE public.bill_items_sequence
    START WITH 1
    INCREMENT BY 50;

CREATE TABLE public.bill_item
(
    id              BIGINT DEFAULT nextval('public.bill_items_sequence') PRIMARY KEY ,
    priceatpurchase INTEGER NOT NULL ,
    quantity        INTEGER NOT NULL ,
    bill_id         BIGINT ,
    item_id         BIGINT ,
    CONSTRAINT fk_bill_item_bill
        FOREIGN KEY(bill_id) REFERENCES public.bills(bill_id) ,
    CONSTRAINT fk_bill_item_item
        FOREIGN KEY(item_id) REFERENCES public.items(item_id)
);


