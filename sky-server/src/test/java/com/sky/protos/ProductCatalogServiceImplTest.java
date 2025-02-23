package com.sky.protos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.sky.dto.DishPageQueryDTO;
import com.sky.mapper.ProductMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ProductCatalogServiceImplTest {

  @Mock private DishService dishService;
  @Mock ProductMapper productMapper;

  @InjectMocks private ProductCatalogServiceImpl productCatalogService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getProduct_ShouldReturnCorrectProduct() {
    // Arrange
    int productId = 1;
    DishVO mockDishVO = new DishVO();
    mockDishVO.setId(1L);
    mockDishVO.setName("测试菜品");
    mockDishVO.setCategoryName("测试分类");
    mockDishVO.setImage("test-image.jpg");
    mockDishVO.setPrice(new BigDecimal("88.00"));

    when(dishService.getByIdWithFlavor(1L)).thenReturn(mockDishVO);

    GetProductReq request = GetProductReq.newBuilder().setId(productId).build();

    // Act
    GetProductResp response = productCatalogService.getProduct(request);

    // Assert
    assertNotNull(response);
    assertNotNull(response.getProduct());

    Product product = response.getProduct();
    assertEquals(productId, product.getId());
    assertEquals(mockDishVO.getName(), product.getName());
    assertEquals(mockDishVO.getCategoryName(), product.getDescription());
    assertEquals(mockDishVO.getImage(), product.getPicture());
    assertEquals(mockDishVO.getPrice().intValue(), product.getPrice());

    verify(dishService, times(1)).getByIdWithFlavor(1L);
  }

  @Test
  void listProducts_ShouldReturnProductList_WhenGivenValidPageRequest() {
    // Arrange
    ListProductsReq request = ListProductsReq.newBuilder().setPage(1).setPageSize(10).build();

    DishVO dishVO = new DishVO(); // Add necessary fields
    List<DishVO> dishVOList = List.of(dishVO);
    PageResult<DishVO> pageResult = new PageResult<>();
    pageResult.setRecords(dishVOList);

    Product mockProduct = Product.newBuilder().build(); // Add necessary fields
    List<Product> productList = List.of(mockProduct);

    when(dishService.pageQuery(any(DishPageQueryDTO.class))).thenReturn(pageResult);

    when(productMapper.toProducts(dishVOList)).thenReturn(productList);

    // Act
    ListProductsResp response = productCatalogService.listProducts(request);

    // Assert
    assertNotNull(response);
    assertEquals(1, response.getProductsCount());
    assertEquals(mockProduct, response.getProducts(0));
  }

  @Test
  void getProduct_ShouldReturnProduct_WhenGivenValidId() {
    // ... existing code ...
  }
}
