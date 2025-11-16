package com.example.demo.bootstrap;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;
    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository = outsourcedPartRepository;
    }

    @Override
    public void run(String... args) {
        List<OutsourcedPart> outsourcedParts = (List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for (OutsourcedPart part : outsourcedParts) {
            System.out.println(part.getName() + " " + part.getCompanyName());
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products: " + productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts: " + partRepository.count());
        System.out.println(partRepository.findAll());

        // Task E: Add sample inventory (5 parts + 5 products)
        if (partRepository.count() == 0 && productRepository.count() == 0) {
            // Add 5 parts (Outsourced)
            OutsourcedPart wick = new OutsourcedPart();
            wick.setName("Wick");
            wick.setPrice(0.25);
            wick.setInv(100);
            wick.setMinInv(10);
            wick.setMaxInv(200);
            wick.setCompanyName("CandleParts Inc.");
            outsourcedPartRepository.save(wick);

            OutsourcedPart wax = new OutsourcedPart();
            wax.setName("Soy Wax");
            wax.setPrice(1.50);
            wax.setInv(200);
            wax.setMinInv(20);
            wax.setMaxInv(400);
            wax.setCompanyName("WaxCo");
            outsourcedPartRepository.save(wax);

            OutsourcedPart jar = new OutsourcedPart();
            jar.setName("Glass Jar");
            jar.setPrice(0.80);
            jar.setInv(150);
            jar.setMinInv(15);
            jar.setMaxInv(300);
            jar.setCompanyName("Jars & More");
            outsourcedPartRepository.save(jar);

            OutsourcedPart label = new OutsourcedPart();
            label.setName("Label");
            label.setPrice(0.10);
            label.setInv(500);
            label.setMinInv(50);
            label.setMaxInv(1000);
            label.setCompanyName("PrintIt");
            outsourcedPartRepository.save(label);

            OutsourcedPart lid = new OutsourcedPart();
            lid.setName("Lid");
            lid.setPrice(0.30);
            lid.setInv(300);
            lid.setMinInv(30);
            lid.setMaxInv(600);
            lid.setCompanyName("TopLid Corp.");
            outsourcedPartRepository.save(lid);

            // Add 5 products (Candles)
            Product lavender = new Product("Lavender Candle", 15.99, 30);
            Product vanilla = new Product("Vanilla Candle", 14.99, 25);
            Product citrus = new Product("Citrus Candle", 13.99, 20);
            Product ocean = new Product("Ocean Breeze Candle", 16.99, 15);
            Product cinnamon = new Product("Cinnamon Candle", 12.99, 18);

            productRepository.save(lavender);
            productRepository.save(vanilla);
            productRepository.save(citrus);
            productRepository.save(ocean);
            productRepository.save(cinnamon);
        }
    }
}
