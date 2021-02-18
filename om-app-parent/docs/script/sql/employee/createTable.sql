

--包含audit包中的init_0.sql、employee包的所有sql文件和fileupload包的两个Init_0.sql文件


--员工信息更新申请表
CREATE TABLE My_EmployeeTask
(
  ID           VARCHAR(64) NOT NULL,
  userId       VARCHAR(32) GENERATED ALWAYS AS (JSON_CONTENT ->> '$.userId') VIRTUAL,
  name         VARCHAR(64) GENERATED ALWAYS AS (JSON_CONTENT ->> '$.name') VIRTUAL,
  deptName     VARCHAR(64)         GENERATED ALWAYS AS (JSON_CONTENT ->> '$.deptName') VIRTUAL,
  isPass       VARCHAR(5) GENERATED ALWAYS AS (JSON_CONTENT ->> '$.isPass') VIRTUAL,
  createdAt    VARCHAR(32) GENERATED ALWAYS AS (JSON_CONTENT ->> '$.createdAt') VIRTUAL,
  JSON_CONTENT JSON        NOT NULL,
  PRIMARY KEY (ID)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
  
--HR消息表  
 CREATE TABLE My_Message
(
  ID           VARCHAR(64) NOT NULL,
  initUserId   VARCHAR(32) GENERATED ALWAYS AS (JSON_CONTENT ->> '$.initUserId') VIRTUAL,
  reader       VARCHAR(32) GENERATED ALWAYS AS (JSON_CONTENT ->> '$.reader') VIRTUAL,
  isRead       VARCHAR(2) GENERATED ALWAYS AS (JSON_CONTENT ->> '$.isRead') VIRTUAL,
  JSON_CONTENT JSON        NOT NULL,
  PRIMARY KEY (ID)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;



 CREATE TABLE my_children
(
  ID               VARCHAR(32) NOT NULL,
  JSON_CONTENT JSON        NOT NULL,
  userId               VARCHAR(50) GENERATED ALWAYS AS (JSON_CONTENT ->> '$.userId') VIRTUAL,
  PRIMARY KEY (ID)
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;



CREATE TABLE my_contractinfo
(
  ID               VARCHAR(64) NOT NULL,
  JSON_CONTENT JSON        NOT NULL,
  userId               VARCHAR(50) GENERATED ALWAYS AS (JSON_CONTENT ->> '$.userId') VIRTUAL,
  PRIMARY KEY (ID)
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;




  CREATE TABLE my_education
(
  ID               VARCHAR(64) NOT NULL,
 
  JSON_CONTENT JSON        NOT NULL,
  userId               VARCHAR(50) GENERATED ALWAYS AS (JSON_CONTENT ->> '$.userId') VIRTUAL,
  PRIMARY KEY (ID)
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;





  CREATE TABLE my_employee
(
  ID               VARCHAR(64) NOT NULL,
  JSON_CONTENT JSON        NOT NULL,
  userId                VARCHAR(50) GENERATED ALWAYS AS (JSON_CONTENT ->> '$.userId') VIRTUAL,
    PRIMARY KEY (ID)
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;


 CREATE TABLE my_lyWorkexperience
(
  ID               VARCHAR(64) NOT NULL,
  JSON_CONTENT JSON        NOT NULL,
   userId              VARCHAR(50) GENERATED ALWAYS AS (JSON_CONTENT ->> '$.userId') VIRTUAL,
  PRIMARY KEY (ID)
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;



 CREATE TABLE my_OuterProject
(
  ID               VARCHAR(64) NOT NULL,
  JSON_CONTENT JSON        NOT NULL,
  userId             VARCHAR(50) GENERATED ALWAYS AS (JSON_CONTENT ->> '$.userId') VIRTUAL, 
  PRIMARY KEY (ID)
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;




 CREATE TABLE my_project
(
  ID               VARCHAR(64) NOT NULL,
  JSON_CONTENT JSON        NOT NULL,
  userId             VARCHAR(50) GENERATED ALWAYS AS (JSON_CONTENT ->> '$.userId') VIRTUAL, 
  PRIMARY KEY (ID)
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;




 CREATE TABLE my_resumption
(
  ID               VARCHAR(64) NOT NULL,
  JSON_CONTENT JSON        NOT NULL,
  userId               VARCHAR(50) GENERATED ALWAYS AS (JSON_CONTENT ->> '$.userId') VIRTUAL,
  PRIMARY KEY (ID)
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;



 CREATE TABLE my_skill
(
  ID               VARCHAR(64) NOT NULL,
  JSON_CONTENT JSON        NOT NULL,
   userId              VARCHAR(50) GENERATED ALWAYS AS (JSON_CONTENT ->> '$.userId') VIRTUAL,
  PRIMARY KEY (ID)
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;



 CREATE TABLE my_workexperience
(
  ID               VARCHAR(64) NOT NULL,
  JSON_CONTENT JSON        NOT NULL,
   userId              VARCHAR(50) GENERATED ALWAYS AS (JSON_CONTENT ->> '$.userId') VIRTUAL,
  PRIMARY KEY (ID)
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;



--文件上传表
CREATE TABLE My_FileInfo
(
  id VARCHAR(32) NOT NULL,
  userId VARCHAR(32) NOT NULL,
  fileType VARCHAR(2) NOT NULL,
  fileName VARCHAR(64),
  content  longblob        NOT NULL,
  comment  VARCHAR(32), 
  createdAt VARCHAR(32) NOT NULL,
  PRIMARY KEY (id)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;



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



 CREATE TABLE my_InnerProject
(
  ID               VARCHAR(64) NOT NULL,
  JSON_CONTENT JSON        NOT NULL,
  userId             VARCHAR(50) GENERATED ALWAYS AS (JSON_CONTENT ->> '$.userId') VIRTUAL, 
  PRIMARY KEY (ID)
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;









