CREATE SCHEMA `medicine_donation` ;
USE medicine_donation;

CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    full_name VARCHAR(101),
    password VARCHAR(1000),
    nic VARCHAR(12) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(20),
    date_of_birth DATE,
    user_type_id BIGINT NOT NULL,
    status_id BIGINT NOT NULL DEFAULT 1,
    profile_image_url VARCHAR(500),
    email_verified BOOLEAN NOT NULL DEFAULT FALSE,
    phone_verified BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_login TIMESTAMP NULL,
    wallet_id BIGINT);

    INSERT INTO users (
    username, first_name, last_name, full_name, password, nic, email, phone_number,
    date_of_birth, user_type_id, status_id, profile_image_url, email_verified,
    phone_verified, created_at, updated_at, last_login, wallet_id
) VALUES
(
    'jdoe', 'John', 'Doe', 'John Doe', '$2a$12$oXKsXzwlZha7S4aV3LeenOE9km7jJdGPW7HsGYNfjHzR9bb5wgK4G',
    '123456789V', 'jdoe@example.com', '0771234567', '1990-05-14',
    1, 1, 'https://example.com/images/jdoe.png', TRUE, TRUE,
    NOW(), NOW(), NULL, 101
),
(
    'asmith', 'Alice', 'Smith', 'Alice Smith', '$2a$12$oXKsXzwlZha7S4aV3LeenOE9km7jJdGPW7HsGYNfjHzR9bb5wgK4G',
    '987654321V', 'asmith@example.com', '0712345678', '1992-11-02',
    2, 1, 'https://example.com/images/asmith.png', FALSE, TRUE,
    NOW(), NOW(), NULL, 102
),
(
    'brucew', 'Bruce', 'Wayne', 'Bruce Wayne', '$2a$12$oXKsXzwlZha7S4aV3LeenOE9km7jJdGPW7HsGYNfjHzR9bb5wgK4G',
    '200012345678', 'bruce.wayne@example.com', '0759876543', '1985-02-19',
    3, 1, 'https://example.com/images/brucew.png', TRUE, FALSE,
    NOW(), NOW(), NOW(), 103
);

SELECT * FROM USERS;

