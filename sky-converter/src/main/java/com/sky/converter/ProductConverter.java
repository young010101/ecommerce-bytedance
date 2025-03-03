package com.sky.converter;

import com.sky.dto.ListProductsReqDTO;
import com.sky.dto.ProductDTO;
import com.sky.entity.Product;
import com.sky.protos.ListProductsReq;
import com.sky.protos.ListProductsResp;
import com.sky.vo.ListProductsRespVO;
import org.mapstruct.Mapper;

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
