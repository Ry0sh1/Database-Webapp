
CREATE TABLE IF NOT EXISTS owner
(
    id         BIGINT AUTO_INCREMENT,
    first_name TEXT,
    last_name  TEXT,
    street     TEXT,
    home       TEXT,
    district   TEXT,
    postalCode int,
    number     TEXT,
    user_id BIGINT,
    primary key (id)
);
CREATE TABLE IF NOT EXISTS dogs
(
    id       BIGINT AUTO_INCREMENT,
    name     TEXT,
    breed    TEXT,
    age      INT,
    owner_id BIGINT,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS event
(
    id          BIGINT AUTO_INCREMENT,
    title       TEXT,
    content     TEXT,
    event_day   INT,
    event_month INT,
    event_year  INT,
    starting    TEXT,
    ending      TEXT,
    dog_id      BIGINT,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS _user(
    id BIGINT AUTO_INCREMENT,
    username TEXT,
    password TEXT,
    email TEXT,
    authorities TEXT,
    owner_id BIGINT,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS mail(
    id BIGINT AUTO_INCREMENT,
    author BIGINT,
    recipient BIGINT,
    title TEXT,
    content TEXT,
    viewed BOOLEAN,
    received TIMESTAMP,
    primary key (id)
);

ALTER TABLE _user ADD FOREIGN KEY (owner_id) REFERENCES owner(id);
ALTER TABLE owner ADD FOREIGN KEY (user_id) REFERENCES _user(id);
ALTER TABLE dogs ADD FOREIGN KEY (owner_id) REFERENCES owner(id);
ALTER TABLE event ADD FOREIGN KEY (dog_id) REFERENCES dogs(id);
ALTER TABLE mail ADD FOREIGN KEY (author) REFERENCES _user(id);
ALTER TABLE mail ADD FOREIGN KEY (recipient) REFERENCES _user(id);