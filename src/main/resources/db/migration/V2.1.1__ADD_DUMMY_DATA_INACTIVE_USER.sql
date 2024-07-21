INSERT INTO public.users (user_account_number, user_email, user_name, phone_number) VALUES
                                                                                        ('214215', 'inactive1@example.com', 'Inactive user 1', '+94456782413'),
                                                                                        ('153235', 'inactive2@example.com', 'Inactive user 2', '+94456782513'),
                                                                                        ('546541', 'inactive3@example.com', 'Inactive user 3', '+94456782213'),
                                                                                        ('879462', 'inactive4@example.com', 'Inactive user 4', '+94456782613');


ALTER TABLE public.users
    OWNER TO dasun;
