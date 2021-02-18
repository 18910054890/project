
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