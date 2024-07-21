INSERT INTO public.users (user_account_number, user_email, user_name, phone_number) VALUES
                                                                                        ('123456', 'user1@example.com', 'John Doe', '+94771234567'),
                                                                                        ('234567', 'user2@example.com', 'Jane Smith', '+94772345678'),
                                                                                        ('345678', 'user3@example.com', 'Alice Johnson', '+94773456789'),
                                                                                        ('456789', 'user4@example.com', 'Bob Brown', '+94774567890'),
                                                                                        ('567890', 'user5@example.com', 'Charlie Davis', '+94775678901'),
                                                                                        ('678901', 'user6@example.com', 'David Evans', '+94776789012'),
                                                                                        ('789012', 'user7@example.com', 'Eve Harris', '+94777890123'),
                                                                                        ('890123', 'user8@example.com', 'Frank Green', '+94778901234'),
                                                                                        ('901234', 'user9@example.com', 'Grace Hall', '+94779012345'),
                                                                                        ('012345', 'user10@example.com', 'Hank White', '+94770123456');

ALTER TABLE public.users
    OWNER TO dasun;
