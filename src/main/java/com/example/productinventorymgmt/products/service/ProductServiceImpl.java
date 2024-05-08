package com.example.productinventorymgmt.products.service;

import com.example.productinventorymgmt.products.models.db.ProductItemDao;
import com.example.productinventorymgmt.products.models.other.ProductServiceException;
import com.example.productinventorymgmt.products.models.requests.CreateProductRequest;
import com.example.productinventorymgmt.products.models.requests.UpdateProductRequest;
import com.example.productinventorymgmt.products.models.responses.ProductItemResponse;
import com.example.productinventorymgmt.products.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implements the ProductService interface and provides methods for managing products.
 * It interacts with the database using Spring Data JPA and JDBC.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final String SELECT_FROM_PRODUCT_ITEMS = "SELECT * FROM product_items"; //can be moved to QueryUtils
    public static final String UPDATE_STOCK_PRODUCT_ITEMS = "UPDATE product_items SET stock_qty = stock_qty + :delta WHERE id = :id";

    public static final String UPDATE_PRODUCT_ITEMS = "UPDATE product_items SET name = :name,supplier_id = :supplierId, price = :price, stock_qty = :stockQty WHERE id = :id";

    public static final String DELETE_FROM_PRODUCT_ITEM = "DELETE FROM product_items WHERE id = :id";

    private static final String ITEM_NOT_FOUND = "Item Not Found";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Creates a new product based on the provided request.
     *
     * @param request The request containing product information.
     * @throws ProductServiceException If an error occurs while creating the product.
     */
    @Override
    public void createProduct(CreateProductRequest request) throws ProductServiceException {
        try {
            ProductItemDao dao = new ProductItemDao();
            dao.setName(request.getName());
            dao.setPrice(request.getPrice());
            dao.setSupplierId(request.getSupplierId());
            dao.setStockQty(request.getStockQty());
            productRepository.save(dao);
        } catch (Exception e) {
           throw ProductServiceException.builder().message(e.getMessage()).build();
        }
    }

    /**
     * Updates an existing product based on the provided request.
     *
     * @param request The request containing updated product information.
     * @throws ProductServiceException If the product to update is not found or an error occurs during the update process.
     */
    @Override
    @Transactional
    public void updateProduct(UpdateProductRequest request) throws ProductServiceException {
        try {
            int updated = productRepository.updateProductItemById(request.getId(), request.getName(), request.getSupplierId(), request.getPrice(), request.getStockQty());
            if(updated == 0) {
                throw ProductServiceException.builder().message(ITEM_NOT_FOUND).build();
            }
        } catch (Exception e) {
            throw ProductServiceException.builder().message(e.getMessage()).build();
        }
    }

    /**
     * Deletes a product with the specified ID.
     *
     * @param id The ID of the product to delete.
     * @throws ProductServiceException If the product to delete is not found or an error occurs during the deletion process.
     */
    @Override
    public void deleteProduct(int id) throws ProductServiceException {
        try {
            int updated = productRepository.deleteProductItemById(id);
            if(updated == 0) {
                throw ProductServiceException.builder().message(ITEM_NOT_FOUND).build();
            }
        } catch (Exception e) {
            throw ProductServiceException.builder().message(e.getMessage()).build();
        }
    }

    /**
     * Retrieves a list of product items based on optional filters such as supplier ID, minimum price, and maximum price.
     *
     * @param supplierId The supplier ID to filter products.
     * @param minPrice   The minimum price to filter products.
     * @param maxPrice   The maximum price to filter products.
     * @return A list of product item responses.
     * @throws ProductServiceException If an error occurs while retrieving the product items.
     */
    @Override
    public List<ProductItemResponse> getProductItems(Integer supplierId, Integer minPrice, Integer maxPrice) throws ProductServiceException {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(SELECT_FROM_PRODUCT_ITEMS);
        if(supplierId!= null || minPrice!=null || maxPrice!=null) {
            queryBuilder.append(" WHERE ");
        }
        boolean first = true;
        if(supplierId!= null) {
            queryBuilder.append(String.format(" supplier_id = %d", supplierId));
            first = false;
        }
        if(minPrice!= null) {
            if(!first) {
                queryBuilder.append(" AND ");
            }
            queryBuilder.append(String.format(" price >= %d ", minPrice));
            first = false;
        }
        if(maxPrice!= null) {
            if(!first) {
                queryBuilder.append(" AND ");
            }
            queryBuilder.append(String.format(" price <= %d ", maxPrice));
        }
        String query = queryBuilder.toString();
        try {
            return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(ProductItemDao.class)) // using dynamic because many filter can come later
                    .stream().map(it ->
                            ProductItemResponse.builder()
                                    .id(it.getId())
                                    .price(it.getPrice())
                                    .name(it.getName())
                                    .stockQty(it.getStockQty())
                                    .supplierId(it.getSupplierId())
                                    .build()
                    ).collect(Collectors.toList());
        } catch (Exception e) {
            throw ProductServiceException.builder().message(e.getMessage()).build();
        }
    }

    /**
     * Retrieves a product item by its ID.
     *
     * @param id The ID of the product item to retrieve.
     * @return The product item response.
     * @throws ProductServiceException If the product item is not found or an error occurs during retrieval.
     */
    @Override
    public ProductItemResponse getProductItemById(int id) throws ProductServiceException {
        try {
            ProductItemDao dao = productRepository.getReferenceById(id);
            return ProductItemResponse.builder()
                    .id(dao.getId())
                    .price(dao.getPrice())
                    .name(dao.getName())
                    .stockQty(dao.getStockQty())
                    .supplierId(dao.getSupplierId())
                    .build();
        } catch (Exception e) {
            throw ProductServiceException.builder().message(e.getMessage()).build();
        }
    }

    /**
     * Increments or decrements the stock quantity of a product item by the specified delta value.
     *
     * @param productId The ID of the product item.
     * @param delta     The value by which to increment or decrement the stock quantity.
     * @throws ProductServiceException If the product item is not found or an error occurs during the operation.
     */
    @Override
    public void incrementOrDecrementStock(int productId, int delta) throws ProductServiceException {
        try {
            int updated = productRepository.updateStockByDelta(productId, delta);
            if(updated == 0) {
                throw ProductServiceException.builder().message(ITEM_NOT_FOUND).build();
            }
        } catch (Exception e) {
            throw ProductServiceException.builder().message(e.getMessage()).build();
        }
    }
}
