INSERT INTO app_user (email, password, pseudo) VALUES
        ('a@a', 'root', 'User A'),
        ('b@b', 'root', 'User B'),
        ('b@b', 'root', 'User C');

INSERT INTO recipe (name, creator_id) VALUES
        ('tarte aux pommes', 1),
        ('paella', 2),
        ('croissant', 1);

INSERT INTO tag (name) VALUES
        ('sud'),
        ('dessert'),
        ('arachide');

INSERT INTO recipe_tags (recipe_id, tags_id) VALUES
        (1, 2),
        (2, 1),
        (3, 2),
        (3, 3);