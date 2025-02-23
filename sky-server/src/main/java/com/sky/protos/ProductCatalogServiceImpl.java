package com.sky.protos;

import com.sky.dto.DishPageQueryDTO;
import com.sky.mapper.ProductMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        DishPageQueryDTO dishPageQueryDTO = new DishPageQueryDTO();
        dishPageQueryDTO.setPage(request.getPage());
        dishPageQueryDTO.setPageSize((int) request.getPageSize());

        PageResult<DishVO> pageResult =
            dishService.pageQuery(dishPageQueryDTO);
        List<Product> products =
            ProductMapper.toProducts(pageResult.getRecords());

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
        DishVO dishVO = dishService.getByIdWithFlavor((long) request.getId());
        Product product = ProductMapper.toProduct(dishVO);
        return GetProductResp.newBuilder().setProduct(product).build();
    }
}
