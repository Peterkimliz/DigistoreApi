package com.example.digishopapi.controllers;

import com.example.digishopapi.dtos.ProductDto;
import com.example.digishopapi.models.Product;
import com.example.digishopapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products/")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("create/{shopId}")
    public ResponseEntity<Product> createProduct(@RequestBody @Validated ProductDto productDto, @PathVariable("shopId") String shopId) {
        return new ResponseEntity<Product>(productService.createProduct(shopId, productDto), HttpStatus.CREATED);
    }

    @GetMapping("all")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String pageNumber) {
        List<Product> products = productService.getAllProducts(pageNumber);
        return new ResponseEntity<List<Product>>(products, products.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") String productId) {
        return new ResponseEntity<Product>(productService.getProductById(productId), HttpStatus.OK);
    }

    @GetMapping("shop/{shopId}/{pageNumber}")
    public ResponseEntity<List<Product>> getProductByShopId(@PathVariable("shopId") String shopId, @PathVariable("pageNumber") int pageNumber) {
        List<Product> products = productService.getProductsByShopId(shopId, pageNumber);
        return new ResponseEntity<List<Product>>(products, products.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PutMapping("{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable("productId") String productId) {
        productService.deleteProductById(productId);
        return new ResponseEntity<String>("product deleted successfully", HttpStatus.OK);
    }
}
