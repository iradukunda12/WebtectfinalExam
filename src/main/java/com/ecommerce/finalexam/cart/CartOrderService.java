package com.ecommerce.finalexam.cart;

import java.util.List;

public interface CartOrderService {
    CartOrder save(CartOrder cartOrder);
    List<CartOrder> findAll();
}
