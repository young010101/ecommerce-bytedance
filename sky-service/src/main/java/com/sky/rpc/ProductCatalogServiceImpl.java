package com.sky.rpc;

import com.sky.protos.DubboProductCatalogServiceTriple;
import com.sky.protos.ListProductsReq;
import com.sky.protos.ListProductsResp;
import com.sky.protos.Product;
import com.sky.protos.GetProductReq;
import com.sky.protos.GetProductResp;
import com.sky.utils.converter.DishToProductConverter;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RPC implementation of the Product Catalog Service.
 * Provides product listing and details functionality.
 */
@Slf4j
@Service
@DubboService
public class ProductCatalogServiceImpl
        extends DubboProductCatalogServiceTriple.ProductCatalogServiceImplBase {
    /**
     * Dish service instance.
     */
    @Autowired
    private DishService dishService;

    /**
     * List products.
     *
     * @param request list products request
     * @return list products response
     */
    @Override
    public ListProductsResp listProducts(final ListProductsReq request) {
        log.info("Listing products with request: {}", request);
        DishPageQueryDTO dishPageQueryDTO = new DishPageQueryDTO();
        dishPageQueryDTO.setPage(request.getPage());
        dishPageQueryDTO.setPageSize((int) request.getPageSize());

        PageResult<DishVO> pageResult =
                dishService.pageQuery(dishPageQueryDTO);
        List<Product> products =
                DishToProductConverter.toProducts(pageResult.getRecords());

        return ListProductsResp.newBuilder()
                .addAllProducts(products)
                .build();
    }

    /**
     * Get product by id.
     *
     * @param request get product request
     * @return get product response
     */
    @Override
    public GetProductResp getProduct(final GetProductReq request) {
        log.info("Getting product with id: {}", request.getId());
        DishVO dishVO = dishService.getByIdWithFlavor((long) request.getId());
        Product product = DishToProductConverter.toProduct(dishVO);
        return GetProductResp.newBuilder().setProduct(product).build();
    }
}
