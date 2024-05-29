package com.ecommerce.finalexam.services;

import com.ecommerce.finalexam.cart.Cart;

import java.util.List;

public interface CheckoutServices {
    List<Cart> getAllCheckouts();
    void approveCheckout(Long id);
    void denyCheckout(Long id);
    void deleteCheckout(Long id);
}
