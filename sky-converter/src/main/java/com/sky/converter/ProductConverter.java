package com.sky.converter;

import com.sky.dto.ListProductsReqDTO;
import com.sky.dto.ProductDTO;
import com.sky.entity.Product;
import com.sky.protos.ListProductsReq;
import com.sky.protos.ListProductsResp;
import com.sky.vo.ListProductsRespVO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Mapper
public interface ProductMapper {

    /**
     * 将 Product 转换为 ProductDTO.
     *
     * @param product 商品实体
     * @return 商品DTO
     */
    ProductDTO toProductDTO(Product product);

    /**
     * 将 ListProductsReq 转换为 ListProductsReqDTO.
     *
     * @param request 请求参数
     * @return 请求DTO
     */
    ListProductsReqDTO toListProductsReqDTO(ListProductsReq request);

    /**
     * 将 ListProductsResp 转换为 ListProductsRespVO.
     *
     * @param resp 响应参数
     * @return 响应VO
     */
    ListProductsRespVO toProductRespVO(ListProductsResp resp);
}

@Component
public class ProductConverter {
    /**
     * 将 Product 转换为 ProductProto.
     *
     * @param entity 商品实体
     * @return 商品Proto
     */
    public ProductProto toProto(Product entity) {
        if (entity == null) {
            return null;
        }
        return ProductProto.newBuilder()
                .setId(entity.getId())
                .setName(entity.getName())
                .setPrice(entity.getPrice().doubleValue())
                .setStock(entity.getStock())
                .build();
    }

    /**
     * 将 ProductProto 转换为 Product.
     *
     * @param proto 商品Proto
     * @return 商品实体
     */
    public Product toEntity(ProductProto proto) {
        if (proto == null) {
            return null;
        }
        Product entity = new Product();
        entity.setId(proto.getId());
        entity.setName(proto.getName());
        entity.setPrice(BigDecimal.valueOf(proto.getPrice()));
        entity.setStock(proto.getStock());
        return entity;
    }
}
