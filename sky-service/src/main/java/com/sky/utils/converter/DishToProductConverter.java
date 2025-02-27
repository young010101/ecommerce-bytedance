package com.sky.utils.converter;

import com.sky.protos.Product;
import com.sky.vo.DishVO;
import java.util.List;
import java.util.stream.Collectors;

public final class DishToProductConverter {
    private DishToProductConverter() { }

    /**
     * Convert a DishVO object to a Product object.
     *
     * @param dishVO The DishVO object to convert.
     * @return The converted Product object.
     */
    public static Product toProduct(final DishVO dishVO) {
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

    /**
     * Convert a list of DishVO objects to a list of Product objects.
     *
     * @param dishVOs The list of DishVO objects to convert.
     * @return The converted list of Product objects.
     */
    public static List<Product> toProducts(final List<DishVO> dishVOs) {
        if (dishVOs == null) {
            return null;
        }

        return dishVOs.stream()
                .map(DishToProductConverter::toProduct)
                .collect(Collectors.toList());
    }
}
