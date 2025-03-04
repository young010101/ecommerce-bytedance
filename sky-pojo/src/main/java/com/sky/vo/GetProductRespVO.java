package com.sky.vo;

import com.sky.dto.ProductDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GetProductRespVO implements Serializable {
    List<ProductDTO> products;
}
