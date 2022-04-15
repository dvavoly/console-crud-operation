CREATE TABLE developer
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(200) NOT NULL,
    last_name  VARCHAR(200) NOT NULL,
    status     VARCHAR(8) DEFAULT 'ACTIVE'
)