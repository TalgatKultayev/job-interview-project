package com.testProject.springboot.testTask.DAO;

import com.testProject.springboot.testTask.entity.ProductPrice;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.EntityManager;
import java.util.List;
@CrossOrigin("http://localhost:4200")
@Repository
public class ProductPriceDAOHibernateImpl implements ProductPriceDAO{
    //inject entityManager to access hibernate sessions
    private EntityManager entityManager;

    @Autowired
    public ProductPriceDAOHibernateImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<ProductPrice> findAllPrices() {
        //get the current hibernate session
        Session session = entityManager.unwrap(Session.class);

        //create a query
        Query<ProductPrice> theQuery = session.createQuery("from ProductPrice", ProductPrice.class);

        //execute query and get the result list
        List<ProductPrice> productPrice = theQuery.getResultList();


        //return the results
        return productPrice;
    }
}
