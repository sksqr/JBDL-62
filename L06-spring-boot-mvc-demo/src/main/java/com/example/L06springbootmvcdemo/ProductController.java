package com.example.L06springbootmvcdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private static Logger LOGGER = LoggerFactory.getLogger(ProductController.class);


    @Autowired
    private ProductService productService;

//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }


    @GetMapping("/hello")
    public String hello(@RequestParam("nm") String name){
        return "Hello! "+name;
    }

    @GetMapping("/product/{id}")
    public Product getProductByID(@PathVariable Long id){
        return productService.getProduct(id);
    }


    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProduct();
    }

    @PostMapping("/product")
    public Product createProduct(@RequestBody Product product){
        LOGGER.info("Request Data: {} ",product);
        LOGGER.error("Got Exception: SomeException");
        LOGGER.debug("Debug log");
        return productService.addProduct(product);
    }

}
