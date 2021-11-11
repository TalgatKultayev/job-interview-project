package com.testProject.springboot.testTask.DAO;

import com.testProject.springboot.testTask.entity.ProductPrice;

import java.util.List;

public interface ProductPriceDAO {

    List<ProductPrice> findAllPrices();
}
