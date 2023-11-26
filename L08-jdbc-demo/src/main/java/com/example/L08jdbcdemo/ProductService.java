package com.example.L08jdbcdemo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {


    private List<Product> productList = new ArrayList<>();


    private AtomicLong nextId = new AtomicLong(0);

    @Autowired
    private ProductDAO productDAO;

    @PostConstruct
    public void initMethod(){
        productList.add(new Product(nextId.incrementAndGet(),"Laptop",30000.00));
        productList.add(new Product(nextId.incrementAndGet(),"Mobile",10000.00));

    }

    public List<Product> getAllProduct(){
        return productList;
    }

    public Product getProduct(Long id){
        return productDAO.getProductById(id);
    }

    public Product addProduct(Product product){
        //productDAO.createProductWithPS(product);
        productDAO.createProductWithPSWithTxn(product);
        return product;
    }


    public Product updateProduct(Product product, Long id){
        Product existingProd = getProduct(id);
        if(existingProd != null){
            existingProd.setName(product.getName());
            existingProd.setCost(product.getCost());
        }
        return existingProd;
    }

    public Product deleteProduct( Long id){
        Product existingProd = getProduct(id);
        if(existingProd != null){
            productList.remove(existingProd);
        }
        return existingProd;
    }

    public List<Product> getProductsByKeyword(String keyword){
        List<Product> response = new ArrayList<>();
        for(Product product : productList){
            if(product.getName().contains(keyword)){
                response.add(product);
            }
        }
        return response;
    }
}
