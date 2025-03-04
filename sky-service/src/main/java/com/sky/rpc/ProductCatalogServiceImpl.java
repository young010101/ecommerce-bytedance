package com.sky.rpc;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.dto.ListProductsReqDTO;
import com.sky.dto.ProductDTO;
import com.sky.entity.Category;
import com.sky.mapper.ProductMapper;
import com.sky.protos.DubboProductCatalogServiceTriple;
import com.sky.protos.ListProductsReq;
import com.sky.protos.ListProductsResp;
import com.sky.protos.Product;
import com.sky.protos.GetProductReq;
import com.sky.protos.GetProductResp;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
     * Product mapper instance.
     */
    @Autowired
    private ProductMapper productMapper;

    /**
     * List products.
     *
     * @param request list products request
     * @return list products response
     */
    @Override
    public ListProductsResp listProducts(final ListProductsReq request) {
        log.info("Listing products with request: {}", request);
        ListProductsReqDTO listProductsReqDTO = new ListProductsReqDTO();
        BeanUtils.copyProperties(request, listProductsReqDTO);

        PageHelper.startPage(request.getPage(), (int) request.getPageSize());
        Page<ProductDTO> getProductRespVOS =
                productMapper.pageQuery(listProductsReqDTO);

        PageInfo<ProductDTO> pageInfo = new PageInfo<>(getProductRespVOS);

        PageResult<ProductDTO> pageResult =
                new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
        List<ProductDTO> products = pageResult.getRecords();

        return ListProductsResp.newBuilder()
                .addAllProducts(convertToProducts(products))
                .build();
    }

    /**
     * Convert productDTO to product.
     *
     * @param dtoList productDTO list
     * @return product list
     */
    private List<Product> convertToProducts(final List<ProductDTO> dtoList) {
        return dtoList.stream()
                .map(this::convertToProduct)
                .collect(Collectors.toList());
    }

    /**
     * Convert productDTO to product.
     *
     * @param dto productDTO
     * @return product
     */
    private Product convertToProduct(final ProductDTO dto) {
        return Product.newBuilder()
                .setId(dto.getId().intValue())
                .setName(dto.getName())
                .setDescription(dto.getDescription())
                .setPicture(dto.getPicture())
                .setPrice(dto.getPrice().floatValue())
                .addAllCategories(dto.getCategories()
                        != null ? dto.getCategories() : Collections.emptyList())
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
        log.info("Dubbo rpc Getting product with id: {}", request.getId());
        com.sky.entity.Product product = productMapper.getById(request.getId());
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        List<Category> categoryList = productMapper
                .getCategoryListByCategoryId(product.getCategoriesId());
        productDTO.setCategories(categoryList
                .stream()
                .map(Category::getName)
                .collect(Collectors.toList())
        );

        Product productProto = Product.newBuilder()
                .setId(productDTO.getId().intValue())
                .setName(productDTO.getName())
                .setDescription(productDTO.getDescription())
                .setPicture(productDTO.getPicture())
                .setPrice(productDTO.getPrice().floatValue())
                .addAllCategories(productDTO.getCategories() != null
                        ? productDTO.getCategories() : Collections.emptyList())
                .build();
        return GetProductResp.newBuilder().setProduct(productProto).build();
    }
}
