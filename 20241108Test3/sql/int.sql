-- Tạo Bảng users
CREATE TABLE user (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      email VARCHAR(255) UNIQUE NOT NULL,
                      full_name VARCHAR(255),
                      phone VARCHAR(20),
                      password VARCHAR(255),
                      signup_date DATE,
                      last_login TIMESTAMP,
                      is_admin BOOLEAN DEFAULT FALSE
);

-- Tạo Bảng roles
CREATE TABLE role (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(50),
                      code VARCHAR(20) UNIQUE
);

-- Tạo Bảng user_role (quan hệ nhiều-nhiều giữa User và Role)

CREATE TABLE user_role (
                           user_id BIGINT NOT NULL,
                           role_id BIGINT NOT NULL,
                           PRIMARY KEY (user_id, role_id),
                           FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                           FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

-- Tạo Bảng authors

CREATE TABLE author (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        author_name VARCHAR(255) NOT NULL,
                        date_of_birth DATE
);
-- Tạo Bảng books

CREATE TABLE book (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      isbn VARCHAR(20) UNIQUE NOT NULL,
                      title VARCHAR(255) NOT NULL,
                      publisher VARCHAR(255),
                      price DECIMAL(10, 2),
                      description TEXT,
                      publish_date DATE,
                      cover_image VARCHAR(255),
                      quantity INT DEFAULT 0
);
-- Tạo Bảng book_author (quan hệ nhiều-nhiều giữa Book và Author)

CREATE TABLE book_author (
                             book_id BIGINT NOT NULL,
                             author_id BIGINT NOT NULL,
                             PRIMARY KEY (book_id, author_id),
                             FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE,
                             FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE CASCADE
);

-- Tạo Bảng rating (quan hệ một-nhiều giữa User và Book qua Rating)

CREATE TABLE rating (
                        user_id BIGINT NOT NULL,
                        book_id BIGINT NOT NULL,
                        rating INT CHECK (rating BETWEEN 1 AND 5),
                        review_text TEXT,
                        PRIMARY KEY (user_id, book_id),
                        FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                        FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE
);

-- Thêm Dữ Liệu vào Bảng roles

INSERT INTO role (name, code) VALUES ('Admin', 'ADMIN');
INSERT INTO role (name, code) VALUES ('User', 'USER');
INSERT INTO role (name, code) VALUES ('Editor', 'EDITOR');

-- Thêm Dữ Liệu vào Bảng users

INSERT INTO user (email, full_name, phone, password, signup_date, last_login, is_admin)
VALUES ('john.doe@example.com', 'John Doe', '1234567890', 'password123', '2023-01-01', NOW(), TRUE);

INSERT INTO user (email, full_name, phone, password, signup_date, last_login, is_admin)
VALUES ('jane.smith@example.com', 'Jane Smith', '0987654321', 'password456', '2023-02-15', NOW(), FALSE);

INSERT INTO user (email, full_name, phone, password, signup_date, last_login, is_admin)
VALUES ('bob.brown@example.com', 'Bob Brown', '1122334455', 'password789', '2023-03-10', NOW(), FALSE);

-- Thêm Dữ Liệu vào Bảng user_role (Quan hệ Nhiều-Nhiều giữa User và Role)

-- Gán role "Admin" cho John Doe
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);

-- Gán role "User" cho Jane Smith và Bob Brown
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO user_role (user_id, role_id) VALUES (3, 2);

-- Gán role "Editor" cho John Doe
INSERT INTO user_role (user_id, role_id) VALUES (1, 3);

-- Thêm Dữ Liệu vào Bảng authors

INSERT INTO author (author_name, date_of_birth) VALUES ('George Orwell', '1903-06-25');
INSERT INTO author (author_name, date_of_birth) VALUES ('Jane Austen', '1775-12-16');
INSERT INTO author (author_name, date_of_birth) VALUES ('Mark Twain', '1835-11-30');

-- Thêm Dữ Liệu vào Bảng books

INSERT INTO book (isbn, title, publisher, price, description, publish_date, cover_image, quantity)
VALUES ('9780451524935', '1984', 'Secker & Warburg', 9.99, 'A dystopian social science fiction novel and cautionary tale', '1949-06-08', NULL, 100);

INSERT INTO book (isbn, title, publisher, price, description, publish_date, cover_image, quantity)
VALUES ('9780141439518', 'Pride and Prejudice', 'T. Egerton', 12.99, 'A romantic novel of manners', '1813-01-28', NULL, 80);

INSERT INTO book (isbn, title, publisher, price, description, publish_date, cover_image, quantity)
VALUES ('9780486280615', 'Adventures of Huckleberry Finn', 'Chatto & Windus', 7.99, 'A novel about the adventures of a young boy and a runaway slave', '1884-12-10', NULL, 60);

-- Thêm Dữ Liệu vào Bảng book_author (Quan hệ Nhiều-Nhiều giữa Book và Author)

-- George Orwell là tác giả của "1984"
INSERT INTO book_author (book_id, author_id) VALUES (1, 1);

-- Jane Austen là tác giả của "Pride and Prejudice"
INSERT INTO book_author (book_id, author_id) VALUES (2, 2);

-- Mark Twain là tác giả của "Adventures of Huckleberry Finn"
INSERT INTO book_author (book_id, author_id) VALUES (3, 3);

-- Thêm Dữ Liệu vào Bảng rating

-- John Doe đánh giá 5 sao cho "1984"
INSERT INTO rating (user_id , book_id, rating, review_text) VALUES (1, 1, 5, 'Amazing book! A must-read.');

-- Jane Smith đánh giá 4 sao cho "Pride and Prejudice"
INSERT INTO rating (user_id, book_id, rating, review_text) VALUES (2, 2, 4, 'Loved the story and the characters.');

-- Bob Brown đánh giá 3 sao cho "Adventures of Huckleberry Finn"
INSERT INTO rating (user_id, book_id, rating, review_text) VALUES (3, 3, 3, 'Interesting read, but a bit slow at times');