package com.sky.controller.client;

import com.sky.dto.ProductDTO;
import com.sky.protos.GetProductReq;
import com.sky.protos.GetProductResp;
import com.sky.protos.ProductCatalogService;
import com.sky.result.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping("/client/product")
public class ProductController {

    @Autowired
    private ProductCatalogService productService;

    @GetMapping("/list")
    public Result<ProductDTO> listProducts(@RequestParam("id") int id) {
        GetProductResp resp = productService.getProduct(GetProductReq.newBuilder().setId(id).build());
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(resp.getProduct(), productDTO);
        productDTO.setId((long) resp.getProduct().getId());
        productDTO.setPrice(BigDecimal.valueOf(resp.getProduct().getPrice())
                .setScale(2, RoundingMode.HALF_UP));
        return Result.success(productDTO);
    }
}
