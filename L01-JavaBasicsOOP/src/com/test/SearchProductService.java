package com.test;

import java.util.List;

public class SearchProductService {

    private SortingAlgoInterface sortingAlgoInterface;

    //tight coupling
    public SearchProductService() {
        sortingAlgoInterface = new BubbleSortAlgo();
    }

    //loose coupling
    public SearchProductService(SortingAlgoInterface sortingAlgoInterface) {
        this.sortingAlgoInterface = sortingAlgoInterface;
    }

    List<String> searchProduct(){
       return null;
    }

    List<String> searchProductWithSortedOrder(){
        List<String> result = searchProduct();
        sortingAlgoInterface.sort(result);
        return result;
    }
}
