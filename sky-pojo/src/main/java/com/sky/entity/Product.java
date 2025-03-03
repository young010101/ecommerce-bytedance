package com.sky.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * <p>
 * 商品表
 * </p>
 *
 * @author Cheng Yang
 * @since 2025-03-03
 */
@Getter
@Setter
@ToString
@Schema(name = "Product", description = "商品表")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    private String name;

    /**
     * 商品描述
     */
    @Schema(description = "商品描述")
    private String description;

    /**
     * 商品图片URL
     */
    @Schema(description = "商品图片URL")
    private String picture;

    /**
     * 商品价格
     */
    @Schema(description = "商品价格")
    private BigDecimal price;

    /**
     * 商品状态(0-下架, 1-上架)
     */
    @Schema(description = "商品状态(0-下架, 1-上架)")
    private Byte status;

    /**
     * 库存数量
     */
    @Schema(description = "库存数量")
    private Integer stock;

    /**
     * 销量
     */
    @Schema(description = "销量")
    private Long sales;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private Long createUser;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    @Schema(description = "更新人")
    private Long updateUser;

    /**
     * 是否删除(0-否, 1-是)
     */
    @Schema(description = "是否删除(0-否, 1-是)")
    private Byte isDeleted;
}
