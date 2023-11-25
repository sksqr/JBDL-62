package com.example.L07SpringMVCAnnotationdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
//@RequestMapping("/web")
public class WebController {


    @Autowired
    private ProductService productService;

    @GetMapping("/menu")
    public String getStaticMenu(){
        return "static-menu.html";
    }

    @GetMapping("/dynamic-menu")
    public ModelAndView getMenu(){
        ModelAndView modelAndView = new ModelAndView("dynamic-menu.html");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
        modelAndView.getModelMap().put("serverTime",dateFormat.format(new Date()));
        modelAndView.getModelMap().put("products",productService.getAllProduct());
        return modelAndView;
    }


}
