package com.testProject.springboot.testTask.service;

import com.testProject.springboot.testTask.DAO.ProductPriceDAO;
import com.testProject.springboot.testTask.entity.ProductPrice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductPriceServiceImpl implements ProductPriceService{


    private ProductPriceDAO productPriceDAO;

    @Autowired
    public ProductPriceServiceImpl(ProductPriceDAO productPriceDAO){
        this.productPriceDAO = productPriceDAO;
    }
    @Override
    @Transactional
    public List<ProductPrice> findAllPrices() {
        return productPriceDAO.findAllPrices();
    }
}
