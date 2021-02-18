
--请假流程demo
INSERT INTO `my_menu` VALUES (83, 0, '请假管理', 'layui-icon layui-icon layui-icon-app', '', '', 11, 0, '2020-08-19 18:43:13', '2020-08-20 11:43:19');

INSERT INTO `my_menu` VALUES (84, 83, '请假', 'layui-icon layui-icon-play', '/workflow/leaveApply.html', '', 12, 1, '2020-08-21 09:17:54', '2020-08-21 09:20:37');
INSERT INTO `my_menu` VALUES (85, 84, '提交', 'layui-icon ', '', 'leave:apply', 13, 2, '2020-08-23 16:32:59', '2020-08-23 16:32:59');


INSERT INTO `my_role_menu` VALUES (1, 83);
INSERT INTO `my_role_menu` VALUES (1, 84);
INSERT INTO `my_role_menu` VALUES (1, 85);