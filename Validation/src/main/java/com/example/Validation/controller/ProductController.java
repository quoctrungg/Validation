package com.example.Validation.controller;

import com.example.Validation.models.Product;
import com.example.Validation.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("product", new Product());
        return "products/create";
    }

    @PostMapping("/create")
    public String create(@Valid Product newProduct, BindingResult result, Model model){
        if(result.hasErrors())
            {
                model.addAttribute("product", newProduct);
                return "products/create";
            }
        productService.add(newProduct);
        return "redirect:/products";
    }

    @GetMapping("/index")
    public String index(Model model)
    {
        model.addAttribute("listproduct", productService.GetAll());
        return "products/index";
    }
}
