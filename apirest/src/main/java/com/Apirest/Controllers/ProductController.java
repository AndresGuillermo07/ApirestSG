package com.Apirest.Controllers;

import com.Apirest.Entities.Product;
import com.Apirest.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository ProductRepository;

        @GetMapping
        public List<Product> GetAllProduct() {
            return ProductRepository.findAll();
        }

        @GetMapping("/{id}")
        public Product GetProductById(@PathVariable Long id){
            return  ProductRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found") );
        }

        @PostMapping
        public Product CreateProduct(@RequestBody Product product){
            return ProductRepository.save(product);
        }

        @PutMapping("/{id}")
        public Product UpdateProduct(@PathVariable Long id, @RequestBody Product ProductDetail){
            Product product =  ProductRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
            product.setName(ProductDetail.getName());
            product.setPrice(ProductDetail.getPrice());

            return ProductRepository.save(product);

        }

        @DeleteMapping("/{id}")
        public String DeleteProduct(@PathVariable Long id){
            Product product =  ProductRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

            ProductRepository.delete(product);
            return "Product deleted";
        }




}
