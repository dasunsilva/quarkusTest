INSERT INTO public.items (item_name, item_price, item_stock) VALUES
                                                                 ('Laptop', 1000, 50),
                                                                 ('Smartphone', 700, 100),
                                                                 ('Tablet', 300, 80),
                                                                 ('Monitor', 150, 60),
                                                                 ('Keyboard', 20, 150),
                                                                 ('Mouse', 15, 200),
                                                                 ('Printer', 120, 40),
                                                                 ('Headphones', 50, 90),
                                                                 ('Speaker', 80, 70),
                                                                 ('External Hard Drive', 100, 30);

ALTER TABLE public.items
    OWNER TO dasun;
