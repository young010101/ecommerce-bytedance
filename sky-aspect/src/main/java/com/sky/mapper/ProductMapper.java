package com.sky.mapper;

import com.sky.entity.Category;
import com.sky.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {
    /**
     * 根据id查询商品.
     *
     * @param id 商品id
     * @return 商品
     */
    @Select("SELECT * FROM product WHERE id = #{id}")
    Product getById(int id);

    /**
     * 根据分类id查询分类列表.
     *
     * @param categoryId 分类id
     * @return 分类列表
     */
    @Select("SELECT * FROM category WHERE id = #{categoryId}")
    List<Category> getCategoryListByCategoryId(long categoryId);
}
