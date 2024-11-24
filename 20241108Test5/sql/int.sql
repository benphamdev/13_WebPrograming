USE web_program;

CREATE TABLE users (
                           username  VARCHAR(255) UNIQUE,
                           password VARCHAR(255) NOT NULL,
                           phone VARCHAR(20),
                           full_name VARCHAR(100),
                           email VARCHAR(100) UNIQUE,
                           is_admin BOOLEAN DEFAULT 0,
                           is_active BOOLEAN DEFAULT 1,
                           images VARCHAR(500),
                           PRIMARY KEY (username)
)

CREATE TABLE category (
                              id INT PRIMARY KEY AUTO_INCREMENT,
                              category_name VARCHAR(100) NOT NULL,
                              code VARCHAR(50) UNIQUE NOT NULL,
                              images VARCHAR(500),
                              status BOOLEAN DEFAULT 1
)

CREATE TABLE videos (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            title VARCHAR(255) NOT NULL,
                            poster VARCHAR(500),
                            views INT DEFAULT 0,
                            description TEXT,
                            is_active BOOLEAN DEFAULT 1,
                            category_id INT,
                            FOREIGN KEY (category_id) REFERENCES category(id)
)

CREATE TABLE favorites (
                               id INT PRIMARY KEY AUTO_INCREMENT,
                               liked_date DATE,
                               video_id INT,
                               username VARCHAR(255),
                               FOREIGN KEY (video_id) REFERENCES videos(id),
                               FOREIGN KEY (username) REFERENCES users(username)
)

CREATE TABLE shares (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            emails VARCHAR(255),
                            shared_date DATE,
                            username VARCHAR(255),
                            video_id INT,
                            FOREIGN KEY (username) REFERENCES users(username),
                            FOREIGN KEY (video_id) REFERENCES videos(id)
)

-- Insert sample data into users
    INSERT INTO users (username, password, phone, full_name, email, is_admin, is_active, images)
VALUES
    ('john_doe', 'password123', '1234567890', 'John Doe', 'john@example.com', 1, 1, NULL),
    ('jane_smith', 'password456', '0987654321', 'Jane Smith', 'jane@example.com', 0, 1, NULL),
    ('alice_wonder', 'password789', '1122334455', 'Alice Wonder', 'alice@example.com', 0, 1, NULL)

-- Insert sample data into category
INSERT INTO category (category_name, code, images, status)
VALUES
    ('Technology', 'TECH', NULL, 1),
    ('Education', 'EDU', NULL, 1),
    ('Entertainment', 'ENT', NULL, 1)

-- Insert sample data into videos
INSERT INTO videos (title, poster, views, description, is_active, category_id)
VALUES
    ('How to Code in Python', NULL, 1000, 'A beginner guide to coding in Python.', 1, 1),
    ('Understanding AI', NULL, 1500, 'Introduction to Artificial Intelligence concepts.', 1, 1),
    ('Top 10 Movies of 2024', NULL, 3000, 'A countdown of the top movies.', 1, 3)

-- Insert sample data into favorites
INSERT INTO favorites (liked_date, video_id, username)
VALUES
    ('2024-01-15', 1, 'john_doe'),
    ('2024-02-10', 2, 'jane_smith'),
    ('2024-03-22', 3, 'alice_wonder')

-- Insert sample data into shares
INSERT INTO shares (emails, shared_date, username, video_id)
VALUES
    ('friend1@example.com,friend2@example.com', '2024-01-16', 'john_doe', 1),
    ('colleague@example.com', '2024-02-11', 'jane_smith', 2),
    ('family@example.com', '2024-03-23', 'alice_wonder', 3)
