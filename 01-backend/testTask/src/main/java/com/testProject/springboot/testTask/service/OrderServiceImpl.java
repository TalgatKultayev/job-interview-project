package com.testProject.springboot.testTask.service;

import com.testProject.springboot.testTask.DAO.OrderDAO;
import com.testProject.springboot.testTask.dto.Request;
import com.testProject.springboot.testTask.dto.Response;
import com.testProject.springboot.testTask.entity.CheckoutOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;

    @Autowired
    public OrderServiceImpl(@Qualifier("orderDAOHibernateImpl") OrderDAO orderDAO){
        this.orderDAO = orderDAO;
    }
    @Override
    @Transactional
    public List<CheckoutOrder> findAll() {
        return orderDAO.findAll();
    }

    @Override
    @Transactional
    public CheckoutOrder findById(int theId) {
        return orderDAO.findById(theId);
    }

    @Override
    @Transactional
    public Request save(Request request) {

        //retrieve order info from dto
        CheckoutOrder checkoutOrder = request.getCheckoutOrder();

        //generate tracking number
        String uniqueOrderId = generateUniqueOrderId();

        checkoutOrder.setUniqueOrderId(uniqueOrderId);


        return new Request(orderDAO.save(checkoutOrder));
    }

    @Override
    @Transactional
    public Request update(Request request){
        CheckoutOrder checkoutOrder = request.getCheckoutOrder();

        orderDAO.update(checkoutOrder);

        return request;
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        orderDAO.deleteById(theId);
    }

    private String generateUniqueOrderId() {

        //generate random UUID number(UUID-version4)

        return UUID.randomUUID().toString();

    }
}
