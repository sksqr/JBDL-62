package org.gfg.exception;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ExceptionDemo {
    public static void main(String[] args) {
//        try{
//            String data = "Hello!";
//            System.out.println(data);
//            System.out.println(data.charAt(1));
//            System.out.println(data.charAt(20));
//            System.out.println("Done");
//        }
//        catch (Exception ex){
//            ex.printStackTrace();
//        }
//        System.out.println("Done All");

        readDataFromFile();



        try {
            System.out.println(checkExist("laptop"));
            System.out.println(checkExist("mobile"));
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
           // throw new RuntimeException(e);
        }
        finally {
            System.out.println("Search Finally block");
        }
        System.out.println("Done Done");
    }

    private static void readDataFromFile() {
        try {
            FileReader fileReader = new FileReader("/tmp/test.txt");
            int a = fileReader.read();
            System.out.println("data read:"+a);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println("Executing finally block");
        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println("Done");
    }

    private static String checkExist(String product) throws ProductNotFoundException {
        if(!product.equals("laptop")){
            throw new ProductNotFoundException(product+" does not exist");
        }
        return "Exist";
    }
}
