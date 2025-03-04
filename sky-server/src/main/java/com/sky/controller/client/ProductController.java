package com.sky.controller.client;

import com.sky.dto.ProductDTO;
import com.sky.protos.*;
import com.sky.result.PageResult;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/client/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductCatalogService productService;

    /**
     * Get product by id.
     * @param id product id
     * @return product
     */
    @GetMapping("/{id}")
    public Result<ProductDTO> getProduct(@PathVariable("id") int id) {
        GetProductResp resp = productService.getProduct(GetProductReq.newBuilder().setId(id).build());
        return Result.success(convertToProductDTO(resp.getProduct()));
    }

    /**
     * Search products by page.
     * @param page page number
     * @param pageSize page size
     * @param categoryName category name
     * @return products
     */
    @GetMapping("/search")
    public Result<PageResult<ProductDTO>> search(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String categoryName) {

        log.info("search products - page: {}, pageSize: {}, categoryName: {}",
                page, pageSize, categoryName);

        ListProductsReq listProductsReq = ListProductsReq.newBuilder()
                .setPage(page)
                .setPageSize(pageSize)
                .setCategoryName(categoryName != null ? categoryName : "")
                .build();

        ListProductsResp listProductsResp = productService.listProducts(listProductsReq);
        PageResult<ProductDTO> pageResult = new PageResult<>();
        pageResult.setTotal(listProductsResp.getProductsCount());
        pageResult.setRecords(listProductsResp.getProductsList().stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList()));
        return Result.success(pageResult);
    }

    /**
     * Convert product to productDTO.
     * @param product product
     * @return productDTO
     */
    private ProductDTO convertToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        productDTO.setId((long) product.getId());
        productDTO.setPrice(BigDecimal.valueOf(product.getPrice())
                .setScale(2, RoundingMode.HALF_UP));
        return productDTO;
    }
}
