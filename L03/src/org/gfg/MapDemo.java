package org.gfg;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("Rajesh","JBDL-62");
        map.put("Ajay","JBDL-62");
        map.put("Binit","JBDL-52");
        map.put("Vikas","JBDL-57");
        System.out.println(map.get("Ajay"));
        System.out.println(map.get("Ravi"));
        for (String key : map.keySet()){
            System.out.println(key);
        }
    }
}
