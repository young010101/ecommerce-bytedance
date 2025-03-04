package com.sky.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String picture;
    private BigDecimal price;

    private List<String> categories = new ArrayList<>();
}
