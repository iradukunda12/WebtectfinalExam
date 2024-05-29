package com.ecommerce.finalexam.cart;

import com.ecommerce.finalexam.product.Product;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class CartOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    private Integer quantity;

}
