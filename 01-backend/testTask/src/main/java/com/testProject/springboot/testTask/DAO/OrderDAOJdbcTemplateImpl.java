package com.testProject.springboot.testTask.DAO;

import com.testProject.springboot.testTask.entity.CheckoutOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class OrderDAOJdbcTemplateImpl implements OrderDAO{

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public OrderDAOJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CheckoutOrder> findAll() {
        return jdbcTemplate.query(" SELECT * FROM checkout_order",new BeanPropertyRowMapper<CheckoutOrder>(CheckoutOrder.class));
    }

    @Override
    public CheckoutOrder findById(int theId) {
        jdbcTemplate.queryForObject("SELECT * FROM checkout_order WHERE id=?", new BeanPropertyRowMapper<CheckoutOrder>(CheckoutOrder.class), theId);
        return null;
    }

    @Override
    public CheckoutOrder save(CheckoutOrder checkoutOrder) {
         jdbcTemplate.update("INSERT INTO checkout_order(product_name,product_price,product_quantity,unique_order_id)"
                                + "VALUES(?,?,?,?)", new Object[]{checkoutOrder.getProductName(),checkoutOrder.getProductPrice(),checkoutOrder.getProductQuantity(),checkoutOrder.getUniqueOrderId()});

         return checkoutOrder;
    }

    @Override
    public CheckoutOrder update(CheckoutOrder checkoutOrder) {
        jdbcTemplate.update("UPDATE checkout_order SET product_name=?, product_price=?,product_quantity=? WHERE id=?", new Object[]{checkoutOrder.getProductName(),checkoutOrder.getProductPrice(),checkoutOrder.getProductQuantity(),checkoutOrder.getId()});
        return checkoutOrder;
    }

    @Override
    public void deleteById(int theId) {
         jdbcTemplate.update("DELETE FROM checkout_order WHERE id=?", theId);

    }
}
