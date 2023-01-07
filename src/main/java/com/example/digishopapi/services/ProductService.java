package com.example.digishopapi.services;

import com.example.digishopapi.dtos.ProductDto;
import com.example.digishopapi.exceptions.NotFoundException;
import com.example.digishopapi.models.Category;
import com.example.digishopapi.models.Product;
import com.example.digishopapi.models.Shop;
import com.example.digishopapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ShopService shopService;
    @Autowired
    CategoryService categoryService;

    public Product createProduct(String shopId, ProductDto productDto) {
        Shop shop = shopService.getShopById(shopId);
        Category category = categoryService.findCategoryById(productDto.getCategory());
        if (shop == null) {
            throw new NotFoundException("shop with the id doesnot exist");
        } else if (category == null) {
            throw new NotFoundException("category with the id doesnot exist");
        } else {
            Product product = new Product();
            product.setCreatedAt(new Date(System.currentTimeMillis()));
            product.setUpdatedAt(new Date(System.currentTimeMillis()));
            product.setName(productDto.getName());
            product.setPrice(Integer.parseInt(productDto.getPrice()));
            product.setQuantity(Integer.parseInt(productDto.getQuantity()));
            product.setCategory(category);
            product.setDescription(productDto.getDescription());
            product.setShop(shop);
            product.setImages(productDto.getImages());
            return productRepository.save(product);
        }

    }

    public Product getProductById(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NotFoundException("product not found");
        } else {
            return product.get();
        }
    }

    public void deleteProductById(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NotFoundException("product not found");
        } else {
            Product foundProduct = product.get();
            foundProduct.setIsDeleted(true);
            productRepository.save(foundProduct);
        }
    }

    public List<Product> getAllProducts(String pageSize) {
      if (pageSize==null){
          System.out.println("called");
          return getProducts();
      }else {
          System.out.println("not called");
          return getPaginatedProducts(Integer.parseInt(pageSize));
      }

    }

    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        if (products.size() == 0) {
            return new ArrayList<>();
        } else {
            return products;
        }
    }

    public List<Product> getPaginatedProducts(int pageNumber) {
        List<Product> products = productRepository.findAll(PageRequest.of(pageNumber, 20).withSort(Sort.by(Sort.Direction.DESC, "createdAt"))).toList();
        if (products.size() == 0) {
            return new ArrayList<>();
        } else {
            return products;
        }
    }

    public List<Product> getProductsByShopId(String shopId, String pageNumber) {
        Pageable page = PageRequest.of(Integer.parseInt(pageNumber), 15).withSort(Sort.Direction.DESC, "createdAt");
        productRepository.findByShopId(shopId, page);
        List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        if (products.size() == 0) {
            return new ArrayList<>();
        } else {
            return products;
        }
    }


}
