CREATE TABLE IF NOT EXISTS dogs(
    id BIGINT,
    name TEXT,
    breed TEXT,
    age INT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS owner(
    id BIGINT,
    first_name TEXT,
    last_name TEXT,
    street TEXT,
    home TEXT,
    district TEXT,
    postalCode int,
    primary key (id)
)