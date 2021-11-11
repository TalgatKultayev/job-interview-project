package com.testProject.springboot.testTask.service;

import com.testProject.springboot.testTask.entity.ProductPrice;

import java.util.List;

public interface ProductPriceService {
    List<ProductPrice> findAllPrices();

}
