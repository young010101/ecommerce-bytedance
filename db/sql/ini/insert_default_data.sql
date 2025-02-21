use sky_take_out;

-- 插入员工数据
INSERT INTO employee (name, username, password, phone, sex, id_number, create_time, update_time, is_deleted)
VALUES ('张三', 'zhangsan', '123456', '13800138000', '1', '123456789012345678', NOW(), NOW(), 0);
