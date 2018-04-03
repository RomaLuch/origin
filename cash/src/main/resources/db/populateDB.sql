DELETE FROM records;
DELETE FROM categories;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO categories (name, user_id) VALUES
  ('EAT', 100000),
  ('ROAD', 100000),
  ('SHOPING',100001)   ;

INSERT INTO records (dateTime, description, amount, user_id, category_id) VALUES
  ('2015-05-30 10:00:00', 'BIG_MAC', 500, 100000,100002),
  ('2015-05-30 13:00:00', 'COLA', 1000, 100000, 100002),
  ('2015-05-30 20:00:00', 'FANTA', 500, 100000, 100002),
  ('2015-05-31 10:00:00', 'SUSHI', 500, 100000, 100002),
  ('2015-05-31 13:00:00', 'BUS', 1000, 100000, 100003),
  ('2015-05-31 20:00:00', 'TRAM', 510, 100000, 100003),
  ('2015-06-01 14:00:00', 'SHOSE', 510, 100001, 100004),
  ('2015-06-01 21:00:00', 'phone', 1500, 100001, 100004);
