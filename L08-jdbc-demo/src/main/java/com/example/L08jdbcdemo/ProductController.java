package com.example.L08jdbcdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static Logger LOGGER = LoggerFactory.getLogger(ProductController.class);


    @Autowired
    private ProductService productService;



    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable Long id){
        Product product = productService.getProduct(id);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }


    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product product1 = productService.addProduct(product);
        URI uri = null;
        try {
            uri = new URI("/product/"+product1.getId());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.created(uri).body(product1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id){
        Product updatedProduct = productService.updateProduct(product, id);
        if(updatedProduct == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProduct);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete( @PathVariable Long id){
        Product updatedProduct = productService.deleteProduct( id);
        if(updatedProduct == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProduct);
    }



    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProduct();
    }

    @GetMapping("/productSearch")
    public List<Product> getAllProductsByKey(@RequestParam String key){
        return productService.getProductsByKeyword(key);
    }


}
