package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ListProductsReqDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int page;
    private long pageSize;
    private String categoryName;
}
