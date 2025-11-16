package com.example.demo.controllers;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.PartService;
import com.example.demo.service.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainScreenControllerr {

    private PartService partService;
    private ProductService productService;

    private List<Part> theParts;
    private List<Product> theProducts;

    public MainScreenControllerr(PartService partService, ProductService productService){
        this.partService = partService;
        this.productService = productService;
    }

    @GetMapping("/mainscreen")
    public String listPartsandProducts(Model theModel, @Param("partkeyword") String partkeyword, @Param("productkeyword") String productkeyword){
        List<Part> partList = partService.listAll(partkeyword);
        theModel.addAttribute("parts", partList);
        theModel.addAttribute("partkeyword", partkeyword);

        List<Product> productList = productService.listAll(productkeyword);
        theModel.addAttribute("products", productList);
        theModel.addAttribute("productkeyword", productkeyword);

        return "mainscreen";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping("/buyProduct/{id}")
    public String buyProduct(@org.springframework.web.bind.annotation.PathVariable Integer id, Model model) {
        if (id == null) {
            model.addAttribute("message", "No product ID provided.");
            return "failure";
        }

        Product product = productService.findById(id);
        if (product != null) {
            int inventory = product.getInv();
            if (inventory > 0) {
                product.setInv(inventory - 1);
                productService.save(product);
                model.addAttribute("message", "Purchase successful!");
                return "success";
            } else {
                model.addAttribute("message", "Purchase failed: Inventory is zero.");
                return "failure";
            }
        } else {
            model.addAttribute("message", "Product not found.");
            return "failure";
        }
    }

}




