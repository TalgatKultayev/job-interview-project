package com.testProject.springboot.testTask.DAO;

import com.testProject.springboot.testTask.entity.CheckoutOrder;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class OrderDAOHibernateImpl implements OrderDAO {
    //define field for entityManager
    private EntityManager entityManager;

    @Autowired
    public OrderDAOHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<CheckoutOrder> findAll() {
        //get the current hibernate session
        Session session = entityManager.unwrap(Session.class);

        //create a query
        Query<CheckoutOrder> theQuery = session.createQuery("from CheckoutOrder", CheckoutOrder.class);

        //execute query and get the result list
        List<CheckoutOrder> checkoutOrders = theQuery.getResultList();


        //return the results
        return checkoutOrders;
    }

    @Override
    public CheckoutOrder findById(int theId) {
        //get the current session
        Session session = entityManager.unwrap(Session.class);

        //get the product
        CheckoutOrder theCheckoutOrder = session.get(CheckoutOrder.class, theId);

        //return the product
        return theCheckoutOrder;
    }

    @Override
    public CheckoutOrder save(CheckoutOrder checkoutOrder) {
        //get current session
        Session session = entityManager.unwrap(Session.class);
        checkoutOrder.setId(0);
        //save or update the Product
        session.saveOrUpdate(checkoutOrder);

        return checkoutOrder;
    }

    @Override
    public CheckoutOrder update(CheckoutOrder checkoutOrder) {
        //get current session
        Session session = entityManager.unwrap(Session.class);

        //save or update the Product
        session.saveOrUpdate(checkoutOrder);

        return checkoutOrder;
    }

    @Override
    public void deleteById(int theId) {
        //get current session
        Session session = entityManager.unwrap(Session.class);

        //delete the object with primary key
        Query theQuery = session.createQuery("delete from CheckoutOrder where id=:productId");

        theQuery.setParameter("productId", theId);

        theQuery.executeUpdate();
    }
}
