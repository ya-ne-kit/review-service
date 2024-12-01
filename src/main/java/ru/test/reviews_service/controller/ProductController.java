package ru.test.reviews_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import ru.test.reviews_service.dto.NewProductDto;
import ru.test.reviews_service.dto.UpdateProductDto;
import ru.test.reviews_service.entity.Product;
import ru.test.reviews_service.service.ProductService;

import java.util.List;

/**
 * Контроллер для управления товарами.
 * <p>
 * Контроллер предоставляет RESTful API для выполнения основных операций с товарами.
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    /**
     * Возвращает список всех товаров с поддержкой пагинации.
     *
     * @param pageable объект Pageable для настройки пагинации.
     * @param page     номер страницы (необязательный параметр).
     * @param size     размер страницы (необязательный параметр).
     * @return список товаров на текущей странице.
     */
    @GetMapping
    public List<Product> getAllProducts(@PageableDefault Pageable pageable,
                                        @RequestParam(required = false) Integer page,
                                        @RequestParam(required = false) Integer size) {
        if (page != null && size != null) {
            pageable = PageRequest.of(page, size);
        }
        return productService.getAllProducts(pageable);
    }

    /**
     * Возвращает товар по его идентификатору.
     *
     * @param id идентификатор продукта.
     * @return товар с указанным идентификатором.
     */
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    /**
     * Создает новый товар.
     *
     * @param product DTO для создания нового товара.
     * @return созданный товар.
     */
    @PostMapping
    public Product createProduct(@Valid @RequestBody NewProductDto product) {
        return productService.createProduct(product);
    }

    /**
     * Обновляет существующий товар.
     *
     * @param id      идентификатор товара, который нужно обновить.
     * @param product DTO для обновления товара.
     * @return обновленный продукт.
     */
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @Valid @RequestBody UpdateProductDto product) {
        return productService.updateProduct(id, product);
    }

    /**
     * Удаляет товар по его идентификатору.
     *
     * @param id идентификатор товара, который нужно удалить.
     */
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
