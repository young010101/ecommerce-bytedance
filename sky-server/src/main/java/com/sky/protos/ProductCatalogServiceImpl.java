package com.sky.protos;

import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCatalogServiceImpl
        extends DubboProductCatalogServiceTriple.ProductCatalogServiceImplBase {
    /**
     * Dish service instance.
     */
    @Autowired
    private DishService dishService;

    /**
     * Get product by id.
     *
     * @param request get product request
     * @return get product response
     */
    @Override
    public GetProductResp getProduct(final GetProductReq request) {
        DishVO dishVO = dishService.getByIdWithFlavor((long) request.getId());
        Product product = Product.newBuilder()
                .setId(dishVO.getId().intValue())
                .setName(dishVO.getName())
                .setDescription(dishVO.getCategoryName())
                .setPicture(dishVO.getImage())
                .setPrice(dishVO.getPrice().intValue())
                .build();
        return GetProductResp.newBuilder()
                .setProduct(product)
                .build();
    }
}
