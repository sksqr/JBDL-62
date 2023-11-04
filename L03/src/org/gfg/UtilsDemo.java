package org.gfg;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UtilsDemo {
    public static void main(String[] args) {
        int[] arr = {10,13,01,7,30};
        Arrays.sort(arr);
        for(int i: arr){
            System.out.println(i);
        }
        List<Integer> integerList = Arrays.asList(21,1,2,5,19);
        Collections.sort(integerList);
        System.out.println(integerList);
    }
}
