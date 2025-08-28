CREATE TABLE un_authenticate_records (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    status INT NOT NULL,
    code VARCHAR(50) NOT NULL,
    message VARCHAR(255) NOT NULL,
    timestamp DATETIME NOT NULL,
    error_message VARCHAR(500),
    path VARCHAR(255),
    user_id VARCHAR(100),
    entered_password VARCHAR(255)
);
