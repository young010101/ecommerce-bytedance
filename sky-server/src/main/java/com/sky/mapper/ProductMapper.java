package com.sky.mapper;

import com.sky.protos.Product;
import com.sky.vo.DishVO;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    private ProductMapper() {}

    public static Product toProduct(DishVO dishVO) {
        if (dishVO == null) {
            return null;
        }

        return Product.newBuilder()
                .setId(dishVO.getId().intValue())
                .setName(dishVO.getName())
                .setDescription(dishVO.getCategoryName())
                .setPicture(dishVO.getImage())
                .setPrice(dishVO.getPrice().floatValue())
                .build();
    }

    public static List<Product> toProducts(List<DishVO> dishVOs) {
        if (dishVOs == null) {
            return null;
        }

        return dishVOs.stream()
                .map(ProductMapper::toProduct)
                .collect(Collectors.toList());
    }
}
