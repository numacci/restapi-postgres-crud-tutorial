CREATE TABLE customer (
    id VARCHAR(10) PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    phone_number VARCHAR(11) NOT NULL,
    post_code VARCHAR(7) NOT NULL
);