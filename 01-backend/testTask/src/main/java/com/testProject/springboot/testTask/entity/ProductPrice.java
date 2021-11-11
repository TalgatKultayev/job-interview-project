package com.testProject.springboot.testTask.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="product_price_table")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="product_name")
    private String productName;

    @Column(name="product_price")
    private BigDecimal productPrice;

    public ProductPrice(String name, BigDecimal price) {
        this.productName = name;
        this.productPrice = price;
    }
}
