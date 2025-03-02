CREATE DATABASE  IF NOT EXISTS `e_shop` ;
use e_shop;

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '商品名称',
  `description` text COMMENT '商品描述',
  `picture` varchar(512) COMMENT '商品图片URL',
  `price` decimal(10,2) NOT NULL COMMENT '商品价格',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '商品状态(0-下架, 1-上架)',
  `stock` int NOT NULL DEFAULT 0 COMMENT '库存数量',
  `sales` bigint NOT NULL DEFAULT 0 COMMENT '销量',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_user` bigint NOT NULL COMMENT '更新人',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除(0-否, 1-是)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`) COMMENT '商品名称唯一索引',
  INDEX `idx_price` (`price`) COMMENT '价格索引',
  INDEX `idx_create_time_deleted` (`create_time`, `is_deleted`) COMMENT '创建时间和删除状态复合索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name` varchar(50) NOT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`) COMMENT '分类名称唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类表';

DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_id` bigint NOT NULL COMMENT '商品ID',
  `category_id` bigint NOT NULL COMMENT '分类ID',
  PRIMARY KEY (`id`),
  INDEX `idx_product_id` (`product_id`) COMMENT '商品ID索引',
  INDEX `idx_category_id` (`category_id`) COMMENT '分类ID索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品与分类关联表';
