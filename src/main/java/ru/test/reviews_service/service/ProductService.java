package ru.test.reviews_service.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.test.reviews_service.dto.NewProductDto;
import ru.test.reviews_service.dto.UpdateProductDto;
import ru.test.reviews_service.entity.Product;
import ru.test.reviews_service.repository.ProductRepository;
import ru.test.reviews_service.util.ModelMapper;

import java.util.List;

/**
 * Сервисный класс для управления товарами.
 * Класс предоставляет методы для выполнения основных операций с товарами.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    /**
     * Возвращает список всех товаров с поддержкой пагинации.
     *
     * @param pageable объект, содержащий информацию о пагинации и сортировке.
     * @return список товаров в соответствии с указанными параметрами пагинации.
     */
    public List<Product> getAllProducts(Pageable pageable) {
        log.info("INFO: find all entities request T:Product {{}}", pageable.toString());
        return productRepository.findAll(pageable).getContent();
    }

    /**
     * Возвращает товар по его идентификатору.
     *
     * @param id идентификатор продукта.
     * @return товар с указанным идентификатором.
     * @throws EntityNotFoundException если товар с указанным идентификатором не найден.
     */
    public Product getProductById(Long id) {
        log.info("INFO: find entity request T:Product ID:{{}}", id);
        return productRepository.findById(id).orElseGet(() -> {
            log.warn("EXC: entity not found T:Product ID:{{}}", id);
            throw new EntityNotFoundException(String.format("Товар с id = {%d} не существует", id));
        });
    }

    /**
     * Создает новый товар на основе переданного DTO.
     *
     * @param product DTO, содержащий информацию о новом товаре.
     * @return созданный товар.
     */
    public Product createProduct(NewProductDto product) {
        log.info("INFO: new entity request {{}}", product.toString());
        return productRepository.save(modelMapper.toProduct(product));
    }

    /**
     * Обновляет существующий товар.
     *
     * @param id  идентификатор товара, который необходимо обновить.
     * @param dto DTO, содержащий новые данные для обновления товара.
     * @return обновленный товар.
     * @throws EntityNotFoundException если товар с указанным идентификатором не найден.
     */
    public Product updateProduct(Long id, UpdateProductDto dto) {
        log.info("INFO: update entity request T:Product ID:{{}} Data:{{}}", id, dto.toString());
        Product product = getProductById(id);
        modelMapper.updateProduct(dto, product);
        return productRepository.save(product);
    }

    /**
     * Удаляет товар по его идентификатору.
     *
     * @param id идентификатор товара, который необходимо удалить.
     */
    public void deleteProduct(Long id) {
        log.info("INFO: remove entity request T:Product ID:{{}}", id);
        productRepository.deleteById(id);
    }
}
