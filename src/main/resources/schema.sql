
CREATE TABLE IF NOT EXISTS owner(
    id BIGINT AUTO_INCREMENT,
    first_name TEXT,
    last_name TEXT,
    street TEXT,
    home TEXT,
    district TEXT,
    postalCode int,
    number TEXT,
    primary key (id)
);
CREATE TABLE IF NOT EXISTS dogs(
       id BIGINT AUTO_INCREMENT,
       name TEXT,
       breed TEXT,
       age INT,
       owner_id BIGINT,
       PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS event(
       id BIGINT AUTO_INCREMENT,
       title TEXT,
       content TEXT,
       starting TEXT,
       ending TEXT,
       dog_id BIGINT,
       PRIMARY KEY (id)
);
ALTER TABLE dogs ADD FOREIGN KEY (owner_id) REFERENCES owner(id);
ALTER TABLE event ADD FOREIGN KEY (dog_id) REFERENCES dogs(id);

INSERT INTO owner(first_name,last_name,street,home,district,postalcode) VALUES ('Ronja','Strunkowski','Barkemeyersweg','11','Hude','27798');

