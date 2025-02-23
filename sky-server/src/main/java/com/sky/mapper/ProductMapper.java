package com.sky.mapper;

import com.sky.protos.Product;
import com.sky.vo.DishVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public Product toProduct(DishVO dishVO) {
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

    public List<Product> toProducts(List<DishVO> dishVOs) {
        if (dishVOs == null) {
            return null;
        }

        return dishVOs.stream()
                .map(this::toProduct)
                .collect(Collectors.toList());
    }
}
