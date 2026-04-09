INSERT INTO app_user (email, password, pseudo, admin) VALUES
        ('a@a', '$2a$10$Wt8piqncC156t0XbZjwjvu59xy1e8kkM05r6UOCAekHcSigJttyWS', 'User A', true),
        ('b@b', '$2a$10$Wt8piqncC156t0XbZjwjvu59xy1e8kkM05r6UOCAekHcSigJttyWS', 'User B', false),
        ('c@c', '$2a$10$Wt8piqncC156t0XbZjwjvu59xy1e8kkM05r6UOCAekHcSigJttyWS', 'User C', false);

INSERT INTO recipe (name, creator_id,return_count) VALUES
        ('tarte aux pommes', 1,0),
        ('paella', 2,0),
        ('croissant', 1,0);

INSERT INTO tag (name) VALUES
        ('sud'),
        ('dessert'),
        ('arachide');

INSERT INTO recipe_tags (recipe_id, tags_id) VALUES
        (1, 2),
        (2, 1),
        (3, 2),
        (3, 3);

INSERT INTO question (content, user_id) VALUES
        ('Je veux un dessert', 1),
        ('Quelque chose du sud', 2),
        ('Une recette sans arachide', 3);

INSERT INTO question_include_tag (question_id, tag_id) VALUES
        (1, 2),
        (2, 1);

INSERT INTO question_exclude_tag (question_id, tag_id) VALUES
        (3, 3);