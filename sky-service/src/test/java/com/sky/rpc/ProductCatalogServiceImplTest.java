package com.sky.rpc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.sky.entity.Category;
import com.sky.entity.Product;
import com.sky.mapper.ProductMapper;
import com.sky.protos.GetProductReq;
import com.sky.protos.GetProductResp;
import com.sky.service.DishService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ProductCatalogServiceImplTest {

  @Mock private DishService dishService;
  @Mock private ProductMapper productMapper;

  @InjectMocks private ProductCatalogServiceImpl productCatalogService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void listProducts_ShouldReturnProductList_WhenGivenValidPageRequest() {
    //    // Arrange
    //    ListProductsReq request = ListProductsReq.newBuilder().setPage(1).setPageSize(10).build();
    //
    //    DishVO dishVO = new DishVO(); // Add necessary fields
    //    dishVO.setId(1L);
    //    dishVO.setName("测试菜品");
    //    dishVO.setCategoryName("测试分类");
    //    dishVO.setImage("test-image.jpg");
    //    dishVO.setPrice(new BigDecimal("88.00"));
    //    List<DishVO> dishVOList = List.of(dishVO);
    //    PageResult<DishVO> pageResult = new PageResult<>();
    //    pageResult.setRecords(dishVOList);
    //
    //    Product mockProduct = new Product(); // Add necessary fields
    //    Product expectedProduct = DishToProductConverter.toProduct(dishVO);
    //
    //    when(dishService.pageQuery(any(DishPageQueryDTO.class))).thenReturn(pageResult);
    //
    //    // Act
    //    ListProductsResp response = productCatalogService.listProducts(request);
    //
    //    // Assert
    //    assertNotNull(response);
    //    assertEquals(1, response.getProductsCount());
    //    assertEquals(expectedProduct, response.getProducts(0));
  }

  @Test
  void getProduct_ShouldReturnCorrectProduct() {
    // Arrange
    int productId = 1;

    Product mockProduct = new Product();
    mockProduct.setId(1L);
    mockProduct.setName("测试菜品");
    mockProduct.setDescription("测试描述");
    mockProduct.setPicture("test-image.jpg");
    mockProduct.setPrice(new BigDecimal("88.00"));
    mockProduct.setStatus((byte) 1);
    mockProduct.setStock(100);
    mockProduct.setCategoriesId(1L);

    Category category = new Category();
    category.setId(1L);
    category.setType(1);
    category.setName("测试分类");
    category.setSort(1);
    category.setStatus(1);
    category.setCreateTime(LocalDateTime.now());
    category.setUpdateTime(LocalDateTime.now());
    category.setCreateUser(1L);
    category.setUpdateUser(1L);

    when(productMapper.getById(1)).thenReturn(mockProduct);
    when(productMapper.getCategoryListByCategoryId(1L)).thenReturn(List.of(category));

    GetProductReq request = GetProductReq.newBuilder().setId(productId).build();

    // Act
    GetProductResp response = productCatalogService.getProduct(request);

    // Assert
    assertNotNull(response);
    assertNotNull(response.getProduct());

    assertEquals(productId, response.getProduct().getId());
    assertEquals(mockProduct.getName(), response.getProduct().getName());
    assertEquals(mockProduct.getDescription(), response.getProduct().getDescription());
    assertEquals(mockProduct.getPicture(), response.getProduct().getPicture());
    assertEquals(mockProduct.getPrice().intValue(), response.getProduct().getPrice());

    verify(productMapper, times(1)).getById(1);
    verify(productMapper, times(1)).getCategoryListByCategoryId(1L);
  }
}
