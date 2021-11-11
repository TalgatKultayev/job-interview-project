package com.testProject.springboot.testTask.service;

import com.testProject.springboot.testTask.dto.Request;
import com.testProject.springboot.testTask.dto.Response;
import com.testProject.springboot.testTask.entity.CheckoutOrder;

import java.util.List;

public interface OrderService {
    List<CheckoutOrder> findAll();

    CheckoutOrder findById(int theId);

    Request save(Request request);

    void deleteById(int theId);

    Request update(Request request);
}
