package com.example.L06springbootmvcdemo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {

    private List<Product> productList = new ArrayList<>();

    @Autowired
    private FlightService flightService;


    private AtomicLong nextId = new AtomicLong(0);

    @PostConstruct
    public void initMethod(){
        productList.add(new Product(nextId.incrementAndGet(),"Laptop",30000.00));
        productList.add(new Product(nextId.incrementAndGet(),"Mobile",10000.00));

    }

    public List<Product> getAllProduct(){
        flightService.flightServiceMethod();
        return productList;
    }

    public Product getProduct(Long id){
        Product result = null;
        for (Product product : productList){
            if(product.getId() == id){
                result = product;
            }
        }
        return result;
    }

    public Product addProduct(Product product){
        product.setId(nextId.incrementAndGet());
        productList.add(product);
        return product;
    }
}
