
CREATE TABLE ROLE
(
    ID  BIGINT AUTO_INCREMENT PRIMARY KEY,
    CREATED_ON  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATED_ON  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    NAME VARCHAR(255)
);

CREATE TABLE USER_IN_ROLE
(
    USER_ID BIGINT NOT NULL,
    ROLE_ID BIGINT NOT NULL
);

INSERT INTO ROLE VALUES(1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'ADMIN');
INSERT INTO ROLE VALUES(2,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'USER');

INSERT INTO USER_IN_ROLE VALUES(1,1);


INSERT INTO ACCESS_OBJECT (NAME) VALUES ('ROLE_R'),('ROLE_E'),('ROLE_D');

INSERT INTO ROLE_ACCESS_OBJECT(ROLE_ID,ACCESS_OBJECT_ID)
SELECT 1, ID FROM ACCESS_OBJECT WHERE NAME IN ('ROLE_R','ROLE_E','ROLE_D');
