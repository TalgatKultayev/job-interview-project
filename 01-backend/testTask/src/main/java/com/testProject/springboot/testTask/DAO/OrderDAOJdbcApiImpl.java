package com.testProject.springboot.testTask.DAO;

import com.testProject.springboot.testTask.entity.CheckoutOrder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDAOJdbcApiImpl implements OrderDAO{

    private static final String URL = "jdbc:mysql://localhost:3306/product_directory?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "springstudent";
    private static final String PASSWORD = "springstudent";

    private static Connection connection;

    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<CheckoutOrder> findAll() {
        List<CheckoutOrder> orders = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM checkout_order";
            ResultSet resultSet = statement.executeQuery(SQL);
            while(resultSet.next()){
                CheckoutOrder order = new CheckoutOrder();
                order.setId(resultSet.getInt("id"));
                order.setProductName(resultSet.getString("product_name"));
                order.setProductPrice(resultSet.getBigDecimal("product_price"));
                order.setProductQuantity(resultSet.getInt("product_quantity"));
                order.setUniqueOrderId(resultSet.getString("unique_order_id"));

                orders.add(order);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public CheckoutOrder findById(int theId) {
        CheckoutOrder checkoutOrder = null;
        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("SELECT * FROM checkout_order WHERE id=?");
            preparedStatement.setInt(1,theId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                checkoutOrder = new CheckoutOrder();
                checkoutOrder.setId(resultSet.getInt("id"));
                checkoutOrder.setProductName(resultSet.getString("product_name"));
                checkoutOrder.setProductPrice(resultSet.getBigDecimal("product_price"));
                checkoutOrder.setProductQuantity(resultSet.getInt("product_quantity"));
                checkoutOrder.setUniqueOrderId(resultSet.getString("unique_order_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return checkoutOrder;
    }

    @Override
    //Prepared statement prevents some sql-injection
    //
    public CheckoutOrder save(CheckoutOrder checkoutOrder) {
        int returnId = 0;

        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("INSERT INTO checkout_order(" +
                    "product_name, product_price, product_quantity, unique_order_id) " +
                    "VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, checkoutOrder.getProductName());
            preparedStatement.setBigDecimal(2,checkoutOrder.getProductPrice());
            preparedStatement.setInt(3,checkoutOrder.getProductQuantity());
            preparedStatement.setString(4,checkoutOrder.getUniqueOrderId());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) {
                checkoutOrder.setId(rs.getInt(1));
                returnId = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return findById(returnId);
    }

    @Override
    public CheckoutOrder update(CheckoutOrder checkoutOrder) {
        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("UPDATE checkout_order SET product_name=?, product_price=?," +
                                                      "product_quantity=?,unique_order_id=? where id = ?");
            preparedStatement.setString(1, checkoutOrder.getProductName());
            preparedStatement.setBigDecimal(2,checkoutOrder.getProductPrice());
            preparedStatement.setInt(3,checkoutOrder.getProductQuantity());
            preparedStatement.setString(4,checkoutOrder.getUniqueOrderId());
            preparedStatement.setLong(5,checkoutOrder.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return findById(checkoutOrder.getId());
    }

    @Override
    public void deleteById(int theId) {
        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("DELETE FROM checkout_order where id=?");
            preparedStatement.setInt(1, theId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
