package org.gfg;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorDemo {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("abc");
        stringList.add("xyz");
        stringList.add("ravi");

        Iterator<String> stringIterator = stringList.iterator();
        while (stringIterator.hasNext()){
            System.out.println(stringIterator.next());
        }


    }
}
