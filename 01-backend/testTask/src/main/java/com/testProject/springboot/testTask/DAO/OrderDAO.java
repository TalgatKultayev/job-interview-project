package com.testProject.springboot.testTask.DAO;

import com.testProject.springboot.testTask.entity.CheckoutOrder;

import java.util.List;

public interface OrderDAO {

    List<CheckoutOrder> findAll();

    CheckoutOrder findById(int theId);

    CheckoutOrder save(CheckoutOrder checkoutOrder);

    CheckoutOrder update(CheckoutOrder checkoutOrder);

    void deleteById(int theId);
}
