CREATE TABLE my_contractinfo
(
  ID               VARCHAR(64) NOT NULL,
  JSON_CONTENT JSON        NOT NULL,
  userId               VARCHAR(50) GENERATED ALWAYS AS (JSON_CONTENT ->> '$.userId') VIRTUAL,
  PRIMARY KEY (ID)
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;