package com.example.productinventorymgmt.products.controllers;

import com.example.productinventorymgmt.base.Failure;
import com.example.productinventorymgmt.base.NetworkResponse;
import com.example.productinventorymgmt.base.Success;
import com.example.productinventorymgmt.products.models.other.ProductServiceException;
import com.example.productinventorymgmt.products.models.requests.CreateProductRequest;
import com.example.productinventorymgmt.products.models.requests.UpdateProductRequest;
import com.example.productinventorymgmt.products.models.responses.ProductItemResponse;
import com.example.productinventorymgmt.products.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * This class represents the REST controller for managing product-related operations.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    Logger LOGGER = Logger.getLogger(ProductController.class.getName());

    private final ProductService productService;

    /**
     * Constructs a new ProductController instance with the specified ProductService.
     *
     * @param productService The ProductService instance to be used for product operations.
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Handles HTTP PUT requests to create a new product.
     *
     * @param request The request body containing product creation information.
     * @return ResponseEntity containing the network response.
     */
    @ApiOperation("Create a new product")
    @PutMapping("/create")// supplierId should be taken from authToken when supplierLogin other wise need to make api internal
    public ResponseEntity<NetworkResponse<String>> create(@RequestBody CreateProductRequest request) {
        try {
            productService.createProduct(request);
            return new Success<String>("Product created Successfully").toResponse();
        } catch (ProductServiceException e) {
            return new Failure<String>(e).toResponse();
        }
    }

    /**
     * Handles HTTP POST requests to update an existing product.
     *
     * @param request The request body containing updated product information.
     * @return ResponseEntity containing the network response.
     */
    @ApiOperation("Update product")
    @PostMapping("/update")// product Id can be received from initial get products, validate with authenticated supplierId(from authToken) before updating
    public ResponseEntity<NetworkResponse<String>> update(@RequestBody UpdateProductRequest request) {
        try {
            productService.updateProduct(request);
            return new Success<String>("Product updated Successfully").toResponse();
        } catch (ProductServiceException e) {
            return new Failure<String>(e).toResponse();
        }
    }

    /**
     * Handles HTTP DELETE requests to delete a product.
     *
     * @param id The ID of the product to delete.
     * @return ResponseEntity containing the network response.
     */
    @ApiOperation("Delete product")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<NetworkResponse<String>> delete(@PathVariable("id")  int id) {
        try {
            productService.deleteProduct(id);
            return new Success<String>("Product deleted Successfully").toResponse();
        } catch (ProductServiceException e) {
            return new Failure<String>(e).toResponse();
        }
    }

    /**
     * Handles HTTP GET requests to retrieve a list of product items based on optional filters.
     *
     * @param supplierId The supplier ID to filter products.
     * @param minPrice   The minimum price to filter products.
     * @param maxPrice   The maximum price to filter products.
     * @return ResponseEntity containing the network response.
     */
    @ApiOperation("Get items")
    @GetMapping("/items")
    public ResponseEntity<NetworkResponse<List<ProductItemResponse>>> getProductItems(
            @RequestParam(required = false) Integer supplierId,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice
    ) {
        try {
            List<ProductItemResponse> productItems = productService.getProductItems(supplierId, minPrice, maxPrice);
            return new Success<List<ProductItemResponse>>(productItems).toResponse();
        } catch (ProductServiceException e) {
            return new Failure<List<ProductItemResponse>>(e).toResponse();
        }
    }

    /**
     * Handles HTTP GET requests to retrieve a product item by its ID.
     *
     * @param id The ID of the product item to retrieve.
     * @return ResponseEntity containing the network response.
     */
    @ApiOperation("Get Product Details")
    @GetMapping("/item/{id}")
    public ResponseEntity<NetworkResponse<ProductItemResponse>> getProductItemById(@PathVariable("id") int id) {
        try {
            ProductItemResponse productItem = productService.getProductItemById(id);
            return new Success<ProductItemResponse>(productItem).toResponse();
        } catch (ProductServiceException e) {
            return new Failure<ProductItemResponse>(e).toResponse();
        }
    }

    /**
     * Handles HTTP PUT requests to increment or decrement the stock quantity of a product item.
     *
     * @param productId The ID of the product item.
     * @param delta     The value by which to increment or decrement the stock quantity.
     * @return ResponseEntity containing the network response.
     */
    @ApiOperation("Update  stock of a product")
    @PutMapping("/stock/{productId}/{delta}")
    public ResponseEntity<NetworkResponse<String>> incrementOrDecrementStock(
            @PathVariable("productId") int productId,
            @PathVariable("delta") int delta
    ) {
        try {
            productService.incrementOrDecrementStock(productId, delta);
            return new Success<String>("Stock updated Successfully").toResponse();
        } catch (ProductServiceException e) {
            return new Failure<String>(e).toResponse();
        }
    }
}
