package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.service.InhousePartService;
import com.example.demo.service.InhousePartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AddInhousePartController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/showFormAddInPart")
    public String showFormAddInhousePart(Model model) {
        InhousePart inhousePart = new InhousePart();
        model.addAttribute("inhousepart", inhousePart);
        return "InhousePartForm";
    }

    @PostMapping("/showFormAddInPart")
    public String submitForm(@Valid @ModelAttribute("inhousepart") InhousePart part,
                             BindingResult result, Model model) {
        model.addAttribute("inhousepart", part);

        if (part.getInv() < part.getMinInv() || part.getInv() > part.getMaxInv()) {
            result.rejectValue("inv", "error.inv", "Inventory must be between Min and Max.");
        }

        if (result.hasErrors()) {
            return "InhousePartForm";
        }

        InhousePartService repo = context.getBean(InhousePartServiceImpl.class);
        InhousePart existing = repo.findById((int) part.getId());
        if (existing != null) {
            part.setProducts(existing.getProducts());
        }

        repo.save(part);
        return "confirmationaddpart";
    }
}
