INSERT INTO public.bills (bill_amount, bill_date, user_id) VALUES
                                                               (1500, '2024-01-15', 1),
                                                               (2300, '2024-02-20', 1),
                                                               (1200, '2024-03-10', 2),
                                                               (4500, '2024-04-25', 1),
                                                               (3200, '2024-05-05', 3),
                                                               (2750, '2024-06-15', 4),
                                                               (3300, '2024-07-20', 5);

ALTER TABLE public.bills
    OWNER TO dasun;
