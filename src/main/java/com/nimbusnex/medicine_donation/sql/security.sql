CREATE SCHEMA `medicine_donation`;
USE medicine_donation;

-- Drop existing users table if exists
DROP TABLE IF EXISTS users;

-- Create users table
CREATE TABLE users
(
    id                BIGINT PRIMARY KEY AUTO_INCREMENT,
    username          VARCHAR(50)   NOT NULL UNIQUE,
    first_name        VARCHAR(50)   NOT NULL,
    last_name         VARCHAR(50)   NOT NULL,
    full_name         VARCHAR(101),
    nic               VARCHAR(12)   NOT NULL UNIQUE,
    email             VARCHAR(100)  NOT NULL UNIQUE,
    password    VARCHAR(1000) NOT NULL,
    phone_number      VARCHAR(20),
    date_of_birth     DATE,
    user_type_id      BIGINT        NOT NULL,
    status_id         BIGINT        NOT NULL DEFAULT 1,
    profile_image_url VARCHAR(500),
    email_verified    BOOLEAN       NOT NULL DEFAULT FALSE,
    phone_verified    BOOLEAN       NOT NULL DEFAULT FALSE,
    created_at        TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at        TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_login        TIMESTAMP NULL,
    wallet_id         BIGINT,

    -- Indexes for performance
    INDEX             idx_username (username),
    INDEX             idx_email (email),
    INDEX             idx_nic (nic),
    INDEX             idx_user_type (user_type_id),
    INDEX             idx_status (status_id)
);

-- Create roles table
CREATE TABLE roles
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    created_at  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by  BIGINT,
    updated_at  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by  BIGINT,

    -- Index for performance
    INDEX       idx_role_name ( name)
);

-- Create privileges table
CREATE TABLE privileges
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by  BIGINT,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by  BIGINT,

    -- Index for performance
    INDEX       idx_privilege_name ( name)
);

-- Create user_roles junction table
CREATE TABLE user_roles
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id    BIGINT    NOT NULL,
    role_id    BIGINT    NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- Foreign key constraints
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE,

    -- Unique constraint to prevent duplicate role assignments
    UNIQUE KEY unique_user_role (user_id, role_id),

    -- Indexes for performance
    INDEX      idx_user_roles_user (user_id),
    INDEX      idx_user_roles_role (role_id)
);

-- Create role_privileges junction table
CREATE TABLE role_privileges
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    role_id      BIGINT    NOT NULL,
    privilege_id BIGINT    NOT NULL,
    created_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- Foreign key constraints
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE,
    FOREIGN KEY (privilege_id) REFERENCES privileges (id) ON DELETE CASCADE,

    -- Unique constraint to prevent duplicate privilege assignments
    UNIQUE KEY unique_role_privilege (role_id, privilege_id),

    -- Indexes for performance
    INDEX        idx_role_privileges_role (role_id),
    INDEX        idx_role_privileges_privilege (privilege_id)
);

-- Insert sample roles
INSERT INTO roles (name, description)
VALUES ('ROLE_SUPER_ADMIN', 'Super Administrator with full system access'),
       ('ROLE_ADMIN', 'Administrator with most system access'),
       ('ROLE_MANAGER', 'Manager with limited administrative access'),
       ('ROLE_USER', 'Regular user with basic access');

-- Insert sample privileges
INSERT INTO privileges (name, description)
VALUES
-- User Management
('READ_USERS', 'View user information'),
('WRITE_USERS', 'Create and update users'),
('DELETE_USERS', 'Delete users'),
('MANAGE_USER_ROLES', 'Assign/remove user roles'),

-- Admin Management
('ADD_ADMIN', 'Create new admin users'),
('REMOVE_ADMIN', 'Remove admin privileges'),

-- System Management
('SYSTEM_SETTINGS', 'Manage system configuration'),
('VIEW_AUDIT_LOGS', 'View system audit logs'),
('BACKUP_RESTORE', 'Perform system backup and restore'),

-- Reports
('READ_REPORTS', 'View reports'),
('GENERATE_REPORTS', 'Generate new reports'),

-- Profile Management
('READ_PROFILE', 'View own profile'),
('UPDATE_PROFILE', 'Update own profile');

