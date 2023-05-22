INSERT INTO `role` (id, name) VALUES (1, "ADMIN");
INSERT INTO `role` (id, name) VALUES (2, "USER");

INSERT INTO `users` (`id`, `created_at`, `email`, `is_hot_user`, `last_login_time`, `password`, `username`) VALUES
('0c577348-7148-426b-9b77-8b755a318c47',	'2023-05-21 15:30:28',	'teste@gmail.com',	CONV('1', 2, 10) + 0,	'2023-05-21 15:30:28',	'$2a$10$gkDF0CwtLW3u8f2qGwyKUO/I1NP6aGkZqW2e0831UKhf3AYobAYNK',	'Teste'),
('a152993f-6074-4c8f-8e8a-83408b54c3b9',	'2023-05-21 14:24:57',	'jjeanjacques10@github.com',	CONV('1', 2, 10) + 0,	'2023-05-21 14:24:57',	'$2a$10$rlUChxvf9XJt4kDhwlYHLeLSY3Pia1B6m1hAQwsXTGfT8VFLRtUkG',	'jjeanjacques10');

INSERT INTO `relationship` (`followed_id`, `follower_id`, `created_at`) VALUES
('0c577348-7148-426b-9b77-8b755a318c47',	'a152993f-6074-4c8f-8e8a-83408b54c3b9',	'2023-05-21 15:38:09'),
('a152993f-6074-4c8f-8e8a-83408b54c3b9',	'0c577348-7148-426b-9b77-8b755a318c47',	'2023-05-21 15:38:22');