package ru.test.reviews_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import ru.test.reviews_service.controller.ProductController;
import ru.test.reviews_service.dto.NewProductDto;
import ru.test.reviews_service.dto.UpdateProductDto;
import ru.test.reviews_service.entity.Product;
import ru.test.reviews_service.service.ProductService;
import ru.test.reviews_service.util.Category;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setCustomArgumentResolvers(pageableResolver())
                .build();
    }

    private HandlerMethodArgumentResolver pageableResolver() {
        return new PageableHandlerMethodArgumentResolver();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> products = Arrays.asList(
                createProduct(1L, "Ноутбук", "Asus", Category.ELECTRONICS),
                createProduct(2L, "Несквик", "Нестле", Category.FOOD)
        );

        when(productService.getAllProducts(any())).thenReturn(products);

        mockMvc.perform(get("/api/products")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetProductById() throws Exception {
        Product product = createProduct(1L, "Ноутбук", "Asus", Category.ELECTRONICS);

        when(productService.getProductById(1L)).thenReturn(product);

        mockMvc.perform(get("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ноутбук"));
    }

    @Test
    public void testCreateProduct() throws Exception {
        NewProductDto newProductDto = new NewProductDto();
        newProductDto.setName("Ноутбук");
        newProductDto.setCategory("ELECTRONICS");
        newProductDto.setManufacturer("Asus");

        Product createdProduct = createProduct(1L, "Ноутбук", "Asus", Category.ELECTRONICS);

        when(productService.createProduct(any(NewProductDto.class))).thenReturn(createdProduct);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newProductDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ноутбук"));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        UpdateProductDto updateProductDto = new UpdateProductDto();
        updateProductDto.setName("Ноутбук");
        updateProductDto.setManufacturer("Samsung");

        Product updatedProduct = createProduct(1L, "Ноутбук", "Samsung", Category.ELECTRONICS);

        when(productService.updateProduct(anyLong(), any(UpdateProductDto.class))).thenReturn(updatedProduct);

        mockMvc.perform(put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updateProductDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.manufacturer").value("Samsung"));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isOk());
    }

    private Product createProduct(Long id, String name, String brand, Category category) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setManufacturer(brand);
        product.setCategory(category);
        return product;
    }
}
