CREATE TABLE shedlock
(
  name       VARCHAR(64)  NOT NULL,
  lock_until DATETIME     NULL,
  locked_at  DATETIME     NULL,
  locked_by  VARCHAR(255) NOT NULL,
  PRIMARY KEY (name)
);

CREATE TABLE DOMAIN_EVENT
(
  ID           VARCHAR(64) NOT NULL,
  JSON_CONTENT JSON        NOT NULL,
  TYPE         VARCHAR(100) GENERATED ALWAYS AS (JSON_CONTENT ->> '$._type') VIRTUAL,
  CREATED_AT   VARCHAR(50) GENERATED ALWAYS AS (JSON_CONTENT ->> '$.createdAt') VIRTUAL,
  STATUS       VARCHAR(10) NOT NULL DEFAULT 'CREATED',
  PRIMARY KEY (ID)
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

CREATE TABLE DOMAIN_EVENT_RECEIVE_RECORD
(
  EVENT_ID    CHAR(64) NOT NULL,
  RECORDED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (EVENT_ID)
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
