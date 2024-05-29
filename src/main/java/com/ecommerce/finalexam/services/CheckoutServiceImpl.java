package com.ecommerce.finalexam.services;


import com.ecommerce.finalexam.cart.Cart;
import com.ecommerce.finalexam.cart.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckoutServiceImpl implements CheckoutServices {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public List<Cart> getAllCheckouts() {
        return cartRepository.findAll();
    }

    @Override
    public void approveCheckout(Long id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        optionalCart.ifPresent(cart -> {
            cart.setStatus("APPROVED");
            cartRepository.save(cart);
            emailService.sendApprovalEmail(cart.getUser().getEmail());
        });
    }

    @Override
    public void denyCheckout(Long id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        optionalCart.ifPresent(cart -> {
            cart.setStatus("DENIED");
            cartRepository.save(cart);
            emailService.sendDenialEmail(cart.getUser().getEmail());
        });
    }

    @Override
    public void deleteCheckout(Long id) {
        cartRepository.deleteById(id);
    }
}
