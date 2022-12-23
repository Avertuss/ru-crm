CREATE TABLE ACCESS_OBJECT
(
    ID  BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME  VARCHAR(255) UNIQUE
);

CREATE TABLE ACCESS_OBJECT_IN_ROLE_RIGHT
(
ID  BIGINT AUTO_INCREMENT PRIMARY KEY,
ROLE_ID BIGINT NOT NULL,
ACCESS_OBJECT_ID BIGINT NOT NULL,
CAN_READ BOOLEAN NOT NULL DEFAULT FALSE,
CAN_EDIT BOOLEAN NOT NULL DEFAULT FALSE,
CAN_DELETE BOOLEAN NOT NULL DEFAULT FALSE
);

