package com.test;

import java.util.List;

public class CompositionDemo {
    public static void main(String[] args) {
        SearchProductService searchProductService = new SearchProductService(new MergeSortAlgo());
        List<String> data = searchProductService.searchProductWithSortedOrder();
    }

}