-- Assign privileges to roles
INSERT INTO role_privileges (role_id, privilege_id)
VALUES
-- SUPER_ADMIN gets all privileges
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 13),

-- ADMIN gets most privileges except ADD_ADMIN and REMOVE_ADMIN
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(2, 7),
(2, 8),
(2, 10),
(2, 11),
(2, 12),
(2, 13),

-- MANAGER gets limited privileges
(3, 1),
(3, 10),
(3, 11),
(3, 12),
(3, 13),

-- USER gets basic privileges
(4, 12),
(4, 13);

INSERT INTO users (username, first_name, last_name, full_name, password, nic, email, phone_number,
                   date_of_birth, user_type_id, status_id, profile_image_url, email_verified,
                   phone_verified, created_at, updated_at, last_login, wallet_id)
VALUES ('jdoe', 'John', 'Doe', 'John Doe', '$2a$12$oXKsXzwlZha7S4aV3LeenOE9km7jJdGPW7HsGYNfjHzR9bb5wgK4G',
        '123456789V', 'jdoe@example.com', '0771234567', '1990-05-14',
        1, 1, 'https://example.com/images/jdoe.png', TRUE, TRUE,
        NOW(), NOW(), NULL, 101),
       ('asmith', 'Alice', 'Smith', 'Alice Smith', '$2a$12$oXKsXzwlZha7S4aV3LeenOE9km7jJdGPW7HsGYNfjHzR9bb5wgK4G',
        '987654321V', 'asmith@example.com', '0712345678', '1992-11-02',
        2, 1, 'https://example.com/images/asmith.png', FALSE, TRUE,
        NOW(), NOW(), NULL, 102),
       ('brucew', 'Bruce', 'Wayne', 'Bruce Wayne', '$2a$12$oXKsXzwlZha7S4aV3LeenOE9km7jJdGPW7HsGYNfjHzR9bb5wgK4G',
        '200012345678', 'bruce.wayne@example.com', '0759876543', '1985-02-19',
        3, 1, 'https://example.com/images/brucew.png', TRUE, FALSE,
        NOW(), NOW(), NOW(), 103);

SELECT * FROM USERS;

-- Assign roles to existing users

-- Assign ROLE_SUPER_ADMIN to John Doe (user_id = 1)
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);

-- Assign ROLE_ADMIN to Alice Smith (user_id = 2)
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);

-- Assign ROLE_MANAGER to Bruce Wayne (user_id = 3)
INSERT INTO user_roles (user_id, role_id) VALUES (3, 3);

-- Optional: You can assign multiple roles to a single user
-- For example, give Bruce Wayne both MANAGER and USER roles:
INSERT INTO user_roles (user_id, role_id) VALUES (3, 4);

-- Verify the role assignments
SELECT
    u.username,
    u.full_name,
    r.name AS role_name,
    r.description AS role_description
FROM users u
         JOIN user_roles ur ON u.id = ur.user_id
         JOIN roles r ON ur.role_id = r.id
ORDER BY u.username, r.name;

-- Query to see all privileges for each user (through their roles)
SELECT
    u.username,
    u.full_name,
    r.name AS role_name,
    p.name AS privilege_name,
    p.description AS privilege_description
FROM users u
         JOIN user_roles ur ON u.id = ur.user_id
         JOIN roles r ON ur.role_id = r.id
         JOIN role_privileges rp ON r.id = rp.role_id
         JOIN privileges p ON rp.privilege_id = p.id
ORDER BY u.username, r.name, p.name;

-- Query to check specific user's roles and privileges
-- Replace 'jdoe' with any username you want to check
SELECT
    u.username,
    GROUP_CONCAT(DISTINCT r.name ORDER BY r.name) AS roles,
    GROUP_CONCAT(DISTINCT p.name ORDER BY p.name) AS privileges
FROM users u
    LEFT JOIN user_roles ur ON u.id = ur.user_id
    LEFT JOIN roles r ON ur.role_id = r.id
    LEFT JOIN role_privileges rp ON r.id = rp.role_id
    LEFT JOIN privileges p ON rp.privilege_id = p.id
WHERE u.username = 'jdoe'
GROUP BY u.id, u.username;