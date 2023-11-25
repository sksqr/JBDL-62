package com.example.L07SpringMVCAnnotationdemo;

import jakarta.annotation.PostConstruct;
import org.gfg.keywordanalyzer.KeywordAnalyzerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {

    @Autowired
    private RequestScopeService requestScopeService;

    private List<Product> productList = new ArrayList<>();


    private AtomicLong nextId = new AtomicLong(0);

    @Autowired
//    @Qualifier("uniqueKeywordAnalyzerImpl")
    private KeywordAnalyzerInterface keywordAnalyzer;

    @PostConstruct
    public void initMethod(){
        productList.add(new Product(nextId.incrementAndGet(),"Laptop",30000.00));
        productList.add(new Product(nextId.incrementAndGet(),"Mobile",10000.00));

    }

    public List<Product> getAllProduct(){
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

    public List<Product> getProductsByKeyword(String keyword){
        keywordAnalyzer.recordKeyword(keyword);
        List<Product> response = new ArrayList<>();
        for(Product product : productList){
            if(product.getName().contains(keyword)){
                response.add(product);
            }
        }
        return response;
    }
}
