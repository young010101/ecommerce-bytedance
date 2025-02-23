package com.sky.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.sky.protos.Product;
import com.sky.vo.DishVO;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class ProductMapperTest {

  @Test
  void toProduct_ShouldReturnNull_WhenDishVOIsNull() {
    assertNull(ProductMapper.toProduct(null));
  }

  @Test
  void toProduct_ShouldMapCorrectly_WhenDishVOIsValid() {
    // Arrange
    DishVO dishVO = new DishVO();
    dishVO.setId(1L);
    dishVO.setName("测试菜品");
    dishVO.setCategoryName("测试分类");
    dishVO.setImage("test.jpg");
    dishVO.setPrice(new BigDecimal("88.88"));

    // Act
    Product product = ProductMapper.toProduct(dishVO);

    // Assert
    assertNotNull(product);
    assertEquals(1, product.getId());
    assertEquals("测试菜品", product.getName());
    assertEquals("测试分类", product.getDescription());
    assertEquals("test.jpg", product.getPicture());
    assertEquals(88.88f, product.getPrice(), 0.001);
  }

  @Test
  void toProducts_ShouldReturnNull_WhenDishVOListIsNull() {
    assertNull(ProductMapper.toProducts(null));
  }

  @Test
  void toProducts_ShouldMapCorrectly_WhenDishVOListIsValid() {
    // Arrange
    DishVO dishVO = new DishVO();
    dishVO.setId(1L);
    dishVO.setName("测试菜品");
    dishVO.setCategoryName("测试分类");
    dishVO.setImage("test.jpg");
    dishVO.setPrice(new BigDecimal("88.88"));

    List<DishVO> dishVOs = List.of(dishVO);

    // Act
    List<Product> products = ProductMapper.toProducts(dishVOs);

    // Assert
    assertNotNull(products);
    assertEquals(1, products.size());

    Product product = products.get(0);
    assertEquals(1, product.getId());
    assertEquals("测试菜品", product.getName());
    assertEquals("测试分类", product.getDescription());
    assertEquals("test.jpg", product.getPicture());
    assertEquals(88.88f, product.getPrice(), 0.001);
  }
}
