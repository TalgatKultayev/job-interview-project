package com.testProject.springboot.testTask.REST;

import com.testProject.springboot.testTask.dto.Request;
import com.testProject.springboot.testTask.dto.Response;
import com.testProject.springboot.testTask.entity.CheckoutOrder;
import com.testProject.springboot.testTask.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderRESTController {

    private OrderService orderService;

    //quick and dirty: inject employee dao(constructor injection)
    @Autowired
    public OrderRESTController(OrderService orderService) {
        this.orderService = orderService;
    }

    //expose "/employees" and return list of employees
    //retrieve all products
    @GetMapping("/checkoutOrders")
    public List<CheckoutOrder> findAll() {
        return orderService.findAll();
    }
    //retrieve single product

    @GetMapping("/checkoutOrders/{orderId}")
    public CheckoutOrder findById(@PathVariable int orderId) {
        CheckoutOrder theCheckoutOrder = orderService.findById(orderId);

        if (theCheckoutOrder == null) {
            throw new RuntimeException("Employee id not found - " + orderId);
        }
        return theCheckoutOrder;
    }

    @PostMapping("/checkoutOrders")
    public Request save(@RequestBody Request request){
        //request.getCheckoutOrder().setId(0);


        return orderService.save(request);
    }

    //update the product
    @PutMapping("/checkoutOrders")
    public Request updateOrder(@RequestBody Request request){

        Request theRequest = orderService.update(request);

        return theRequest;
    }

    @DeleteMapping("/checkoutOrders/{productId}")
    public String deleteProduct(@PathVariable int productId) {

        CheckoutOrder theCheckoutOrder = orderService.findById(productId);

        if (theCheckoutOrder == null) {
            throw new RuntimeException("Employee id not found - " + productId);
        }
        orderService.deleteById(productId);

        return "Deleted product id - " + productId;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////


}
