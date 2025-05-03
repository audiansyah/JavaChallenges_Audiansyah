package com.codeid.eshopper_schema_oe.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.codeid.eshopper_schema_oe.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
    private final CategoryService categoryService;

    public IndexController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String showIndex(Model model){
        model.addAttribute("title", "Hello Bootcamp Java 2025");
        model.addAttribute("categories", categoryService.findAllCategory());
        return "index";
    }
    
}
