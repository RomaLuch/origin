DROP TABLE IF EXISTS records;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;



DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE categories
(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  user_id     INTEGER   NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE TABLE records (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  dateTime   TIMESTAMP NOT NULL,
  description TEXT      NOT NULL,
  amount    INTEGER       NOT NULL,
  user_id     INTEGER   NOT NULL,
  category_id     INTEGER   NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX record_unique_user_datetime_idx ON records (user_id, datetime);