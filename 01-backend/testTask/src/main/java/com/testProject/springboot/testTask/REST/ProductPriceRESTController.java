package com.testProject.springboot.testTask.REST;

import com.testProject.springboot.testTask.entity.ProductPrice;
import com.testProject.springboot.testTask.service.ProductPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductPriceRESTController {

    private ProductPriceService productPriceService;

    @Autowired
    public ProductPriceRESTController(ProductPriceService productPriceService) {
        this.productPriceService = productPriceService;
    }

    //mapping for product-price table
    @GetMapping("/productPrices")
    public List<ProductPrice> findAllPrices() {
        return productPriceService.findAllPrices();
    }
}
