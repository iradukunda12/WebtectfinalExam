package com.ecommerce.finalexam.cart;

import com.ecommerce.finalexam.product.Product;
import com.ecommerce.finalexam.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer Cart_quantity ;
    private String status;
    @ManyToOne
    private User user;
    @ManyToMany
    private List<Product> products;
    private boolean checked;
    @ManyToMany
    private List<CartOrder> cartOrders;
}
