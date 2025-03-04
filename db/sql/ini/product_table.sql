# CREATE DATABASE  IF NOT EXISTS `e_shop` ;
# use e_shop;
use sky_take_out;

DROP TABLE IF EXISTS product;
CREATE TABLE product
(
    id            bigint         NOT NULL AUTO_INCREMENT COMMENT '主键',
    name          varchar(50)    NOT NULL COMMENT '商品名称',
    description   text COMMENT '商品描述',
    picture       varchar(512) COMMENT '商品图片URL',
    price         decimal(10, 2) NOT NULL COMMENT '商品价格',
    status        tinyint        NOT NULL DEFAULT 1 COMMENT '商品状态(0-下架, 1-上架)',
    stock         int            NOT NULL DEFAULT 0 COMMENT '库存数量',
    sales         bigint         NOT NULL DEFAULT 0 COMMENT '销量',
    categories_id bigint         NOT NULL COMMENT '分类ID',
    create_time   datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    create_user   bigint         NOT NULL COMMENT '创建人',
    update_time   datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    update_user   bigint         NOT NULL COMMENT '更新人',
    is_deleted    tinyint        NOT NULL DEFAULT 0 COMMENT '是否删除(0-否, 1-是)',
    PRIMARY KEY (id),
    UNIQUE KEY uk_name (name) COMMENT '商品名称唯一索引',
    INDEX idx_price (price) COMMENT '价格索引',
    INDEX idx_create_time_deleted (create_time, is_deleted) COMMENT '创建时间和删除状态复合索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='商品表';

DROP TABLE IF EXISTS category;
CREATE TABLE category
(
    id          bigint                       NOT NULL AUTO_INCREMENT COMMENT '主键',
    type        int                                   DEFAULT NULL COMMENT '类型   1 菜品分类 2 套餐分类',
    name        varchar(32) COLLATE utf8_bin NOT NULL COMMENT '分类名称',
    sort        int                          NOT NULL DEFAULT '0' COMMENT '顺序',
    status      int                                   DEFAULT NULL COMMENT '分类状态 0:禁用，1:启用',
    create_time datetime                              DEFAULT NULL COMMENT '创建时间',
    update_time datetime                              DEFAULT NULL COMMENT '更新时间',
    create_user bigint                                DEFAULT NULL COMMENT '创建人',
    update_user bigint                                DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (id),
    UNIQUE KEY idx_category_name (name)
) ENGINE = InnoDB
  AUTO_INCREMENT = 23
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8_bin COMMENT ='菜品及套餐分类';

INSERT INTO category
VALUES (11, 1, '酒水饮料', 10, 1, '2022-06-09 22:09:18', '2022-06-09 22:09:18', 1, 1);
INSERT INTO category
VALUES (12, 1, '传统主食', 9, 1, '2022-06-09 22:09:32', '2022-06-09 22:18:53', 1, 1);
INSERT INTO category
VALUES (13, 2, '人气套餐', 12, 1, '2022-06-09 22:11:38', '2022-06-10 11:04:40', 1, 1);
INSERT INTO category
VALUES (15, 2, '商务套餐', 13, 1, '2022-06-09 22:14:10', '2022-06-10 11:04:48', 1, 1);
INSERT INTO category
VALUES (16, 1, '蜀味烤鱼', 4, 1, '2022-06-09 22:15:37', '2022-08-31 14:27:25', 1, 1);
INSERT INTO category
VALUES (17, 1, '蜀味牛蛙', 5, 1, '2022-06-09 22:16:14', '2022-08-31 14:39:44', 1, 1);
INSERT INTO category
VALUES (18, 1, '特色蒸菜', 6, 1, '2022-06-09 22:17:42', '2022-06-09 22:17:42', 1, 1);
INSERT INTO category
VALUES (19, 1, '新鲜时蔬', 7, 1, '2022-06-09 22:18:12', '2022-06-09 22:18:28', 1, 1);
INSERT INTO category
VALUES (20, 1, '水煮鱼', 8, 1, '2022-06-09 22:22:29', '2022-06-09 22:23:45', 1, 1);
INSERT INTO category
VALUES (21, 1, '汤类', 11, 1, '2022-06-10 10:51:47', '2022-06-10 10:51:47', 1, 1);

DROP TABLE IF EXISTS product_category;
CREATE TABLE product_category
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    product_id  bigint NOT NULL COMMENT '商品ID',
    category_id bigint NOT NULL COMMENT '分类ID',
    PRIMARY KEY (id),
    INDEX idx_product_id (product_id) COMMENT '商品ID索引',
    INDEX idx_category_id (category_id) COMMENT '分类ID索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='商品与分类关联表';

-- Insert test data into product table
CREATE PROCEDURE InsertTestProducts()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE category_count INT;

    -- Get the number of categories
    SELECT COUNT(*) INTO category_count FROM category;

    WHILE i <= 1000 DO
        INSERT INTO product (
            name,
            description,
            picture,
            price,
            status,
            stock,
            sales,
            categories_id,
            create_user,
            update_user
        ) VALUES (
            CONCAT('Test Product ', i),
            CONCAT('This is a description for test product ', i),
            'https://example.com/default-product-image.jpg',
            ROUND(RAND() * 1000 + 10, 2), -- Random price between 10 and 1010
            FLOOR(RAND() * 2), -- Random status 0 or 1
            FLOOR(RAND() * 1000), -- Random stock between 0 and 999
            FLOOR(RAND() * 500), -- Random sales between 0 and 499
            FLOOR(RAND() * category_count) + 11, -- Random category_id from existing categories
            1, -- create_user
            1  -- update_user
        );

        -- Link product to category in product_category table
        INSERT INTO product_category (
            product_id,
            category_id
        ) VALUES (
            LAST_INSERT_ID(),
            FLOOR(RAND() * category_count) + 11
        );

        SET i = i + 1;
    END WHILE;
END;

-- Execute the procedure
CALL InsertTestProducts();

-- Clean up
DROP PROCEDURE IF EXISTS InsertTestProducts;
