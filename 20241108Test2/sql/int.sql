-- 1. author Table
CREATE TABLE `author` (
                            `id` BIGINT AUTO_INCREMENT NOT NULL,
                            `code` NVARCHAR(255) DEFAULT NULL,
                            `name` NVARCHAR(255) DEFAULT NULL,
                            `createddate` DATETIME DEFAULT NULL,
                            `modifieddate` DATETIME DEFAULT NULL,
                            `createdby` NVARCHAR(150) DEFAULT NULL,
                            `modifiedby` NVARCHAR(150) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- 2. rating Table

CREATE TABLE `rating` (
                           `id` BIGINT AUTO_INCREMENT NOT NULL,
                           `content` TEXT DEFAULT NULL,
                           `user_id` BIGINT DEFAULT NULL,
                           `new_id` BIGINT DEFAULT NULL,
                           `status` INT DEFAULT NULL,
                           `createddate` DATETIME DEFAULT NULL,
                           `modifieddate` DATETIME DEFAULT NULL,
                           `createdby` NVARCHAR(150) DEFAULT NULL,
                           `modifiedby` NVARCHAR(150) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           FOREIGN KEY (`new_id`) REFERENCES `book` (`id`),
                           FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3. book Table
CREATE TABLE `book` (
                        `id` BIGINT AUTO_INCREMENT NOT NULL,
                        `title` NVARCHAR(350) DEFAULT NULL,
                        `thumbnail` NVARCHAR(255) DEFAULT NULL,
                        `shortdescription` TEXT DEFAULT NULL,
                        `content` TEXT DEFAULT NULL,
                        `categoryid` BIGINT DEFAULT NULL,
                        `createddate` DATETIME DEFAULT NULL,
                        `modifieddate` DATETIME DEFAULT NULL,
                        `createdby` NVARCHAR(150) DEFAULT NULL,
                        `modifiedby` NVARCHAR(150) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        FOREIGN KEY (`categoryid`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4. role Table
CREATE TABLE `role` (
                        `id` BIGINT AUTO_INCREMENT NOT NULL,
                        `name` VARCHAR(255) DEFAULT NULL,
                        `code` VARCHAR(255) DEFAULT NULL,
                        `createddate` DATETIME DEFAULT NULL,
                        `modifieddate` DATETIME DEFAULT NULL,
                        `createdby` VARCHAR(255) DEFAULT NULL,
                        `modifiedby` VARCHAR(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 5. user Table
CREATE TABLE `user` (
                        `id` BIGINT AUTO_INCREMENT NOT NULL,
                        `username` VARCHAR(150) DEFAULT NULL,
                        `password` VARCHAR(150) DEFAULT NULL,
                        `fullname` VARCHAR(150) DEFAULT NULL,
                        `status` INT DEFAULT NULL,
                        `roleid` BIGINT DEFAULT NULL,
                        `createddate` DATETIME DEFAULT NULL,
                        `modifieddate` DATETIME DEFAULT NULL,
                        `createdby` VARCHAR(150) DEFAULT NULL,
                        `modifiedby` VARCHAR(150) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        FOREIGN KEY (`roleid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- 6. user_role Table
CREATE TABLE `user_role` (
                             `userid` INT NOT NULL,
                             `roleid` INT NOT NULL,
                             PRIMARY KEY (`userid`, `roleid`),
                             FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
                             FOREIGN KEY (`roleid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- 7. Inserting data into author table

INSERT INTO `author` (`id`, `code`, `name`, `createddate`, `modifieddate`, `createdby`, `modifiedby`)
VALUES
(7, 'thoi-su', 'Thời sự', '2021-09-13 11:37:36', '2021-09-13 12:45:56', 'admin', 'admin'),
(8, 'The-thao', 'Thể thao', '2021-09-13 12:46:26', NULL, 'admin', NULL),
(9, 'Chinh-tri', 'Chính trị', '2021-09-13 12:46:50', NULL, 'admin', NULL),
(10, 'Giao-duc', 'Giáo dục', '2021-09-13 12:47:16', NULL, 'admin', NULL);

-- 8. Inserting data into role table

INSERT INTO `role` (`id`, `name`, `code`, `createddate`, `modifieddate`, `createdby`, `modifiedby`)
VALUES
    (1, 'ADMIN', 'ADMIN', '1900-01-01 00:00:06', NULL, NULL, NULL),
    (2, 'USER', 'USER', '1900-01-01 00:00:06', NULL, NULL, NULL);



-- 9. Inserting data into user table

INSERT INTO `user` (`id`, `username`, `password`, `fullname`, `status`, `roleid`, `createddate`, `modifieddate`, `createdby`, `modifiedby`) VALUES
    (1, 'admin', '123456', 'admin', 1, 1, NULL, NULL, NULL, NULL),
    (2, 'trungnh', '123456', 'Nguyễn Hữu Trung', 1, 2, NULL, NULL, NULL, NULL);

-- 10. Inserting data into user_role table

INSERT INTO `user_role` (`userid`, `roleid`)
VALUES (1, 1),
       (2, 2),
       (3, 2); -- Assuming a user with `id` 3 exists.
