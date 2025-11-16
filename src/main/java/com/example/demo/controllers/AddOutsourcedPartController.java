package com.example.demo.controllers;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
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
public class AddOutsourcedPartController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/showFormAddOutPart")
    public String showFormAddOutsourcedPart(Model theModel){
        OutsourcedPart part = new OutsourcedPart();
        theModel.addAttribute("outsourcedpart", part);
        return "OutsourcedPartForm";
    }

    @PostMapping("/showFormAddOutPart")
    public String submitForm(@Valid @ModelAttribute("outsourcedpart") OutsourcedPart part,
                             BindingResult bindingResult,
                             Model theModel) {

        System.out.println("🟢 Form Submitted:");
        System.out.println("Name: " + part.getName());
        System.out.println("Price: " + part.getPrice());
        System.out.println("Inv: " + part.getInv());
        System.out.println("Min: " + part.getMinInv());
        System.out.println("Max: " + part.getMaxInv());
        System.out.println("Company Name: " + part.getCompanyName());

        theModel.addAttribute("outsourcedpart", part);

        if (part.getInv() < part.getMinInv() || part.getInv() > part.getMaxInv()) {
            bindingResult.rejectValue("inv", "error.inv", "Inventory must be between Min and Max.");
        }

        if (bindingResult.hasErrors()) {
            System.out.println("🔴 Form has errors.");
            return "OutsourcedPartForm";
        }

        OutsourcedPartService repo = context.getBean(OutsourcedPartServiceImpl.class);
        repo.save(part);

        System.out.println("✅ Saved successfully.");

        return "confirmationaddpart";
    }
}

